/*
 * �ͻ����߳��࣬���ϵؽ��մӷ�������������Ϣ
 */
package Therd;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Message.Message;

public class ClickTherd extends Thread {
	private Socket s;
	public ClickTherd(Socket s) {
		// TODO Auto-generated constructor stub
		this.s = s;
	}

	public void run() {
		while (true) {//�����ܷ�������������Ϣ
			try {
				System.out.println("���뵽���߳�ѭ��");
				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				Message m = (Message) ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	public Socket getS() {
		return s;
	}
 
	public void setS(Socket s) {
		this.s = s;
	}
}
