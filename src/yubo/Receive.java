package yubo;

import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Receive extends Thread{
	
	private ServerSocket serverSocket;
	private MainUI mainUI;
	private Send send;
	
	public Receive (MainUI mainUI){
		this.mainUI=mainUI;
		try{
			//新建socket服务器
			serverSocket=new ServerSocket(0);
			//得到socket端口并显示出来
			mainUI.setMyPort(serverSocket.getLocalPort());
			//开启线程，调用run方法
			start();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//线程的run方法
	public void run(){
		while(true){
			Socket socket;
			try{
				//接收消息
				socket=serverSocket.accept();
				//得到收到的消息并写入进reader中
				InputStreamReader reader = new InputStreamReader(socket.getInputStream());
				int x;
				StringBuffer stringBuffer=new StringBuffer();
				while((x=reader.read())!=-1){
					stringBuffer.append((char)x);
				}
				//显示收到的消息
				mainUI.setReceive(stringBuffer.toString());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
