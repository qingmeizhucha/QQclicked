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
		Socket s = null;//新建一个连接
		Message m1 = null;//需要发送的内容
		try {
			s = new Socket("localhost", 9999);//新建一个准备连接的Socket
			ObjectOutputStream oos = new ObjectOutputStream(s//新建一个输出流，向服务器发送文件
					.getOutputStream());// 新建输出流
			oos.writeObject(me);// 发给服务器
			ObjectInputStream ois = new ObjectInputStream(s
					.getInputStream());// 新建输入流
			m1 = (Message) ois.readObject();// 获得服务器的反馈消息
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(m1.getTcp_ip() == 4){//如果登录成功
			//如果登录成功，就向线程管理器中添加一个线程，保持和服务器的连接
			ClickTherd ss = new ClickTherd(s);
			ss.start();
			ManageClickTherd.add(m1.getUb().getId(), ss);
		}
		
		return m1;
		// TODO Auto-generated constructor stub
	}
}
