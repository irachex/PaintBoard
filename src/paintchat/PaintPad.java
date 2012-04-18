package paintchat;

import java.awt.*;
import java.awt.event.*;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.*;

// ��������
public class PaintPad extends JFrame implements ActionListener {

	protected JToolBar toolpanel;// ���尴ť���
	protected JMenuBar bar;// ����˵���
	protected JMenu file, edit, help;// ����˵�
	protected JMenuItem newfile, openfile, savefile, exit;// file �˵��еĲ˵���
	protected JMenuItem setfont, setcolor, setwidth;// file �˵��еĲ˵���
	protected JMenuItem helpin, helpmain;// help �˵��еĲ˵���
	protected JLabel startbar;// ״̬��
	protected PaintArea paintarea;// ������Ķ���
	protected Help helpobject; // ����һ�����������
	protected FileClass fileclass;// �ļ�����
	protected FontDialog fontDialog;
	// ���幤����ͼ�������
	protected String names[] = { "pen", "word", "line", "rect", "frect", "oval", "foval", "circle", "fcircle",
			"roundrect", "froundrect", "rubber", "color", "stroke" };// ���幤����ͼ�������
	protected Icon icons[];// ����ͼ������

	protected String tiptext[] = {// ����������Ƶ���Ӧ�İ�ť�ϸ�����Ӧ����ʾ
	 "Ǧ��", "����" , "ֱ��", "����", "������", "��Բ", "�����Բ",
			"Բ", "���Բ", "Բ�Ǿ���", "���Բ�Ǿ���", "��Ƥ��", "��ɫ", "��ϸ"};
	JButton button[];// ���幤�����еİ�ť��
	
	public PaintPad(String string) {
		// TODO ������Ĺ��캯��
		super(string);
		
		// ״̬���ĳ�ʼ��
		startbar = new JLabel("�ҵ�СС��ͼ��");

		// �滭���ĳ�ʼ��
		paintarea = new PaintArea(this);
		
		// �˵��ĳ�ʼ��
		initMenubar();
		
		// �������ĳ�ʼ��
		initToolbar();
		

		Container con = getContentPane();// �õ��������
		con.add(toolpanel, BorderLayout.WEST);
		con.add(paintarea, BorderLayout.CENTER);
		con.add(startbar, BorderLayout.SOUTH);
		setBounds(200, 100, 800, 600);
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initMenubar() {
		file = new JMenu("�ļ�");
		edit = new JMenu("�༭");
		help = new JMenu("����");
		bar = new JMenuBar();// �˵����ĳ�ʼ��

		// �˵�����Ӳ˵�
		bar.add(file);
		bar.add(edit);
		bar.add(help);

		// ��������Ӳ˵���
		setJMenuBar(bar);

		// �˵�����ӿ�ݼ�
		file.setMnemonic('F');// ����ALT+��F��
		help.setMnemonic('H');// ����ALT+��H��

		newfile = new JMenuItem("�½�");
		openfile = new JMenuItem("��");
		savefile = new JMenuItem("����");
		exit = new JMenuItem("�˳�");

		// File �˵�����Ӳ˵���
		file.add(newfile);
		file.add(openfile);
		file.add(savefile);
		file.add(exit);

		// File �˵�����ӿ�ݼ�
		newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));

		// File �˵����ע�����
		newfile.addActionListener(this);
		openfile.addActionListener(this);
		savefile.addActionListener(this);
		exit.addActionListener(this);
		
		setfont = new JMenuItem("����");
		setcolor = new JMenuItem("��ɫ");
		setwidth = new JMenuItem("���");

		fontDialog = new FontDialog(this, "��������", false, paintarea);
		edit.add(setfont);
		edit.add(setcolor);
		edit.add(setwidth);
		
		setfont.addActionListener(this);
		setcolor.addActionListener(this);
		setwidth.addActionListener(this);

		// Help �˵���ĳ�ʼ��
		helpmain = new JMenuItem("����");
		helpin = new JMenuItem("����PaintChat");

		// Help �˵�����Ӳ˵���
		help.add(helpmain);
		help.addSeparator();// ��ӷָ���
		help.add(helpin);

		// Help �˵����ע�����
		helpin.addActionListener(this);
		helpmain.addActionListener(this);
		
		helpobject = new Help(this);
		fileclass = new FileClass(this, paintarea);

	}
	
	public void initToolbar() {
		toolpanel = new JToolBar(JToolBar.VERTICAL);
		icons = new ImageIcon[names.length];
		button = new JButton[names.length];
		for (int i = 0; i < names.length; i++) {
			icons[i] = new ImageIcon(getClass().getResource(
					"/icon/" + names[i] + ".png"));// ���ͼƬ������·��Ϊ��׼��
			button[i] = new JButton("", icons[i]);// �����������еİ�ť
			button[i].setToolTipText(tiptext[i]);// ����������Ƶ���Ӧ�İ�ť�ϸ�����Ӧ����ʾ
			toolpanel.add(Box.createHorizontalStrut(1));
			toolpanel.add(button[i]);
			button[i].setBorder(null);
			button[i].addActionListener(this);
		}
	}

	// ����״̬����ʾ���ַ�
	public void setStratBar(String s) {
		startbar.setText(s);
	}

	public void send(Graphic obj) {
	    // not implement
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO �¼��Ĵ���
		for (int i = 0; i <= 11; i++) {
			if (e.getSource() == button[i] && i!=1) {
				paintarea.setCurrentChoice(i);
				paintarea.createNewitem();
				paintarea.repaint();
			}

		}
		if (e.getSource() == newfile)// �½�
		{
			fileclass.newFile();
		} else if (e.getSource() == openfile)// ��
		{
			fileclass.openFile();
		} else if (e.getSource() == savefile)// ����
		{
			fileclass.saveFile();
		} else if (e.getSource() == exit)// �˳�����
		{
			System.exit(0);
		} else if (e.getSource() == button[12] || e.getSource() == setcolor)// ������ɫ�Ի���
		{
			paintarea.chooseColor();// ��ɫ��ѡ��
		} else if (e.getSource() == button[13] || e.getSource() == setwidth)// ���ʴ�ϸ
		{
			paintarea.setStroke();// ���ʴ�ϸ�ĵ���
		} else if (e.getSource() == button[1])// �������
		{
			JOptionPane.showMessageDialog(null, "�뵥��������ȷ���������ֵ�λ�ã�", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			paintarea.setCurrentChoice(1);
			paintarea.createNewitem();
			paintarea.repaint();
		} else if (e.getSource() == helpin)// ������Ϣ
		{
			helpobject.AboutBook();
		} else if (e.getSource() == helpmain)// ��������
		{
			helpobject.MainHeip();
		} else if (e.getSource() == setfont) //��������
		{
			fontDialog.setLocation(this.getBounds().x+200, this.getBounds().y+100);
			fontDialog.setVisible(true);
		}

	}
	
}
