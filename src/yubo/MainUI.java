package yubo;

import java.awt.EventQueue;
import java.net.Inet4Address;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField remoteIP;//远程IP
	private JTextField remotePort;//远程端口
	private JTextField sendText;//发送数据的输入框
	private JLabel myIP;//我的IP
	private JLabel myPort;//我的端口
	private JLabel label_2;
	
	private Send send;
	private JTextArea textArea;//聊天显示区域
	private JScrollPane scrollPane;//滚动的显示界面
	private JButton buttonThree;
	
	private static String ACK="ACK";

	/**
	 * 启动程序
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainUI() {
		initUI();
		//初始化send,receive
		send=new Send(0, this);
		new Receive(this);
	}
	
	//初始化界面
	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblip = new JLabel("我的电脑的IP：");
		lblip.setBounds(6, 25, 121, 16);
		contentPane.add(lblip);
		
		JLabel label = new JLabel("我的对话端口：");
		label.setBounds(6, 53, 96, 16);
		contentPane.add(label);
		
		JLabel lblip_1 = new JLabel("小伙伴的电脑的IP：");
		lblip_1.setBounds(6, 103, 151, 16);
		contentPane.add(lblip_1);
		
		JLabel label_1 = new JLabel("小伙伴的对话端口");
		label_1.setBounds(6, 131, 151, 16);
		contentPane.add(label_1);
		
		remotePort = new JTextField();
		remotePort.setBounds(178, 126, 130, 26);
		contentPane.add(remotePort);
		remotePort.setColumns(10);
		
		myIP = new JLabel("New label");
		myIP.setBounds(178, 25, 130, 16);
		contentPane.add(myIP);
		//得到自己电脑的IP地址
		try{
		myIP.setText(Inet4Address.getLocalHost().getHostAddress());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		remoteIP = new JTextField();
		remoteIP.setBounds(178, 98, 130, 26);
		contentPane.add(remoteIP);
		remoteIP.setColumns(10);
		remoteIP.setText(myIP.getText());
		
		myPort = new JLabel("");
		myPort.setBounds(178, 53, 130, 16);
		contentPane.add(myPort);
		
		JLabel lblYaofasong = new JLabel("要发送的内容：");
		lblYaofasong.setBounds(16, 380, 111, 16);
		contentPane.add(lblYaofasong);
		
		//键盘监听事件，与鼠标监听事件相同
		sendText = new JTextField();
		sendText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					event();
				}
			}
		});
		sendText.setBounds(6, 408, 270, 26);
		contentPane.add(sendText);
		sendText.setColumns(10);
		
		JButton sendButton = new JButton("发送");
		//鼠标监听事件。发送消息并显示
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1){
					event();
				}
			}
		});
		sendButton.setBounds(116, 435, 117, 29);
		contentPane.add(sendButton);
		
		label_2 = new JLabel("聊天内容：");
		label_2.setBounds(6, 173, 85, 16);
		contentPane.add(label_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 216, 317, 150);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel label_3 = new JLabel("四种情况");
		label_3.setBounds(356, 131, 61, 16);
		contentPane.add(label_3);
		
		//设置四种情况的启动按钮
		JButton buttonOne = new JButton("一");
		buttonOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sit1();
			}
		});
		buttonOne.setBounds(356, 168, 117, 29);
		contentPane.add(buttonOne);
		
		JButton buttonTwo = new JButton("二");
		buttonTwo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sit2();
			}
		});
		buttonTwo.setBounds(356, 213, 117, 29);
		contentPane.add(buttonTwo);
		
		buttonThree = new JButton("三");
		buttonThree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sit3();
			}
		});
		buttonThree.setBounds(356, 254, 117, 29);
		contentPane.add(buttonThree);
		
		JButton buttonFour = new JButton("四");
		buttonFour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sit4();
			}
		});
		buttonFour.setBounds(356, 295, 117, 29);
		contentPane.add(buttonFour);
	}
	
	//情景1
	private void sit1() {
		//清空聊天窗口
		textArea.setText("");
		//发送消息
		send.sendMessage(remoteIP.getText(),Integer.parseInt(remotePort.getText()),"hello");
		//显示发送的消息
		textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+"hello"+"\n");
		
	}
	
	//情景2
	private void sit2() {
		//清空聊天窗口
		textArea.setText("");
		textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+"hello"+"\n");
		//发送消息
		Timer timer=new Timer();//实例化Timer类   
		timer.schedule(new TimerTask(){
		public void run(){
			send.sendMessage(remoteIP.getText(),Integer.parseInt(remotePort.getText()),"hello");
			textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+"hello"+"\n");}
		},2000);//五百毫秒
		
		//显示发送的消息
		
	}
		
	//情景3
	private void sit3() {
		//清空聊天窗口
		textArea.setText("");
		//发送消息
		send.sendMessage(remoteIP.getText(),Integer.parseInt(remotePort.getText()),"hello3");
		//显示发送的消息
		textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+"hello3"+"\n");
		Timer timer=new Timer();//实例化Timer类   
		timer.schedule(new TimerTask(){
		public void run(){
			send.sendMessage(remoteIP.getText(),Integer.parseInt(remotePort.getText()),"hello");
			textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+"hello"+"\n");}
		},2000);//五百毫秒
	}
		
	//情景4
	private void sit4() {
		//清空聊天窗口
		textArea.setText("");
		//发送消息
		send.sendMessage(remoteIP.getText(),Integer.parseInt(remotePort.getText()),"hello4");
		//显示发送的消息
		textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+"hello4"+"\n");
		Timer timer=new Timer();//实例化Timer类   
		timer.schedule(new TimerTask(){
		public void run(){
			textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+"hello4"+"\n");}
		},2000);//五百毫秒
	}
		
	//设置端口
	public void setMyPort(int Port) {
		myPort.setText(""+Port);
	}
	
	//显示收到的消息
	public void setReceive(String receive) {
		textArea.setText(textArea.getText()+"接收时间："+getTime()+"\n"+"收到："+receive+"\n");
		if (receive.equals("hello3")) {
			textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+ACK+"\n");
		}else if (receive.equals("hello4")) {
			textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+ACK+"\n");
			Timer timer=new Timer();//实例化Timer类   
			timer.schedule(new TimerTask(){
			public void run(){
				send.sendMessage(remoteIP.getText(),Integer.parseInt(remotePort.getText()),ACK);
				textArea.setText(textArea.getText()+"接收时间："+getTime()+"\n"+"收到："+"hello4"+"\n");
				textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+ACK+"\n");
				}
			},2500);//五百毫秒
			timer.schedule(new TimerTask(){
			public void run(){
				send.sendMessage(remoteIP.getText(),Integer.parseInt(remotePort.getText()),ACK);
				}
			},4000);//五百毫秒
		}else if(!receive.equals(ACK)){
			send.sendMessage(remoteIP.getText(),Integer.parseInt(remotePort.getText()),ACK);
			textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+ACK+"\n");
		}
	}
	
	//得到时间
	public String getTime()
	{
		Date date=new Date(System.currentTimeMillis());
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		return time;
	}
	
	//点击发送或按enter时执行的方法
	public void event() {
		//发送消息
		send.sendMessage(remoteIP.getText(),Integer.parseInt(remotePort.getText()),sendText.getText());
		//显示发送的消息
		textArea.setText(textArea.getText()+"发送时间："+getTime()+"\n"+"发送："+sendText.getText()+"\n");
		//清空输入框
		sendText.setText("");
	}
}
