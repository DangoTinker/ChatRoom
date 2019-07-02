package Frame;
import java.awt.*;
import java.util.*;
import javax.swing.*;

import Manager.ChatManager;

import java.awt.event.*;
import java.net.*;
import java.io.*;
public class LoginFrame extends JFrame{
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	public LoginFrame () {
		this.setTitle("连接");
		this.setSize(300, 100);
		JPanel panel=new JPanel();
		JLabel label1=new JLabel("ip:");
		JLabel label2=new JLabel("port:");
		JLabel label3=new JLabel("昵称:");
		textField1=new JTextField(5);
		textField2=new JTextField(5);
		textField3=new JTextField(5);
		JButton button=new JButton("连接");
		panel.add(label1);
		panel.add(textField1);
		panel.add(label2);
		panel.add(textField2);
		panel.add(label3);
		panel.add(textField3);
		panel.add(button);
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		textField1.addKeyListener(new Enter());
		textField2.addKeyListener(new Enter());
		textField3.addKeyListener(new Enter());
		button.addMouseListener(new ButtonListener());
	}
	
	class ButtonListener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			try {
			Socket socket=ChatManager.link(textField1.getText(), Integer.valueOf(textField2.getText()), textField3.getText());
			dispose();
			new MainFrame(new ChatManager(socket,textField3.getText()));
			}catch(Exception ex) {
				new ErrorFrame("连接失败"+ex);
			}
		}
	}
	
	
	
	class Enter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getSource()==textField1&&e.getKeyCode()==KeyEvent.VK_ENTER) {
				textField2.requestFocus();
			}
			if(e.getSource()==textField2&&e.getKeyCode()==KeyEvent.VK_ENTER) {
				textField3.requestFocus();
			}
			if(e.getSource()==textField3&&e.getKeyCode()==KeyEvent.VK_ENTER) {
				try {
					Socket socket=ChatManager.link(textField1.getText(), Integer.valueOf(textField2.getText()), textField3.getText());
					dispose();
					new MainFrame(new ChatManager(socket,textField3.getText()));
					}catch(Exception ex) {
						new ErrorFrame("连接失败"+ex);
					}
			}
		}
	}
	
	
}
