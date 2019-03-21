package com.qq.clicked.dao;

import java.util.ArrayList;
import java.util.Map;

import com.qq.clicked.tools.SendMessage;
import Message.Message;
import Message.UserBean;

public class GetFriend {
	public Map<String ,Object> GetFriend(String id) throws ClassNotFoundException{
		Map<String ,Object> Fri = null;
		UserBean user = new UserBean();
		Message me = new Message();
		user.setId(id);
		me.setTcp_ip(6);
		me.setUb(user);
		Fri = new SendMessage().SendMessage(id,me).getUb().getFriend();
		return Fri;
		
	}
	public ArrayList<String> getAllGrop(String id) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<String> list = null;
		UserBean us = new UserBean();
		Message m = new Message();
		us.setId(id);
		m.setUb(us);
		m.setTcp_ip(7);
		list = new SendMessage().SendMessage(id,m).getUb().getGrop();
		return list;
	}
}
