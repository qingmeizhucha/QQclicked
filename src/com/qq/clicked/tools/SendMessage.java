package com.qq.clicked.tools;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Message.Message;

public class SendMessage {
	public Message SendMessage(String myid,Message me) throws ClassNotFoundException {
		Socket s;//新建一个连接
		Message mm = null;
		try {
			s = new Socket("localhost", 9999);//新建一个准备连接的Socket
			ObjectOutputStream oos = new ObjectOutputStream(s//新建一个输出流，向服务器发送文件
					.getOutputStream());// 新建输出流
			oos.writeObject(me);// 发给服务器
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
