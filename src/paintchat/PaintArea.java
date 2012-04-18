package paintchat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.MouseMotionAdapter;

//��ͼ���ࣨ����ͼ�εĻ��ƺ�����¼���
public class PaintArea extends JPanel {
	PaintPad paintpad = null;
	Graphic[] itemList = new Graphic[5000];; // ����ͼ����

	private int currentChoice = 0;// ����Ĭ�ϻ���ͼ��״̬Ϊ��ʻ�
	int index = 0;// ��ǰ�Ѿ����Ƶ�ͼ����Ŀ
	private Color color = Color.black;// ��ǰ���ʵ���ɫ
	int R, G, B;// ������ŵ�ǰ��ɫ�Ĳ�ֵ
	int f1, f2;// ������ŵ�ǰ����ķ��
	String stytle;// ��ŵ�ǰ����
	float stroke = 1.0f;// ���û��ʵĴ�ϸ ��Ĭ�ϵ��� 1.0

	PaintArea(PaintPad dp) {
		paintpad = dp;
		// ��������ó�ʮ����
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		// setCursor ����������״ ��getPredefinedCursor()����һ������ָ�����͵Ĺ��Ķ���

		setBackground(Color.white);// ���û������ı����ǰ�ɫ
		addMouseListener(new MouseA());// �������¼�
		addMouseMotionListener(new MouseB());
		createNewitem();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;// ������ʻ�
		int j = 0;
		while (j <= index) {
			draw(g2d, itemList[j]);
			j++;
		}

	}

	void draw(Graphics2D g2d, Graphic i) {
		i.draw(g2d);// �����ʴ���������������У�������ɸ��ԵĻ�ͼ
	}

	// �½�һ��ͼ�εĻ�����Ԫ����ĳ����
	public Graphic createNewitem() {
		if (currentChoice == 1)// �������������Ӧ������Ϊ�ı������ʽ
			setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		else
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		switch (currentChoice) {
		case 0:
			itemList[index] = new Pencil();
			break;
		case 1:
			itemList[index] = new Word();
			break;
		case 2:
			itemList[index] = new Line();
			break;
		case 3:
			itemList[index] = new Rect();
			break;
		case 4:
			itemList[index] = new fillRect();
			break;
		case 5:
			itemList[index] = new Oval();
			break;
		case 6:
			itemList[index] = new fillOval();
			break;
		case 7:
			itemList[index] = new Circle();
			break;
		case 8:
			itemList[index] = new fillCircle();
			break;
		case 9:
			itemList[index] = new RoundRect();
			break;
		case 10:
			itemList[index] = new fillRoundRect();
			break;
		case 11:
			itemList[index] = new Rubber();
			break;
		}
		itemList[index].type = currentChoice;
		itemList[index].R = R;
		itemList[index].G = G;
		itemList[index].B = B;
		itemList[index].stroke = stroke;
		
		return itemList[index];

	}

	public void setIndex(int x) {// ����index�Ľӿ�
		index = x;
	}

	public int getIndex() {// ����index�Ľӿ�
		return index;
	}

	public void setColor(Color color)// ������ɫ��ֵ
	{
		this.color = color;
	}

	public void setStroke(float f)// ���û��ʴ�ϸ�Ľӿ�
	{
		stroke = f;
	}

	public void chooseColor()// ѡ��ǰ��ɫ
	{
		color = JColorChooser.showDialog(paintpad, "��ѡ����ɫ", color);
		try {
			R = color.getRed();
			G = color.getGreen();
			B = color.getBlue();
		} catch (Exception e) {
			R = 0;
			G = 0;
			B = 0;
		}
		itemList[index].R = R;
		itemList[index].G = G;
		itemList[index].B = B;
	}

	public void setStroke()// ���ʴ�ϸ�ĵ���
	{
		String input;
		input = JOptionPane.showInputDialog("�����뻭�ʵĴ�ϸ( >0 )");
		try {
			stroke = Float.parseFloat(input);

		} catch (Exception e) {
			stroke = 1.0f;

		}
		itemList[index].stroke = stroke;

	}

	public void setCurrentChoice(int i)// ���ֵ�����
	{
		currentChoice = i;
	}

	public void setFont(int i, int font)// ��������
	{
		if (i == 1) {
			f1 = font;
		} else
			f2 = font;
	}

	// TODO ����¼�MouseA��̳���MouseAdapter
	// �������������Ӧ�¼��Ĳ��������İ��¡��ͷš��������ƶ����϶�����ʱ����һ���������ʱ�˳�����ʱ���������� )
	class MouseA extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent me) {
			// TODO ������
			paintpad.setStratBar("�������ڣ�[" + me.getX() + " ," + me.getY() + "]");
		}

		@Override
		public void mouseExited(MouseEvent me) {
			// TODO ����˳�
			paintpad.setStratBar("����˳��ڣ�[" + me.getX() + " ," + me.getY() + "]");
		}

		@Override
		public void mousePressed(MouseEvent me) {
			// TODO ��갴��
			paintpad.setStratBar("��갴���ڣ�[" + me.getX() + " ," + me.getY() + "]");// ����״̬����ʾ

			itemList[index].x1 = itemList[index].x2 = me.getX();
			itemList[index].y1 = itemList[index].y2 = me.getY();

			// �����ǰѡ��Ϊ��ʻ�����Ƥ�� �����������Ĳ���
			if (currentChoice == 0 || currentChoice == 11) {
				itemList[index].x1 = itemList[index].x2 = me.getX();
				itemList[index].y1 = itemList[index].y2 = me.getY();
				index++;
				createNewitem();// �����µ�ͼ�εĻ�����Ԫ����
			}
			// ���ѡ��ͼ�ε��������룬���������Ĳ���
			if (currentChoice == 1) {
				itemList[index].x1 = me.getX();
				itemList[index].y1 = me.getY();
				String input;
				input = JOptionPane.showInputDialog("��������Ҫд������֣�");
				itemList[index].s1 = input;
				itemList[index].x2 = f1;
				itemList[index].y2 = f2;
				itemList[index].s2 = stytle;
				paintpad.send(itemList[index]);

				index++;
				currentChoice = 11;
				createNewitem();// �����µ�ͼ�εĻ�����Ԫ����
				repaint();
			}

		}

		@Override
		public void mouseReleased(MouseEvent me) {
			// TODO ����ɿ�
			paintpad.setStratBar("����ɿ��ڣ�[" + me.getX() + " ," + me.getY() + "]");
			if (currentChoice == 0 || currentChoice == 11) {
				itemList[index].x1 = me.getX();
				itemList[index].y1 = me.getY();
			}
			itemList[index].x2 = me.getX();
			itemList[index].y2 = me.getY();
			repaint();
			paintpad.send(itemList[index]);

			index++;
			Graphic item = createNewitem();// �����µ�ͼ�εĻ�����Ԫ����
			
		}

	}

	// ����¼�MouseB�̳���MouseMotionAdapter
	// �����������Ĺ������϶�
	class MouseB extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent me)// �����϶�
		{
			paintpad.setStratBar("����϶��ڣ�[" + me.getX() + " ," + me.getY() + "]");
			if (currentChoice == 0 || currentChoice == 11) {
				itemList[index - 1].x1 = itemList[index].x2 = itemList[index].x1 = me.getX();
				itemList[index - 1].y1 = itemList[index].y2 = itemList[index].y1 = me.getY();
				index++;
				Graphic item = createNewitem();// �����µ�ͼ�εĻ�����Ԫ����
				paintpad.send(itemList[index - 2]);

			} else {
				itemList[index].x2 = me.getX();
				itemList[index].y2 = me.getY();
			}
			repaint();
		}

		public void mouseMoved(MouseEvent me)// �����ƶ�
		{
			paintpad.setStratBar("����ƶ��ڣ�[" + me.getX() + " ," + me.getY() + "]");
		}
	}

}
