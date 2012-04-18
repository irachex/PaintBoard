package paintchat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;
//ͼ�λ����� ���ڻ��Ƹ���ͼ��
//���࣬����ͼ�ε�Ԫ���õ����еĽӿڣ�����ʹ�õ�
//���������Էŵ������У�������Ա����ظ�����

/*��ͨ��ʵ�� java.io.Serializable �ӿ������������л����ܡ�
 δʵ�ִ˽ӿڵ��ཫ�޷�ʹ���κ�״̬���л������л���
 �����л�������������ͱ����ǿ����л��ġ����л��ӿ�û�з������ֶΣ�
 �����ڱ�ʶ�����л������塣*/

public class Graphic implements Serializable {

	public int x1=0, x2=0, y1=0, y2=0; // ������������
	public int R=0, G=0, B=0; // ����ɫ������
	public float stroke=1; // ����������ϸ������
	public int type=0; // ������������
	public String s1=""; // ��������ķ��
	public String s2=""; // ��������ķ��

	void draw(Graphics2D g2d) {
	}// �����ͼ����
}

class Line extends Graphic// ֱ����
{
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));// Ϊ Graphics2D ���������� Paint ���ԡ�
		// ʹ��Ϊ null �� Paint ������ô˷����Դ� Graphics2D �ĵ�ǰ Paint ����û���κ�Ӱ�졣

		g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		// setStroke(Stroke s)Ϊ Graphics2D ���������� Stroke
		// BasicStroke �ඨ�����ͼ��ͼԪ�����������Ե�һ����������
		// BasicStroke.CAP_ROUNDʹ�ð뾶���ڻ��ʿ��һ���Բ��װ�ν���δ��յ���·���������߶�
		// BasicStroke.JOIN_BEVELͨ��ֱ�����ӿ�����������ǣ���·���߶�������һ��
		g2d.drawLine(x1, y1, x2, y2);// ��ֱ��

	}
}

class Rect extends Graphic {// ������
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawRect(Math.min(x1, x2), Math.min(y2, y2), Math.abs(x1 - x2),
				Math.abs(y1 - y2));
	}
}

class fillRect extends Graphic {// ʵ�ľ�����
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.fillRect(Math.min(x1, x2), Math.min(y2, y2), Math.abs(x1 - x2),
				Math.abs(y1 - y2));
	}
}

class Oval extends Graphic {// ��Բ��
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawOval(Math.min(x1, x2), Math.min(y2, y2), Math.abs(x1 - x2),
				Math.abs(y1 - y2));
	}
}

class fillOval extends Graphic {// ʵ����Բ��
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.fillOval(Math.min(x1, x2), Math.min(y2, y2), Math.abs(x1 - x2),
				Math.abs(y1 - y2));
	}
}

class Circle extends Graphic {// ������
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawOval(Math.min(x1, x2), Math.min(y2, y2),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
	}
}

class fillCircle extends Graphic {// ʵ��Բ��
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.fillOval(Math.min(x1, x2), Math.min(y2, y2),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)),
				Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
	}
}

class RoundRect extends Graphic {// Բ�Ǿ�����
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawRoundRect(Math.min(x1, x2), Math.min(y2, y2),
				Math.abs(x1 - x2), Math.abs(y1 - y2), 50, 35);
	}
}

class fillRoundRect extends Graphic {// ʵ��Բ�Ǿ�����
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.fillRoundRect(Math.min(x1, x2), Math.min(y2, y2),
				Math.abs(x1 - x2), Math.abs(y1 - y2), 50, 35);
	}
}

class Pencil extends Graphic {// ��ʻ���
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.drawLine(x1, y1, x2, y2);
	}
}

class Rubber extends Graphic {// ��Ƥ����
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(255, 255, 255));// ��ɫ
		g2d.setStroke(new BasicStroke(stroke + 4, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		g2d.drawLine(x1, y1, x2, y2);
	}
}

class Word extends Graphic {// ����������
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));
		g2d.setFont(new Font(s2, x2 + y2, ((int) stroke) * 18));// ��������
		if (s1 != null)
			g2d.drawString(s1, x1, y1);
	}
}