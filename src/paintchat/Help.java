package paintchat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Help extends JFrame {
	private PaintPad paintpad = null;

	Help(PaintPad dp) {
		paintpad = dp;
	}

	public void MainHeip() {
		JOptionPane.showMessageDialog(this, "以画图的方式聊天", "PaintChat",
				JOptionPane.WARNING_MESSAGE);
	}

	public void AboutBook() {
		JOptionPane.showMessageDialog(paintpad, "Product by Zhang Huayi", "About", JOptionPane.WARNING_MESSAGE);
	}
}
