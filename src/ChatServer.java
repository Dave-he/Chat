import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class ChatServer {
	public static void main(String[] args) throws IOException {
		boolean started ;
		try{
			ServerSocket ss = new ServerSocket(8888);
			started = true;

			while (started){
				boolean bConnected = false;
				Socket s = ss.accept();
				bConnected = true;
				System.out.println("a client connected");
				DataInputStream dis = new DataInputStream(s.getInputStream());
				while (bConnected){
					String str = dis.readUTF();
					System.out.println(str);
				}
				dis.close();
			}
		}catch (IOException e){
			e.printStackTrace();
		}


	}

}
