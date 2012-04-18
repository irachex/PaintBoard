package paintchat;

import java.awt.*;
import java.awt.event.*;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.*;

// 主界面类
public class PaintPad extends JFrame implements ActionListener {

	protected JToolBar toolpanel;// 定义按钮面板
	protected JMenuBar bar;// 定义菜单条
	protected JMenu file, edit, help;// 定义菜单
	protected JMenuItem newfile, openfile, savefile, exit;// file 菜单中的菜单项
	protected JMenuItem setfont, setcolor, setwidth;// file 菜单中的菜单项
	protected JMenuItem helpin, helpmain;// help 菜单中的菜单项
	protected JLabel startbar;// 状态栏
	protected PaintArea paintarea;// 画布类的定义
	protected Help helpobject; // 定义一个帮助类对象
	protected FileClass fileclass;// 文件对象
	protected FontDialog fontDialog;
	// 定义工具栏图标的名称
	protected String names[] = { "pen", "word", "line", "rect", "frect", "oval", "foval", "circle", "fcircle",
			"roundrect", "froundrect", "rubber", "color", "stroke" };// 定义工具栏图标的名称
	protected Icon icons[];// 定义图象数组

	protected String tiptext[] = {// 这里是鼠标移到相应的按钮上给出相应的提示
	 "铅笔", "文字" , "直线", "矩形", "填充矩形", "椭圆", "填充椭圆",
			"圆", "填充圆", "圆角矩形", "填充圆角矩形", "橡皮擦", "颜色", "粗细"};
	JButton button[];// 定义工具条中的按钮组
	
	public PaintPad(String string) {
		// TODO 主界面的构造函数
		super(string);
		
		// 状态栏的初始化
		startbar = new JLabel("我的小小绘图板");

		// 绘画区的初始化
		paintarea = new PaintArea(this);
		
		// 菜单的初始化
		initMenubar();
		
		// 工具栏的初始化
		initToolbar();
		

		Container con = getContentPane();// 得到内容面板
		con.add(toolpanel, BorderLayout.WEST);
		con.add(paintarea, BorderLayout.CENTER);
		con.add(startbar, BorderLayout.SOUTH);
		setBounds(200, 100, 800, 600);
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initMenubar() {
		file = new JMenu("文件");
		edit = new JMenu("编辑");
		help = new JMenu("帮助");
		bar = new JMenuBar();// 菜单条的初始化

		// 菜单条添加菜单
		bar.add(file);
		bar.add(edit);
		bar.add(help);

		// 界面中添加菜单条
		setJMenuBar(bar);

		// 菜单中添加快捷键
		file.setMnemonic('F');// 既是ALT+“F”
		help.setMnemonic('H');// 既是ALT+“H”

		newfile = new JMenuItem("新建");
		openfile = new JMenuItem("打开");
		savefile = new JMenuItem("保存");
		exit = new JMenuItem("退出");

		// File 菜单中添加菜单项
		file.add(newfile);
		file.add(openfile);
		file.add(savefile);
		file.add(exit);

		// File 菜单项添加快捷键
		newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));

		// File 菜单项的注册监听
		newfile.addActionListener(this);
		openfile.addActionListener(this);
		savefile.addActionListener(this);
		exit.addActionListener(this);
		
		setfont = new JMenuItem("字体");
		setcolor = new JMenuItem("颜色");
		setwidth = new JMenuItem("宽度");

		fontDialog = new FontDialog(this, "设置字体", false, paintarea);
		edit.add(setfont);
		edit.add(setcolor);
		edit.add(setwidth);
		
		setfont.addActionListener(this);
		setcolor.addActionListener(this);
		setwidth.addActionListener(this);

		// Help 菜单项的初始化
		helpmain = new JMenuItem("帮助");
		helpin = new JMenuItem("关于PaintChat");

		// Help 菜单中添加菜单项
		help.add(helpmain);
		help.addSeparator();// 添加分割线
		help.add(helpin);

		// Help 菜单项的注册监听
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
					"/icon/" + names[i] + ".png"));// 获得图片（以类路径为基准）
			button[i] = new JButton("", icons[i]);// 创建工具条中的按钮
			button[i].setToolTipText(tiptext[i]);// 这里是鼠标移到相应的按钮上给出相应的提示
			toolpanel.add(Box.createHorizontalStrut(1));
			toolpanel.add(button[i]);
			button[i].setBorder(null);
			button[i].addActionListener(this);
		}
	}

	// 设置状态栏显示的字符
	public void setStratBar(String s) {
		startbar.setText(s);
	}

	public void send(Graphic obj) {
	    // not implement
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO 事件的处理
		for (int i = 0; i <= 11; i++) {
			if (e.getSource() == button[i] && i!=1) {
				paintarea.setCurrentChoice(i);
				paintarea.createNewitem();
				paintarea.repaint();
			}

		}
		if (e.getSource() == newfile)// 新建
		{
			fileclass.newFile();
		} else if (e.getSource() == openfile)// 打开
		{
			fileclass.openFile();
		} else if (e.getSource() == savefile)// 保存
		{
			fileclass.saveFile();
		} else if (e.getSource() == exit)// 退出程序
		{
			System.exit(0);
		} else if (e.getSource() == button[12] || e.getSource() == setcolor)// 弹出颜色对话框
		{
			paintarea.chooseColor();// 颜色的选择
		} else if (e.getSource() == button[13] || e.getSource() == setwidth)// 画笔粗细
		{
			paintarea.setStroke();// 画笔粗细的调整
		} else if (e.getSource() == button[1])// 添加文字
		{
			JOptionPane.showMessageDialog(null, "请单击画板以确定输入文字的位置！", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			paintarea.setCurrentChoice(1);
			paintarea.createNewitem();
			paintarea.repaint();
		} else if (e.getSource() == helpin)// 帮助信息
		{
			helpobject.AboutBook();
		} else if (e.getSource() == helpmain)// 帮助主题
		{
			helpobject.MainHeip();
		} else if (e.getSource() == setfont) //设置字体
		{
			fontDialog.setLocation(this.getBounds().x+200, this.getBounds().y+100);
			fontDialog.setVisible(true);
		}

	}
	
}
