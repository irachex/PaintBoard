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

public class Server extends PaintPad implements Runnable, ActionListener{
	
	Thread thread = new Thread(this);
	ServerSocket server = null;
	Socket socket = null;
	int port;
	ArrayList<ServerThread> list = new ArrayList<ServerThread>();

	Server(String title, int port) {
		super(title);
		this.port = port;
        thread.start();
	}
	
	@Override
	public void send(Graphic obj) {
		System.out.println("send" + list.size());
		for (int i=0; i<list.size(); ++i) {
			list.get(i).send(obj);
		}
	}
	
	public void send(Graphic obj, ServerThread client) {
		System.out.println("send" + list.size());
		for (int i=0; i<list.size(); ++i) {
			if (list.get(i)!=client) list.get(i).send(obj);
		}
	}
	
	public void run() {
		try {
			server = new ServerSocket(this.port);
		} catch (IOException e) {}
		setStratBar("服务已启动，正在等待客户\n");
		while (true) {
			try {
				socket = server.accept();
				setStratBar("新客户"+socket.getInetAddress()+"连接\n");
				System.out.println("connect");
				ServerThread client = new ServerThread(socket, this);
				list.add(client);
				client.start();
			} catch (Exception e) {
				setStratBar("正在等待客户\n");
				System.out.println("error");
			}
		}
	}
	
	public static void main(String args[]) {
	    new Server("server", 9999);
	}

}

class ServerThread extends Thread {
	Socket socket;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	Server server;
	
	ServerThread(Socket t, Server s) {
		socket = t;
		server = s;
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) { }
	}
	public void send(Graphic obj) {
		try {
			System.out.println(obj);
			out.writeObject(obj);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		while (true) {
			System.out.println("server: wait");
			try {
			    Graphic s = (Graphic) in.readObject();
				System.out.println("server: get");
				server.paintarea.itemList[server.paintarea.index] = s;
				server.send(s, this);
				server.paintarea.index++;
				server.paintarea.createNewitem();
				server.paintarea.repaint();
		    } catch (Exception e) {
		    	break;
		    }
		}
	}
}
