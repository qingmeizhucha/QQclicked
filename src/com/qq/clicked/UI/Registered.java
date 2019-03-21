/*
 * 主界面
 */
package com.qq.clicked.UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

import com.qq.clicked.tools.LoginPanduan;

import Message.Message;
import Message.UserBean;

public class Registered extends JFrame implements ActionListener {
	JLabel QQname, password, repassword, birthday, sex, phone, title, realname;// 所有用户属性标签
	JButton reg;// 注册按钮
	JPasswordField passtext, repasstext;// 两次密码框
	JTextField QQnametext, phonetext, titletext, realnametext;// 剩余需要的文本框
	JRadioButton man, woman;// 性别复选框；
	JComboBox year, mon, day;// 生日的三个下拉框
	Socket s;// 网络连接

	public Registered() {
		// TODO Auto-generated constructor stub
		/** 主界面设置 **/
		this.setTitle("注册QQ");
		this.setSize(250, 350);
		this.setLocation(497, 342);
		this.setVisible(true);
		this.setLayout(null);
		/** QQ昵称与输入框设置 **/
		QQname = new JLabel(" QQ昵称");
		QQname.setBounds(20, 10, 50, 30);
		QQnametext = new JTextField();
		QQnametext.setBounds(80, 15, 130, 20);
		this.add(QQname);
		this.add(QQnametext);
		/********************/

		/** QQ密码标签与重复密码标签，框设置 **/
		password = new JLabel(" QQ密码");
		passtext = new JPasswordField();
		repassword = new JLabel("重复密码");
		repasstext = new JPasswordField();
		password.setBounds(20, 40, 50, 30);
		passtext.setBounds(80, 45, 130, 20);
		repassword.setBounds(20, 70, 55, 30);
		repasstext.setBounds(80, 75, 130, 20);
		this.add(password);
		this.add(passtext);
		this.add(repassword);
		this.add(repasstext);
		/************************/
		/** 生日设置 **/
		birthday = new JLabel("设置生日");
		birthday.setBounds(20, 100, 55, 30);
		this.add(birthday);
		/* 年下拉框 */
		Vector<String> yy = new Vector<String>();
		for (int i = 1990; i <= 2019; i++) {
			yy.add(Integer.toString(i));
		}
		year = new JComboBox(yy);
		year.setBounds(80, 105, 51, 20);
		year.setBorder(null);// 设置无边框
		year.setEditable(true);// 设置下拉框可编辑
		this.add(year);
		/*******************/
		/* 月下拉框 */
		Vector<String> mm = new Vector<String>();
		for (int i = 1; i <= 12; i++) {
			String s1 = "";
			if (i < 10) {
				s1 = '0' + Integer.toString(i);
			} else {
				s1 = Integer.toString(i);
			}
			mm.add(s1);
		}
		mon = new JComboBox(mm);
		mon.setBounds(131, 105, 40, 20);
		mon.setBorder(null);// 设置无边框
		mon.setEditable(true);// 设置下拉框可编辑
		this.add(mon);
		/*****************/

		/* 日下拉框 */
		Vector<String> dd = new Vector<String>();
		for (int i = 1; i <= 31; i++) {
			String s1 = "";
			if (i < 10) {
				s1 = '0' + Integer.toString(i);
			} else {
				s1 = Integer.toString(i);
			}
			dd.add(s1);
		}
		day = new JComboBox(dd);
		day.setBounds(171, 105, 40, 20);
		day.setBorder(null);// 设置无边框
		day.setEditable(true);// 设置下拉框可编辑
		this.add(day);
		/***************/
		/* 性别单选框 */
		sex = new JLabel("性	        别");
		sex.setBounds(20, 130, 55, 30);
		man = new JRadioButton("男");
		man.setSelected(true);
		woman = new JRadioButton("女");
		man.setBounds(78, 130, 75, 30);
		woman.setBounds(150, 130, 75, 30);
		ButtonGroup sexgrip = new ButtonGroup();// 设置性别组，保证只能选一个
		sexgrip.add(man);
		sexgrip.add(woman);
		this.add(sex);
		this.add(man);
		this.add(woman);
		/*******************/
		/* 姓名 */
		realname = new JLabel("真实姓名");
		realname.setBounds(20, 160, 55, 30);
		this.add(realname);
		realnametext = new JTextField();
		realnametext.setBounds(80, 165, 130, 20);
		this.add(realnametext);
		/*************/

		/* 设置电话 */
		phone = new JLabel("电        话");
		phone.setBounds(20, 190, 55, 30);
		phonetext = new JTextField();
		phonetext.setBounds(80, 193, 130, 20);
		this.add(phone);
		this.add(phonetext);
		/* 设置个性签名 */
		title = new JLabel("个性签名");
		title.setBounds(20, 220, 55, 30);
		titletext = new JTextField();
		titletext.setBounds(80, 223, 130, 20);
		this.add(title);
		this.add(titletext);

		/** 注册按钮 **/
		reg = new JButton("注    册");
		reg.setBounds(80, 250, 100, 30);
		reg.addActionListener(this);
		this.add(reg);
		this.repaint();
		this.validate();

	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == reg) {//点击注册按钮
			String s1 = passtext.getText();
			String s2 = repasstext.getText();
			if (!s1.equals(s2)) {
				Container container = new Container();
				JOptionPane.showOptionDialog(container, "两次密码不一致", "失败",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
			} else {
				String pass = passtext.getText();// 获取密码
				String name = QQnametext.getText();// 获取昵称
				String realname = realnametext.getText();// 获取真实姓名
				String sex;// 获取性别
				if (man.isSelected()) {
					sex = "男";
				} else {
					sex = "女";
				}
				String year1 = (String) year.getSelectedItem();
				String mon1 = (String) mon.getSelectedItem();
				String day1 = (String) day.getSelectedItem();
				String dat = year1 + "-" + mon1 + "-" + day1;
				String ph = phonetext.getText();// 获取手机号
				String ti = titletext.getText();// 获取个性签名
				int re = 0;// 记住密码
				int au = 0;// 自动登录
				/* 将注册信息添加到bean */
				UserBean reb = new UserBean();
				reb.setPass(pass);
				reb.setNickname(name);
				reb.setSex(sex);
				reb.setBir(dat);
				reb.setTitle(ti);
				reb.setIcon("image//HeadImage.png");
				reb.setName(realname);
				reb.setTel(ph);
				//创建新的Message类
				Message sendRegMessage = new Message();
				sendRegMessage.setTcp_ip(2);//标识设为2标识注册信息
				sendRegMessage.setUb(reb);
				try {
					Message m1 = new LoginPanduan().SendMessage(sendRegMessage);
					if (m1.getTcp_ip() == 3) {// 注册成功
						new OptionPane().GetQQSucceesed(m1.getUb().getId(), "恭喜");
					} else if (m1.getTcp_ip() == 2) {// 注册失败
						new OptionPane().GetQQFail("注册失败", "未知错误");
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}

	}

}
