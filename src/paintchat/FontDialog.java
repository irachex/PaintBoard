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

	private JCheckBox bold, italic;// ����������ķ�񣨸�ѡ��
	private JComboBox styles;// �������е��������ʽ�������б�
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
		bold = new JCheckBox("����");
		bold.setFont(new Font(Font.DIALOG, Font.BOLD, 15));// ��������
		bold.addItemListener(this);// boldע�����
		
		italic = new JCheckBox("б��");
		italic.addItemListener(this);// italicע�����
		
		italic.setFont(new Font(Font.DIALOG, Font.ITALIC, 15));// ��������
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();// �������������õ�����
		fontName = ge.getAvailableFontFamilyNames();
		styles = new JComboBox(fontName);// �����б�ĳ�ʼ��
		
		styles.addItemListener(this);// stytlesע�����
		
		styles.setMaximumSize(new Dimension(250, 50));// ���������б�����ߴ�
		styles.setFont(new Font(Font.DIALOG, Font.BOLD, 14));// ��������
		
		button = new JButton("ȷ��");
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
	
	// ������ʽ�����ࣨ���塢б�塢�������ƣ�
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == bold)// �������
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
				paintarea.setFont(1, Font.BOLD);
			else
				paintarea.setFont(1, Font.PLAIN);
		} else if (e.getSource() == italic)// ����б��
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
				paintarea.setFont(2, Font.ITALIC);
			else
				paintarea.setFont(2, Font.PLAIN);

		} else if (e.getSource() == styles)// ���������
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

