package paintchat;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;


public class FontDialog extends JDialog implements ItemListener, ActionListener {

	private JCheckBox bold, italic;// 工具条字体的风格（复选框）
	private JComboBox styles;// 工具条中的字体的样式（下拉列表）
	String[] fontName;
	PaintArea paintarea;
	JButton button;
	
	FontDialog(JFrame f, String s, boolean b, PaintArea da) {
		super(f, s, b);
		setLayout(new FlowLayout());
		setBounds(60, 60, 300, 200);

		this.paintarea = da;
		
		init();
	}

	public void init() {
		bold = new JCheckBox("粗体");
		bold.setFont(new Font(Font.DIALOG, Font.BOLD, 15));// 设置字体
		bold.addItemListener(this);// bold注册监听
		
		italic = new JCheckBox("斜体");
		italic.addItemListener(this);// italic注册监听
		
		italic.setFont(new Font(Font.DIALOG, Font.ITALIC, 15));// 设置字体
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();// 计算机上字体可用的名称
		fontName = ge.getAvailableFontFamilyNames();
		styles = new JComboBox(fontName);// 下拉列表的初始化
		
		styles.addItemListener(this);// stytles注册监听
		
		styles.setMaximumSize(new Dimension(250, 50));// 设置下拉列表的最大尺寸
		styles.setFont(new Font(Font.DIALOG, Font.BOLD, 14));// 设置字体
		
		button = new JButton("确定");
		button.addActionListener(this);
		
		add(Box.createHorizontalStrut(3000));
		add(Box.createHorizontalStrut(3000));
		add(Box.createHorizontalStrut(3000));
		add(Box.createHorizontalStrut(3000));
		add(Box.createHorizontalStrut(3000));
		add(Box.createHorizontalStrut(3000));
		add(bold);
		add(italic);
		add(styles);
		add(Box.createHorizontalStrut(3000));
		add(Box.createHorizontalStrut(3000));
		add(Box.createHorizontalStrut(3000));
		add(button);

	}
	
	// 字体样式处理类（粗体、斜体、字体名称）
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == bold)// 字体粗体
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
				paintarea.setFont(1, Font.BOLD);
			else
				paintarea.setFont(1, Font.PLAIN);
		} else if (e.getSource() == italic)// 字体斜体
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
				paintarea.setFont(2, Font.ITALIC);
			else
				paintarea.setFont(2, Font.PLAIN);

		} else if (e.getSource() == styles)// 字体的名称
		{
			paintarea.stytle = fontName[styles.getSelectedIndex()];
		}		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			setVisible(false);
		}
		
	}


}

