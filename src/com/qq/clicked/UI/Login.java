/*
 * ��¼����
 */
package com.qq.clicked.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.naming.spi.DirectoryManager;
import javax.swing.*;

import Message.Message;
import Message.UserBean;
import Therd.ClickTherd;
import Therd.ManageClickTherd;
import Therd.QQbuddyManage;

import com.qq.clicked.dao.AddFriend;
import com.qq.clicked.tools.*;

public class Login extends JFrame {
	JPanel pnor, pcen, pwe;
	JButton login;// ��¼��ť
	JComboBox ID;// �˺�ID
	JPasswordField pass;// ����
	JLabel reg;// ע���˺�
	JLabel find;// �һ�����
	JCheckBox reme, auto;// �Զ���¼����ס����
	JLabel head;// ͷ��
	BorderLayout b1;
	JLabel close, min, set;
	private JPanel jpin;
	private Image image2;
	private JButton jcoc;
	JPopupMenu pop;
	ImageIcon away;
	ImageIcon busy;
	ImageIcon hide;
	ImageIcon not;
	ImageIcon online;
	ImageIcon Qme;
	int xold = 0, yold = 0;

	public Login() throws IOException {// ���췽��
		// TODO Auto-generated constructor stub
		b1 = new BorderLayout();
		this.setSize(430, 330);
		this.setLayout(b1);
		// ��ӱ���ͼƬ
		ImageIcon back = new ImageIcon("image//back.gif");
		JLabel l1 = new JLabel(back);
		l1.setBounds(0, 0, back.getIconWidth(), back.getIconHeight());
		this.getLayeredPane().add(l1, new Integer(Integer.MIN_VALUE));
		JPanel con = (JPanel) this.getContentPane();
		con.setOpaque(false);
		/** ��Ӹ�������� **/
		/**
		 * �������� ��С����ť �رհ�ť ���ð�ť
		 */
		pnor = creatPnor();
		this.add(pnor, b1.NORTH);
		/**
		 * �м���� �˺������ ������ǩ ������ѡ�� ��¼��ť
		 */
		pcen = creatPcen();
		this.add(pcen, b1.CENTER);
		/**
		 * ������� ͷ��� ��¼״̬
		 */
		pwe = creatPwe();
		this.add(pwe, b1.WEST);
		/**************/

		// ���ÿɼ����ޱ߿��
		this.setBackground(new Color(255, 255, 255));
		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);
		Graphics g = jpin.getGraphics();
		this.setLocationRelativeTo(null);
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				int xOn = e.getXOnScreen();
				int yOn = e.getYOnScreen();
				int xnow = xold - xOn;
				int ynow = yold - yOn;
				Login.this.setLocation(xOn, yOn);
			}
		});
	}

	private JPanel creatPwe() {
		// TODO Auto-generated method stub
		/*
		 * ʵ����״̬�����˵�
		 */
		creatpop();

		// ʵ����һ��JPanel��Ķ���
		JPanel PanelWest = new JPanel();
		// ����������������Ĵ�С
		PanelWest.setPreferredSize(new Dimension(140, 0));
		// �����������Ĳ��ַ�ʽΪ��ʽ����
		PanelWest.setLayout((LayoutManager) new FlowLayout(FlowLayout.RIGHT));
		// ʵ����һ��ImageIcon��Ķ���
		ImageIcon imageWest = new ImageIcon("HeadImage//HeadImage.png");
		image2 = imageWest.getImage();

		jpin = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(image2, 0, 0, null);
			}
		};
		jpin.setPreferredSize(new Dimension(82, 82));
		jpin.setLayout(null);
		jpin.setOpaque(false);
		// ����ǩ������ӵ������
		// PanelWest.add(jlaWest);
		// ʵ����һ��JButton��Ķ���
		jcoc = new JButton();
		jcoc.setIcon(online);
		jcoc.setBounds(64, 64, 13, 13);
		jcoc.setFocusPainted(false);
		jcoc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pop.show(jcoc, 13, 13);
			}
		});

		// �����������͸��
		PanelWest.setOpaque(false);
		PanelWest.add(jpin);
		jpin.add(jcoc);

		// ���ش����õ��������
		return PanelWest;
	}

	private void creatpop() {
		// TODO Auto-generated method stub
		busy = new ImageIcon("image//state_busy.png");
		hide = new ImageIcon("image//state_hide.png");
		not = new ImageIcon("image//state_notdisturb.png");
		online = new ImageIcon("image//state_online.png");
		away = new ImageIcon("image//state_away.png");
		Qme = new ImageIcon("image//state_Qme.png");
		JMenuItem busym = new JMenuItem("æµ", busy);
		busym.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(busy);
			}
		});
		JMenuItem hidem = new JMenuItem("����", hide);
		hidem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(hide);
			}
		});
		JMenuItem notm = new JMenuItem("�������", not);
		notm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(not);
			}
		});
		JMenuItem onlinem = new JMenuItem("��������", online);
		onlinem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(online);
			}
		});
		JMenuItem awaym = new JMenuItem("�뿪", away);
		awaym.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(away);
			}
		});
		JMenuItem Qmem = new JMenuItem("Q�Ұ�", Qme);
		Qmem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(Qme);
			}
		});
		pop = new JPopupMenu();
		pop.add(busym);
		pop.add(hidem);
		pop.add(notm);
		pop.add(onlinem);
		pop.add(awaym);
		pop.add(Qmem);

	}

	private JPanel creatPcen() {
		// TODO Auto-generated method stub
		JPanel pc = new JPanel();
		pc.setLayout(null);
		pc.setPreferredSize(new Dimension(280, 0));
		// �����˺Ÿ�ѡ��
		Vector<String> allid = new Vector<String>();// ��ȡ������½����ID
		ID = new JComboBox(allid);
		ID.setBorder(null);
		ID.setEditable(true);
		ID.setBounds(10, 8, 170, 25);
		pc.add(ID);
		/************/
		// ���������
		pass = new JPasswordField();
		pass.setBounds(10, 33, 170, 25);
		pass.setOpaque(false);
		pc.add(pass);
		// ��ѡ��
		// ��ס����
		reme = new JCheckBox("��ס����");
		reme.setFocusPainted(false);
		reme.setOpaque(false);
		reme.setFont(new Font("����", 0, 13));// ����������ʽ
		reme.setBounds(10, 60, 80, 25);
		pc.add(reme);
		// �Զ���¼
		auto = new JCheckBox("�Զ���¼");
		auto.setFocusPainted(false);
		auto.setOpaque(false);
		auto.setFont(new Font("����", 0, 13));// ����������ʽ
		auto.setBounds(110, 60, 80, 25);
		pc.add(auto);

		// ע���˺�
		reg = new JLabel("ע���˺�");
		reg.setBounds(200, 8, 80, 25);
		reg.setFont(new Font("����", 0, 14));// ��ʼ��ע���˺�������ʽ
		reg.setForeground(new Color(114, 184, 254));
		reg.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				reg.setForeground(Color.red);
			}

			public void mouseExited(MouseEvent e) {// ����뿪
				// TODO Auto-generated method stub
				reg.setForeground(new Color(114, 184, 254));
			}

			public void mouseClicked(MouseEvent e) {
				new Registered();
			}
		});
		pc.add(reg);
		// �һ�����
		find = new JLabel("�һ�����");
		find.setBounds(200, 33, 80, 25);
		find.setFont(new Font("����", 0, 14));// ��ʼ��ע���˺�������ʽ
		find.setForeground(new Color(114, 184, 254));
		find.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				find.setForeground(Color.red);
			}

			public void mouseExited(MouseEvent e) {// ����뿪
				// TODO Auto-generated method stub
				find.setForeground(new Color(114, 184, 254));
			}

			public void mouseClicked(MouseEvent e) {

			}
		});
		pc.add(find);
		// ��¼��ť
		login = new JButton("��   ¼");
		login.setBounds(10, 95, 170, 30);
		login.setFont(new Font("����", 0, 14));
		login.setBorder(new RoundBorder());
		login.setBackground(new Color(5, 186, 251));
		login.setForeground(new Color(255, 255, 255));
		login.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserBean log = new UserBean();
				Message mm = new Message();
				log.setId((String) ID.getSelectedItem());
				log.setPass(pass.getText());
				mm.setUb(log);
				mm.setTcp_ip(1);
				try {
					mm = new LoginPanduan().SendMessage(mm);
					if (mm.getTcp_ip() == 4) {
						QQbuddy qqb = new QQbuddy((String) ID.getSelectedItem());
						QQbuddyManage.add((String) ID.getSelectedItem(), qqb);
						dispose();
					}else if(mm.getTcp_ip() == 0){
						new OptionPane().GetQQFail("���˺��ѵ�¼", "��¼ʧ��");
					}else {
						new OptionPane().GetQQFail("�˺Ż��������", "��¼ʧ��");
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		pc.add(login);
		pc.setOpaque(false);
		return pc;
	}

	private JPanel creatPnor() throws IOException {// �������
		// TODO Auto-generated method stub
		JPanel pn = new JPanel();
		pn.setLayout(null);
		pn.setPreferredSize(new Dimension(0, 180));
		// �����������ð�ť
		String setpath = "image/arrowback.png";
		set = new SetJLabel().GetJLabel(setpath, 0, 0, 30, 30);
		set.setBounds(340, 0, 30, 30);
		set.setToolTipText("����");
		set.addMouseListener(new MouseAdapter() {// ���õ������
					public void mouseClicked(MouseEvent e) {
						// setExtendedState(JFrame.ICONIFIED);
					}
				});
		// ������С��
		String minpath = "image/minback.png";
		min = new SetJLabel().GetJLabel(minpath, 0, 0, 30, 30);
		min.setBounds(370, 0, 30, 30);
		min.setToolTipText("��С��");
		min.addMouseListener(new MouseAdapter() {// ������С��
					public void mouseClicked(MouseEvent e) {
						setExtendedState(JFrame.ICONIFIED);
					}
				});
		// ���ùر�
		String closepath = "image/closeback.png";
		close = new SetJLabel().GetJLabel(closepath, 0, 0, 30, 30);
		close.setBounds(400, 0, 30, 30);
		close.setToolTipText("�ر�");
		close.addMouseListener(new MouseAdapter() {// ���ùر�
					public void mouseClicked(MouseEvent e) {
						System.exit(0);
					}
				});
		pn.add(set);
		pn.add(min);
		pn.add(close);
		pn.setOpaque(false);
		return pn;

	} 
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		 new Login();
		// new Registered();
		// new QQbuddy("398353879");
		 //new SetUserInfo("398353879");
		//new TalkUI("","");
		//new AddFriend("398353879");
	}

}
