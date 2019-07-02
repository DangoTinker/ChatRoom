package Frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
public class FileFrame extends JFrame{
	private byte[] temp; 
	private JButton button1,button2,button3;
	private File file;
	public FileFrame(byte[] a) {
		temp=a;
		this.setTitle("接收文件提示");
		JPanel panel=new JPanel();
		button1=new JButton("确定");
		button2=new JButton("取消");
		JLabel label=new JLabel("是否接收");
		this.add(panel);
		
		JLabel label3=new JLabel("路径");
		button3=new JButton("选择");
		panel.add(label3);
		panel.add(button3);
		panel.add(label);
		panel.add(button1);
		panel.add(button2);
		
		
		this.setSize(400, 125);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		button1.addMouseListener(new Button1Listener());
		button2.addMouseListener(new Button2Listener());
		button3.addMouseListener(new Button3Listener());
		this.addKeyListener(new Enter());
	}
	
	class Button1Listener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			try {
				
			OutputStream fileOut=new FileOutputStream(file);
			fileOut.write(temp);
			fileOut.close();
			dispose();
			}catch(Exception ex) {
				new ErrorFrame("接收失败"+ex);
			}
		}
	}
	
	class Button2Listener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			dispose();
		}
	}
	
	class Button3Listener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			JFrame frame=new JFrame();
			FileDialog openField=new FileDialog(frame,"打开文件",FileDialog.SAVE);
			openField.setVisible(true);
			if(openField.getFile()!=null) {
				file=new File(openField.getDirectory(),openField.getFile());
 			
			}
		}
	}
	
	class Enter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getSource()==button1&&e.getKeyCode()==KeyEvent.VK_ENTER ) {
				try {
					OutputStream fileOut=new FileOutputStream(file);
					fileOut.write(temp);
					fileOut.close();
					dispose();
					}catch(Exception ex) {
						new ErrorFrame("接收失败"+ex);
					}
			}
			
			if(e.getSource()==button2&&e.getKeyCode()==KeyEvent.VK_ENTER ) {
				dispose();
			}
		}
	}
	
}
