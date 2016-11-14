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
	
	public void mainSend() {
		InetSocketAddress inetSocketAddress=new InetSocketAddress(remoteIP, remotePort);
		try{
			//新建socket对象
			Socket socket=new Socket();
			//socket连接远程主机
			socket.connect(inetSocketAddress);
			//将要发送的消息传到socket的输出流中
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
	
	//发送消息的方法
	public void  sendMessage(String remoteIP,int remotePort,String message) {
		this.remoteIP=remoteIP;
		this.remotePort=remotePort;
		this.message=message;
		mainSend();
	}
}
