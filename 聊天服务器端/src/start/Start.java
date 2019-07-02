package start;
import java.awt.*;
import manager.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.net.*;
public class Start {
	public static void main (String[] args) {
		System.out.println("输入端口号:");
		Scanner in=new Scanner(System.in);
		String port=in.next();
		in.close();
		ServerSocket serverSocket;
		try {
			serverSocket=new ServerSocket(Integer.valueOf(port));
		}catch(Exception ex) {
			System.out.print("创建失败"+ex);
			return;
		}
		System.out.println("创建成功");
		ServerManager server=new ServerManager();
		Socket socket;
		while(true) {
			try {
				socket=serverSocket.accept();
			}catch(Exception ex) {
				System.out.println("连接失败");
				break;
			}
			System.out.println(socket.getInetAddress()+" 已连接 ");
			Thread thread=new Thread(new ServerThread(server,socket));
			
			thread.start();
		}
	}
	
}
