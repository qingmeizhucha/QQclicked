/*
 * ������
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
	JLabel QQname, password, repassword, birthday, sex, phone, title, realname;// �����û����Ա�ǩ
	JButton reg;// ע�ᰴť
	JPasswordField passtext, repasstext;// ���������
	JTextField QQnametext, phonetext, titletext, realnametext;// ʣ����Ҫ���ı���
	JRadioButton man, woman;// �Ա�ѡ��
	JComboBox year, mon, day;// ���յ�����������
	Socket s;// ��������

	public Registered() {
		// TODO Auto-generated constructor stub
		/** ���������� **/
		this.setTitle("ע��QQ");
		this.setSize(250, 350);
		this.setLocation(497, 342);
		this.setVisible(true);
		this.setLayout(null);
		/** QQ�ǳ������������ **/
		QQname = new JLabel(" QQ�ǳ�");
		QQname.setBounds(20, 10, 50, 30);
		QQnametext = new JTextField();
		QQnametext.setBounds(80, 15, 130, 20);
		this.add(QQname);
		this.add(QQnametext);
		/********************/

		/** QQ�����ǩ���ظ������ǩ�������� **/
		password = new JLabel(" QQ����");
		passtext = new JPasswordField();
		repassword = new JLabel("�ظ�����");
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
		/** �������� **/
		birthday = new JLabel("��������");
		birthday.setBounds(20, 100, 55, 30);
		this.add(birthday);
		/* �������� */
		Vector<String> yy = new Vector<String>();
		for (int i = 1990; i <= 2019; i++) {
			yy.add(Integer.toString(i));
		}
		year = new JComboBox(yy);
		year.setBounds(80, 105, 51, 20);
		year.setBorder(null);// �����ޱ߿�
		year.setEditable(true);// ����������ɱ༭
		this.add(year);
		/*******************/
		/* �������� */
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
		mon.setBorder(null);// �����ޱ߿�
		mon.setEditable(true);// ����������ɱ༭
		this.add(mon);
		/*****************/

		/* �������� */
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
		day.setBorder(null);// �����ޱ߿�
		day.setEditable(true);// ����������ɱ༭
		this.add(day);
		/***************/
		/* �Ա�ѡ�� */
		sex = new JLabel("��	        ��");
		sex.setBounds(20, 130, 55, 30);
		man = new JRadioButton("��");
		man.setSelected(true);
		woman = new JRadioButton("Ů");
		man.setBounds(78, 130, 75, 30);
		woman.setBounds(150, 130, 75, 30);
		ButtonGroup sexgrip = new ButtonGroup();// �����Ա��飬��ֻ֤��ѡһ��
		sexgrip.add(man);
		sexgrip.add(woman);
		this.add(sex);
		this.add(man);
		this.add(woman);
		/*******************/
		/* ���� */
		realname = new JLabel("��ʵ����");
		realname.setBounds(20, 160, 55, 30);
		this.add(realname);
		realnametext = new JTextField();
		realnametext.setBounds(80, 165, 130, 20);
		this.add(realnametext);
		/*************/

		/* ���õ绰 */
		phone = new JLabel("��        ��");
		phone.setBounds(20, 190, 55, 30);
		phonetext = new JTextField();
		phonetext.setBounds(80, 193, 130, 20);
		this.add(phone);
		this.add(phonetext);
		/* ���ø���ǩ�� */
		title = new JLabel("����ǩ��");
		title.setBounds(20, 220, 55, 30);
		titletext = new JTextField();
		titletext.setBounds(80, 223, 130, 20);
		this.add(title);
		this.add(titletext);

		/** ע�ᰴť **/
		reg = new JButton("ע    ��");
		reg.setBounds(80, 250, 100, 30);
		reg.addActionListener(this);
		this.add(reg);
		this.repaint();
		this.validate();

	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == reg) {//���ע�ᰴť
			String s1 = passtext.getText();
			String s2 = repasstext.getText();
			if (!s1.equals(s2)) {
				Container container = new Container();
				JOptionPane.showOptionDialog(container, "�������벻һ��", "ʧ��",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
			} else {
				String pass = passtext.getText();// ��ȡ����
				String name = QQnametext.getText();// ��ȡ�ǳ�
				String realname = realnametext.getText();// ��ȡ��ʵ����
				String sex;// ��ȡ�Ա�
				if (man.isSelected()) {
					sex = "��";
				} else {
					sex = "Ů";
				}
				String year1 = (String) year.getSelectedItem();
				String mon1 = (String) mon.getSelectedItem();
				String day1 = (String) day.getSelectedItem();
				String dat = year1 + "-" + mon1 + "-" + day1;
				String ph = phonetext.getText();// ��ȡ�ֻ���
				String ti = titletext.getText();// ��ȡ����ǩ��
				int re = 0;// ��ס����
				int au = 0;// �Զ���¼
				/* ��ע����Ϣ��ӵ�bean */
				UserBean reb = new UserBean();
				reb.setPass(pass);
				reb.setNickname(name);
				reb.setSex(sex);
				reb.setBir(dat);
				reb.setTitle(ti);
				reb.setIcon("image//HeadImage.png");
				reb.setName(realname);
				reb.setTel(ph);
				//�����µ�Message��
				Message sendRegMessage = new Message();
				sendRegMessage.setTcp_ip(2);//��ʶ��Ϊ2��ʶע����Ϣ
				sendRegMessage.setUb(reb);
				try {
					Message m1 = new LoginPanduan().SendMessage(sendRegMessage);
					if (m1.getTcp_ip() == 3) {// ע��ɹ�
						new OptionPane().GetQQSucceesed(m1.getUb().getId(), "��ϲ");
					} else if (m1.getTcp_ip() == 2) {// ע��ʧ��
						new OptionPane().GetQQFail("ע��ʧ��", "δ֪����");
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}

	}

}
