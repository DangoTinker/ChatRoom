package Frame;
import java.awt.*;
import javax.swing.*;

import Manager.ChatManager;

import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
public class MainFrame extends JFrame{
	private JTextField textField;
	private JTextArea textArea;
	private ChatManager chatManager;
	private JFrame frame;
	private File file=null;
	private JButton button3;
	public MainFrame(ChatManager a) {
		chatManager=a;
		this.setTitle("聊天室");
		textField=new JTextField(12);
		textArea=new JTextArea(20,20);
		textArea.setEditable(false);
		JScrollPane scrollPanel=new JScrollPane(textArea);
		this.setLayout(new BorderLayout());
		JButton button1=new JButton("发送");
		JButton button2=new JButton("清屏");
		button3=new JButton("选择文件");
		JPanel panel=new JPanel();
		this.add(panel);
		panel.add(scrollPanel);
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(textField);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		textField.addKeyListener(new Enter());
		button1.addMouseListener(new Button1Listener());
		button2.addMouseListener(new Button2Listener());
		button3.addMouseListener(new Button3Listener());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		textField.requestFocus();
		
		
		this.setSize(300,500);
		chatManager.receiveThread(this);
			
		

	}
	
	public JTextField getTextField() {
		return textField;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextField(String a) {
		textField.setText(a);
	}
	
	class Button1Listener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			sendCollection();
		}
	}
	
	class Button2Listener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			textArea.setText("");
		}
	}
	
	class Button3Listener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			FileDialog openField=new FileDialog(frame,"打开文件",FileDialog.LOAD);
			openField.setVisible(true);
			if(openField.getFile()!=null) {
				file=new File(openField.getDirectory(),openField.getFile());
				button3.setText(file.getName());
			
			}
		
		}
	}
	
	public void sendCollection() {
		try {
			if(file!=null) {
				
				InputStream fileIn=new FileInputStream(file);
				String tipz="已发送文件"+file.getName();
				chatManager.send(tipz.getBytes("UTF-8"), 1);
				chatManager.send(fileIn.readAllBytes(), 2);
				file=null;
				button3.setText("选择文件");
				fileIn.close();
			}
			if(file==null) {
				chatManager.send((textField.getText()).getBytes("UTF-8"),1);
				textField.setText("");
			}
			
			
		}catch(Exception ex) {
			new ErrorFrame("发送失败"+ex);
		}
	}
	
	class Enter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				sendCollection();
			}
		}
	}
	
}



