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

//�ļ��� ���ļ��Ĵ򿪡��½������棩
public class FileClass {
	private PaintPad paintpad;
	PaintArea paintarea = null;

	FileClass(PaintPad dp, PaintArea da) {
		paintpad = dp;
		paintarea = da;
	}

	public void newFile() {
		// TODO �½�ͼ��
		paintarea.setIndex(0);
		paintarea.setCurrentChoice(3);// ����Ĭ��Ϊ��ʻ�
		paintarea.setColor(Color.black);// ������ɫ
		paintarea.setStroke(1.0f);// ���û��ʵĴ�ϸ
		paintarea.createNewitem();
		paintarea.repaint();
	}

	public void openFile() {
		// TODO ��ͼ��

		// JFileChooser Ϊ�û�ѡ���ļ��ṩ��һ�ּ򵥵Ļ���
		JFileChooser filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		/*
		 * FileNameExtensionFilter filter = new FileNameExtensionFilter(
		 * "JPG & GIF Images", "jpg", "gif");//����ֻ��ʾ .jpg �� .gif ͼ��
		 * filechooser.setFileFilter(filter);
		 */
		int returnVal = filechooser.showOpenDialog(paintpad);

		if (returnVal == JFileChooser.CANCEL_OPTION) {// �������ȷ����ť��ִ������ó���
			return;
		}
		File fileName = filechooser.getSelectedFile();// getSelectedFile()����ѡ�е��ļ�
		fileName.canRead();
		if (fileName == null || fileName.getName().equals(""))// �ļ���������ʱ
		{
			JOptionPane.showMessageDialog(filechooser, "�ļ���", "�������ļ�����",
					JOptionPane.ERROR_MESSAGE);
		}

		else {

			try {
				BufferedImage image = ImageIO.read(fileName);
				Graphics2D g2d = (Graphics2D) paintarea.getGraphics();
				g2d.drawRenderedImage(image, AffineTransform.getScaleInstance(1, 1));
				
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(paintpad, "û���ҵ�Դ�ļ���", "û���ҵ�Դ�ļ�",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(paintpad, "���ļ��Ƿ�������", "��ȡ����",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// ����ͼ���ļ�����Σ��õ��ļ��ԣ�FileOupputSream������
	public void saveFile() {
		// TODO ����ͼ��

		// JFileChooser Ϊ�û�ѡ���ļ��ṩ��һ�ּ򵥵Ļ���
		JFileChooser filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// setFileSelectionMode()���� JFileChooser���������û�ֻѡ���ļ���ֻѡ��Ŀ¼�����߿�ѡ���ļ���Ŀ¼��
		int result = filechooser.showSaveDialog(paintpad);
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}

		File fileName = filechooser.getSelectedFile();// getSelectedFile()����ѡ�е��ļ�
		fileName.canWrite();// ����Ӧ�ó����Ƿ�����޸Ĵ˳���·������ʾ���ļ�
		if (fileName == null || fileName.getName().equals(""))// �ļ���������ʱ
		{
			JOptionPane.showMessageDialog(filechooser, "�ļ���", "�������ļ�����",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				fileName.delete();// ɾ���˳���·������ʾ���ļ���Ŀ¼
				
				BufferedImage image = new BufferedImage(paintarea.getWidth(),paintarea.getHeight(), BufferedImage.TYPE_INT_RGB); //��ȡ��С  
				Graphics2D g2d = image.createGraphics();   
				paintarea.paint(g2d); // instead of just paint(g2);   
				ImageIO.write(image, "jpeg", new File(fileName +".jpg"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
