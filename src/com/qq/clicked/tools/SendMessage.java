package com.qq.clicked.tools;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Message.Message;

public class SendMessage {
	public Message SendMessage(String myid,Message me) throws ClassNotFoundException {
		Socket s;//�½�һ������
		Message mm = null;
		try {
			s = new Socket("localhost", 9999);//�½�һ��׼�����ӵ�Socket
			ObjectOutputStream oos = new ObjectOutputStream(s//�½�һ���������������������ļ�
					.getOutputStream());// �½������
			oos.writeObject(me);// ����������
			ObjectInputStream ois = new ObjectInputStream(s
					.getInputStream());
			mm = (Message) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
		return mm;
	}
}
