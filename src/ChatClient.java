import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends Frame {
	Socket socket =null;
	DataOutputStream dos = null;
	TextField tfTxt = new TextField();
	TextArea taContent = new TextArea();

	public static void main(String[] args){
		new ChatClient().launchFrame();
	}

	public void launchFrame(){
		setLocation(400,300);
		this.setSize(300,300);
		add(tfTxt,BorderLayout.SOUTH);
		add(taContent,BorderLayout.NORTH);
		pack();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		});
		tfTxt.addActionListener(new TextFieldListener());
		setVisible(true);
		connect();
	}

	public void connect(){
		try {
			socket = new Socket("127.0.0.1",8888);
			dos = new DataOutputStream(socket.getOutputStream());
		}catch (UnknownHostException e){
			System.out.println("There is no host");
		}catch (IOException e) {
			System.out.println("input failed");
		}
	}

	public void disconnect(){
		try{
			if(dos!=null) {
				dos.close();
				socket.close();
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}


	private class TextFieldListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String str = tfTxt.getText().trim();
			taContent.setText(str);
			try {
				System.out.println(socket);
				if(dos!=null) {
					dos.writeUTF(str);
					dos.flush();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
