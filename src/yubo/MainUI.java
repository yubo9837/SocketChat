package yubo;

import java.awt.EventQueue;
import java.net.Inet4Address;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField remoteIP;
	private JTextField remotePort;
	private JTextField textField;
	private JTextField sendText;
	private JLabel myIP;
	private JLabel myPort;
	private JLabel label_2;
	
	private Receive receive;
	private Send send;

	/**
	 * Launch the application.
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

	/**
	 * Create the frame.
	 */
	public MainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 492);
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
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(6, 196, 302, 172);
		contentPane.add(textField);
		textField.setColumns(10);
		
		myIP = new JLabel("New label");
		myIP.setBounds(178, 25, 130, 16);
		contentPane.add(myIP);
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
		
		sendText = new JTextField();
		sendText.setBounds(6, 408, 270, 26);
		contentPane.add(sendText);
		sendText.setColumns(10);
		
		init();
		
		JButton sendButton = new JButton("发送");
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1){
					send.sendMessage(remoteIP.getText(),Integer.parseInt(remotePort.getText()),sendText.getText());
					textField.setText(textField.getText()+"发送："+sendText.getText()+"    ");
				}
			}
		});
		sendButton.setBounds(116, 435, 117, 29);
		contentPane.add(sendButton);
		
		label_2 = new JLabel("聊天内容：");
		label_2.setBounds(6, 173, 85, 16);
		contentPane.add(label_2);
	}
	
	public void setMyPort(int Port) {
		myPort.setText(""+Port);
	}
	
	public void setReceive(String receive) {
		textField.setText(textField.getText()+"收到："+receive+"\n");
	}
	
	public void init() {
		send=new Send(0, this);
		receive=new Receive(this);
	}
}
