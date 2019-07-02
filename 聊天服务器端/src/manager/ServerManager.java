package manager;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.net.*;
public class ServerManager {
	private Map<Socket,String> map=new HashMap();
	
	
	public void add(String name,Socket socket) {
		map.put(socket, name);
	}
	public void remove(Socket socket) {
		map.remove(socket);
	}
	public void send(byte[] temp,int type,Socket socket) throws Exception{
		for(Map.Entry<Socket, String> o : map.entrySet()) {
			if(!o.equals(socket)) {
			OutputStream out=o.getKey().getOutputStream();
			DataOutputStream outData=new DataOutputStream(out);
			outData.writeInt(type);
			outData.writeInt(temp.length);
			out.write(temp);
			}
		}
		
	}
	
	public int receiveType(Socket socket) throws Exception{
		DataInputStream inData=new DataInputStream(socket.getInputStream());
		return inData.readInt();
	}
	public byte[] receive(Socket socket) throws Exception{

			
		InputStream in=socket.getInputStream();
		DataInputStream inData=new DataInputStream(in);
		
		int length=inData.readInt();
		
		byte[] temp=new byte[length];
		in.read(temp, 0, length);
		return temp;
		
	}
	
	public String getName(Socket socket) {
		return map.get(socket);
	}
}
