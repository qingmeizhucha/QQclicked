package com.qq.clicked.UI;

import java.awt.*;
import java.awt.Color;

import javax.swing.*;

public class SetUserInfo {
	JFrame mainjf;
	JLabel nickname,sex,password,repass,bir,title,head,realnam,phone;
	JTextField nickt,sext,biry,birm,bird,headt,realt,titlet,pt;
	JPasswordField passt,repasst;
	JButton save,ret;
	
	public SetUserInfo(String myid) {
		// TODO Auto-generated constructor stub
		mainjf = new JFrame();
		mainjf.setSize(472, 740);
		mainjf.getContentPane().setBackground(new Color(255,255,255));
		mainjf.setLayout(null);
		mainjf.setTitle("我的资料");
		/*****昵称*****/
		nickname = new JLabel("昵 称:");
		nickname.setFont(new Font("仿宋",Font.PLAIN,18 ));
		nickname.setBounds(5, 10, 70,30);
		mainjf.add(nickname);
		nickt = new JTextField(50);
		nickt.setBounds(80, 13, 120,25);
		//nickt.setOpaque(false);
		mainjf.add(nickt);
		/****************/
		
		
		
		
		mainjf.setVisible(true);
	}
}
