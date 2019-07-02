package manager;
import java.awt.*;
import java.util.concurrent.locks.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.net.*;
public class ServerThread implements Runnable{
	private ServerManager server;
	private Socket socket;
	public ServerThread(ServerManager a,Socket b) {
		server=a;
		socket=b;
	}
	
	public void run() {
		Integer type=0;
		
		try{		
				type=server.receiveType(socket);
				String name=new String(server.receive(socket),"UTF-8");
				server.add(name,socket);
				String welcome="欢迎"+name;
				server.send(welcome.getBytes("UTF-8"),type,null);
				while(true) {
					Date date=new Date();
					type=server.receiveType(socket);
					byte[] temp=server.receive(socket);
					String time=date.getHours()+":"+date.getMonth()+":"+date.getMinutes()+"   ";
					String a=time+name;
					if(type==2) {
						server.send(temp, type,socket);
					}
					else {
						server.send(a.getBytes("UTF-8"),1,null);
						server.send(temp, type,null);
					}
				}
		}catch(Exception ex) {
			System.out.println(socket.getInetAddress()+"已断开");
					return;
		}finally {
			
			try {
				String leave=server.getName(socket)+"已经离开聊天室";
				
				server.remove(socket);
				server.send(leave.getBytes("UTF-8"),1,null);
				socket.close();
			}catch(Exception ex2) {
				ex2.printStackTrace();
			}
		}
}
}
