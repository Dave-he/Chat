import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class ChatServer {
	public static void main(String[] args) throws IOException {
		boolean started;
		ServerSocket ss = null;
		DataInputStream dis = null;
		Socket s = null;
		try{
			ss = new ServerSocket(8888);
			started = true;

			while (started){
				boolean bConnected = false;
				s = ss.accept();
				bConnected = true;
				System.out.println("a client connected");
				dis = new DataInputStream(s.getInputStream());
				while (bConnected){
					String str = dis.readUTF();
					System.out.println(str);
				}
				dis.close();
			}
		}catch (IOException e){
			try {
				dis.close();//在处理完之后需要关闭DataStream和ServerSocket
				ss.close();
			}catch (IOException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}


	}

}
