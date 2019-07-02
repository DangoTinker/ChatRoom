package Frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
public class ErrorFrame extends JFrame{
	private JButton button;
	public ErrorFrame(String message) {
		this.setTitle("´íÎó");
		JPanel panel=new JPanel();
		button=new JButton("È·¶¨");
		JLabel label=new JLabel(message);
		this.add(panel);
		panel.add(label);
		panel.add(button);
		this.setSize(400, 125);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		button.addMouseListener(new ButtonListener());
		this.addKeyListener(new Enter());
	}
	
	class ButtonListener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			dispose();
		}
	}
	
	class Enter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER ) {
				dispose();
			}
		}
	}
	
}
