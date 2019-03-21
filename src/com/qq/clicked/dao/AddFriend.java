package com.qq.clicked.dao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Message.*;

import com.qq.clicked.tools.SendMessage;
import com.sun.xml.internal.ws.api.server.Container;


public class AddFriend {
	String myid;
	String friendid;
	JFrame jf;
	JPanel pcen,pnor;
	BorderLayout b1;
	JTextField idtext;
	JLabel head;
	JTextField friname,frititle;
	JButton addFri;
	JButton find;
	boolean res = false;//判断是否查找到好友
	public AddFriend(String id) {
		// TODO Auto-generated constructor stub
		myid = id;
		/*初始化所有组件*/
		idtext = new JTextField();
		head = new JLabel();
		friname = new JTextField();
		frititle = new JTextField();
		addFri = new JButton();
		/***************/
		b1 = new BorderLayout();
		jf = new JFrame();
		jf.setTitle("添加好友");
		jf.setLayout(b1);
		jf.setBackground(new Color(255,255,255));
		JPanel con = (JPanel) jf.getContentPane();
		con.setOpaque(false);
		jf.setSize(500,300);
		jf.setLocationRelativeTo(null);
		jf.setLayout(b1);
		creatPnor();
		jf.add(pnor,b1.NORTH);
		creatPcen();
		jf.add(pcen,b1.CENTER);
		jf.setVisible(true);
		
	}
	private void creatPcen() {//查找到的好友信息
		// TODO Auto-generated method stub
		pcen = new JPanel();
		pcen.setLayout(null);
		head.setOpaque(true);
		head.setBackground(new Color(255,255,255));
		head.setBounds(20, 20,80,80);
		pcen.add(head);
		
		friname.setBounds(105, 20, 130, 20);
		friname.setBorder(new EmptyBorder(0, 0, 0, 0));
		//friname.setOpaque(false);
		pcen.add(friname);
		
		frititle.setBounds(105, 50, 130, 20);
		frititle.setBorder(new EmptyBorder(0, 0, 0, 0));
		//frititle.setOpaque(false);
		pcen.add(frititle);
		
		addFri.setText("加为好友");
		addFri.setBounds(105, 75,130,25);
		pcen.add(addFri);
		
		//点击加为好友
		addFri.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(res == false){
					JOptionPane.showOptionDialog(jf,"该账号不存在或输入非法",
							"查找失败", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE,null,null,null);
				}else{
					JOptionPane.showOptionDialog(jf,"已经发送申请，等待对方同意",
							"发送成功", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE,null,null,null);
					Message mm = new Message();
					UserBean ub = new UserBean();
					ub.setId(myid);
					ub.setName(friendid);
					mm.setTcp_ip(16);
					mm.setUb(ub);
					try {
						mm = new SendMessage().SendMessage(myid, mm);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
	}
	private void creatPnor() {
		// TODO Auto-generated method stub
		pnor = new JPanel();
		pnor.setLayout(null);
		pnor.setOpaque(false);
		pnor.setPreferredSize(new Dimension(500,50));
		JLabel QQid = new JLabel("输入账号:");
		QQid.setBounds(30, 10,70,30);
		pnor.add(QQid);
		idtext.setBounds(105, 15,150,20);
		pnor.add(idtext);
		find = new JButton("查找");
		find.setBounds(270, 12, 70,25);
		find.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				friendid = idtext.getText();
				try {
					setFriInfo(friendid);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//设置查找到的好友信息
			}
		});
		pnor.add(find);
	}
	public void setFriInfo(String id) throws ClassNotFoundException{
		Message mm = new Message();
		UserBean ub = new UserBean();
		ub.setId(id);
		mm.setTcp_ip(8);
		mm.setUb(ub);
		mm = new SendMessage().SendMessage(myid, mm);
		if(mm.getUb().getId().equals("!!")){
			JOptionPane.showOptionDialog(jf,"该账号不存在或输入非法","查找失败", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
			res = false;
		}else{
			res = true;
			ImageIcon im = new ImageIcon(mm.getUb().getIcon());
			head.setIcon(im);
			friname.setText(mm.getUb().getName());
			frititle.setText(mm.getUb().getTitle());
			pnor.validate();
		}
	}

}
