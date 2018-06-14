import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;

public class ChatServer {
	public static void main(String[] args) throws IOException {
		boolean started;
		ServerSocket ss = null;
		DataInputStream dis = null;
		Socket s = null;
		try{
			try{
				ss = new ServerSocket(8888);
			}catch (BindException e){
				System.out.println("端口使用中");
				System.out.println("请关闭相关程序端口并重连服务器！");
				System.exit(0);
			}

			started = true;
			while (started){
				boolean bConnected = false;
				s = ss.accept();
				bConnected = true;
				System.out.println("a client connected");
				dis = new DataInputStream(s.getInputStream());
				while (bConnected){//这里导致无法连接第二个客户端
					String str = dis.readUTF();
					System.out.println(str);
				}
				dis.close();
			}
		}catch (EOFException e){
			System.out.println("Client Close");
		}
		catch (IOException e){
			e.printStackTrace();
		}finally {
			try {
				if(dis!=null)dis.close();//在处理完之后需要关闭DataStream和ServerSocket
				if(dis!=null)ss.close();
			}catch (IOException e1){
				e1.printStackTrace();
			}

		}


	}

}
