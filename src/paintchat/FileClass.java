package paintchat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

//文件类 （文件的打开、新建、保存）
public class FileClass {
	private PaintPad paintpad;
	PaintArea paintarea = null;

	FileClass(PaintPad dp, PaintArea da) {
		paintpad = dp;
		paintarea = da;
	}

	public void newFile() {
		// TODO 新建图像
		paintarea.setIndex(0);
		paintarea.setCurrentChoice(3);// 设置默认为随笔画
		paintarea.setColor(Color.black);// 设置颜色
		paintarea.setStroke(1.0f);// 设置画笔的粗细
		paintarea.createNewitem();
		paintarea.repaint();
	}

	public void openFile() {
		// TODO 打开图像

		// JFileChooser 为用户选择文件提供了一种简单的机制
		JFileChooser filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		/*
		 * FileNameExtensionFilter filter = new FileNameExtensionFilter(
		 * "JPG & GIF Images", "jpg", "gif");//其中只显示 .jpg 和 .gif 图像
		 * filechooser.setFileFilter(filter);
		 */
		int returnVal = filechooser.showOpenDialog(paintpad);

		if (returnVal == JFileChooser.CANCEL_OPTION) {// 如果单击确定按钮就执行下面得程序
			return;
		}
		File fileName = filechooser.getSelectedFile();// getSelectedFile()返回选中的文件
		fileName.canRead();
		if (fileName == null || fileName.getName().equals(""))// 文件名不存在时
		{
			JOptionPane.showMessageDialog(filechooser, "文件名", "请输入文件名！",
					JOptionPane.ERROR_MESSAGE);
		}

		else {

			try {
				BufferedImage image = ImageIO.read(fileName);
				Graphics2D g2d = (Graphics2D) paintarea.getGraphics();
				g2d.drawRenderedImage(image, AffineTransform.getScaleInstance(1, 1));
				
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(paintpad, "没有找到源文件！", "没有找到源文件",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(paintpad, "读文件是发生错误！", "读取错误",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// 保存图像文件程序段，用到文件对（FileOupputSream）象流
	public void saveFile() {
		// TODO 保存图像

		// JFileChooser 为用户选择文件提供了一种简单的机制
		JFileChooser filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// setFileSelectionMode()设置 JFileChooser，以允许用户只选择文件、只选择目录，或者可选择文件和目录。
		int result = filechooser.showSaveDialog(paintpad);
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}

		File fileName = filechooser.getSelectedFile();// getSelectedFile()返回选中的文件
		fileName.canWrite();// 测试应用程序是否可以修改此抽象路径名表示的文件
		if (fileName == null || fileName.getName().equals(""))// 文件名不存在时
		{
			JOptionPane.showMessageDialog(filechooser, "文件名", "请输入文件名！",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				fileName.delete();// 删除此抽象路径名表示的文件或目录
				
				BufferedImage image = new BufferedImage(paintarea.getWidth(),paintarea.getHeight(), BufferedImage.TYPE_INT_RGB); //截取大小  
				Graphics2D g2d = image.createGraphics();   
				paintarea.paint(g2d); // instead of just paint(g2);   
				ImageIO.write(image, "jpeg", new File(fileName +".jpg"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
