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
		System.out.println("����˿ں�:");
		Scanner in=new Scanner(System.in);
		String port=in.next();
		in.close();
		ServerSocket serverSocket;
		try {
			serverSocket=new ServerSocket(Integer.valueOf(port));
		}catch(Exception ex) {
			System.out.print("����ʧ��"+ex);
			return;
		}
		System.out.println("�����ɹ�");
		ServerManager server=new ServerManager();
		Socket socket;
		while(true) {
			try {
				socket=serverSocket.accept();
			}catch(Exception ex) {
				System.out.println("����ʧ��");
				break;
			}
			System.out.println(socket.getInetAddress()+" ������ ");
			Thread thread=new Thread(new ServerThread(server,socket));
			
			thread.start();
		}
	}
	
}
