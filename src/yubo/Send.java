package yubo;

import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Send {
	MainUI mainUI;
	private String remoteIP="";
	private int remotePort=0;
	private String message="";
	
	public Send (int remotePort,MainUI mainUI) {
		this.mainUI=mainUI;
	}
	
	public void notRun() {
		InetSocketAddress inetSocketAddress=new InetSocketAddress(remoteIP, remotePort);
		try{
			Socket socket=new Socket();
			socket.connect(inetSocketAddress);
			OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
			writer.write(message);
			writer.flush();
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			message="";
		}
	}
	
	public void  sendMessage(String remoteIP,int remotePort,String message) {
		this.remoteIP=remoteIP;
		this.remotePort=remotePort;
		this.message=message;
		notRun();
	}
}
