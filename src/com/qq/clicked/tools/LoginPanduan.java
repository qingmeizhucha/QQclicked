package com.qq.clicked.tools;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Message.Message;
import Therd.ClickTherd;
import Therd.ManageClickTherd;

public class LoginPanduan {
	public Message SendMessage(Message me) throws ClassNotFoundException {
		Socket s = null;//�½�һ������
		Message m1 = null;//��Ҫ���͵�����
		try {
			s = new Socket("localhost", 9999);//�½�һ��׼�����ӵ�Socket
			ObjectOutputStream oos = new ObjectOutputStream(s//�½�һ���������������������ļ�
					.getOutputStream());// �½������
			oos.writeObject(me);// ����������
			ObjectInputStream ois = new ObjectInputStream(s
					.getInputStream());// �½�������
			m1 = (Message) ois.readObject();// ��÷������ķ�����Ϣ
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(m1.getTcp_ip() == 4){//�����¼�ɹ�
			//�����¼�ɹ��������̹߳����������һ���̣߳����ֺͷ�����������
			ClickTherd ss = new ClickTherd(s);
			ss.start();
			ManageClickTherd.add(m1.getUb().getId(), ss);
		}
		
		return m1;
		// TODO Auto-generated constructor stub
	}
}
