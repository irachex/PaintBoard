package paintchat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class Client extends PaintPad implements Runnable, ActionListener{
	
	Thread thread = new Thread(this);
    Socket socket = null;
    String server;
	int port;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	

	Client(String title, String server, int port) {
		super(title);
		this.server = server;
		this.port = port;
        thread.start();
	}
	
	@Override
	public void send(Graphic obj) {
		try {
			System.out.println("client: send");
			out.writeObject(obj);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		setStratBar("���з�����\n");
		try {
			socket = new Socket(server, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());


		} catch (Exception e) {
			setStratBar(String.format("�������%s:%d��������ʧ��\n", server, port));
			System.out.println("connect fail");
		}
		
		while (true) {
			System.out.println("client: wait");
			try {
				Graphic s = (Graphic) in.readObject();
				System.out.println("client: get");
				paintarea.itemList[paintarea.index] = s;
				paintarea.index++;
				paintarea.createNewitem();
				paintarea.repaint();

			} catch (Exception e) {
				setStratBar("������������ѶϿ�\n");
				break;
			}
		}
	}
	
	public static void main(String args[]) {
	    new Client("client", "127.0.0.1", 9999);
	}

}
