package paintchat;

public class PaintChat {

	public static void main(String[] args) throws InterruptedException {
		new Server("server", 1119);
		Thread.sleep(3000);
		new Client("client1", "127.0.0.1", 1119);
		new Client("client2", "127.0.0.1", 1119);
	}

}
