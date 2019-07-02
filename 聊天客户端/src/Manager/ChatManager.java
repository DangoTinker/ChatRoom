package Manager;
import java.awt.*;
import Frame.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class ChatManager {
	private Socket socket;
	private String name;
	
	public ChatManager(Socket a,String b) {
		socket=a;
		name=b;
	}
	
	public static Socket link(String ip,int port,String name) throws Exception{
		Socket socket=new Socket(ip,port);
		OutputStream out=socket.getOutputStream();
		DataOutputStream outData=new DataOutputStream(out);
		outData.writeInt(1);
		outData.writeInt((name.getBytes("UTF-8")).length);
		out.write(name.getBytes("UTF-8"));
		return socket;
	}
	
	
	public void send(byte[] temp,int type) throws Exception{
			OutputStream out=socket.getOutputStream();
			DataOutputStream outData=new DataOutputStream(out);
			outData.writeInt(type);
			outData.writeInt(temp.length);
			out.write(temp);
		
	}
	public Socket getSocket() {
		return socket;
	}
	
	
	public void receiveThread(MainFrame frame) {
		Thread thread=new Thread(new ReceiveThread(frame,socket));
		thread.start();
	}
	
	public void receive(MainFrame frame) throws Exception{
		InputStream in=socket.getInputStream();
		DataInputStream inData=new DataInputStream(in);
		int type=inData.readInt();
		int length=inData.readInt();
		byte[] temp=new byte[length];
		in.read(temp, 0, length);
		switch(type) {
			case 1:{
				frame.getTextArea().append("\n"+new String(temp,"UTF-8"));
				frame.getTextArea().setCaretPosition((frame.getTextArea()).getText().length());
				break;
			}
			case 2:{
				
				new FileFrame(temp);
				
				
				break;
			}
		}
		
	}
	
	class ReceiveThread implements Runnable{
		private MainFrame frame;
		private Socket socket;
		public ReceiveThread(MainFrame a,Socket b) {
			frame=a;
			socket=b;
		}
		
		public void run() {
			try {
				while(true){
						receive(frame);
				}	
			}catch(Exception ex) {
				
				new ErrorFrame("Á¬½Ó¶Ï¿ª");
			}finally {
				try {
					socket.close();
				}catch(Exception ex2) {
					new ErrorFrame(ex2.toString());
				}
			}
			
		
		}
	}

}
