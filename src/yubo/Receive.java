package yubo;

import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Receive extends Thread{
	
	private ServerSocket serverSocket;
	private MainUI mainUI;
	
	public Receive (MainUI mainUI){
		this.mainUI=mainUI;
		try{
			serverSocket=new ServerSocket(0);
			mainUI.setMyPort(serverSocket.getLocalPort());
			start();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run(){
		while(true){
			Socket socket;
			try{
				socket=serverSocket.accept();
				InputStreamReader reader = new InputStreamReader(socket.getInputStream());
				int x;
				StringBuffer stringBuffer=new StringBuffer();
				while((x=reader.read())!=-1){
					stringBuffer.append((char)x);
				}
				mainUI.setReceive(stringBuffer.toString());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
