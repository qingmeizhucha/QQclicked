/*
 * 登录界面
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
	JButton login;// 登录按钮
	JComboBox ID;// 账号ID
	JPasswordField pass;// 密码
	JLabel reg;// 注册账号
	JLabel find;// 找回密码
	JCheckBox reme, auto;// 自动登录，记住密码
	JLabel head;// 头像
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

	public Login() throws IOException {// 构造方法
		// TODO Auto-generated constructor stub
		b1 = new BorderLayout();
		this.setSize(430, 330);
		this.setLayout(b1);
		// 添加背景图片
		ImageIcon back = new ImageIcon("image//back.gif");
		JLabel l1 = new JLabel(back);
		l1.setBounds(0, 0, back.getIconWidth(), back.getIconHeight());
		this.getLayeredPane().add(l1, new Integer(Integer.MIN_VALUE));
		JPanel con = (JPanel) this.getContentPane();
		con.setOpaque(false);
		/** 添加各部分面板 **/
		/**
		 * 北部包含 最小化按钮 关闭按钮 设置按钮
		 */
		pnor = creatPnor();
		this.add(pnor, b1.NORTH);
		/**
		 * 中间面板 账号密码框 两个标签 两个复选框 登录按钮
		 */
		pcen = creatPcen();
		this.add(pcen, b1.CENTER);
		/**
		 * 西部面板 头像框 登录状态
		 */
		pwe = creatPwe();
		this.add(pwe, b1.WEST);
		/**************/

		// 设置可见，无边框等
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
		 * 实例化状态弹出菜单
		 */
		creatpop();

		// 实例化一个JPanel类的对象
		JPanel PanelWest = new JPanel();
		// 设置西边面板容器的大小
		PanelWest.setPreferredSize(new Dimension(140, 0));
		// 设置西边面板的布局方式为流式布局
		PanelWest.setLayout((LayoutManager) new FlowLayout(FlowLayout.RIGHT));
		// 实例化一个ImageIcon类的对象
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
		// 将标签对象添加到面板上
		// PanelWest.add(jlaWest);
		// 实例化一个JButton类的对象
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

		// 设置西边面板透明
		PanelWest.setOpaque(false);
		PanelWest.add(jpin);
		jpin.add(jcoc);

		// 返回创建好的西边面板
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
		JMenuItem busym = new JMenuItem("忙碌", busy);
		busym.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(busy);
			}
		});
		JMenuItem hidem = new JMenuItem("隐身", hide);
		hidem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(hide);
			}
		});
		JMenuItem notm = new JMenuItem("请勿打扰", not);
		notm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(not);
			}
		});
		JMenuItem onlinem = new JMenuItem("我在线上", online);
		onlinem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(online);
			}
		});
		JMenuItem awaym = new JMenuItem("离开", away);
		awaym.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(away);
			}
		});
		JMenuItem Qmem = new JMenuItem("Q我吧", Qme);
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
		// 设置账号复选框
		Vector<String> allid = new Vector<String>();// 获取本机登陆过的ID
		ID = new JComboBox(allid);
		ID.setBorder(null);
		ID.setEditable(true);
		ID.setBounds(10, 8, 170, 25);
		pc.add(ID);
		/************/
		// 设置密码框
		pass = new JPasswordField();
		pass.setBounds(10, 33, 170, 25);
		pass.setOpaque(false);
		pc.add(pass);
		// 复选框
		// 记住密码
		reme = new JCheckBox("记住密码");
		reme.setFocusPainted(false);
		reme.setOpaque(false);
		reme.setFont(new Font("宋体", 0, 13));// 设置字体样式
		reme.setBounds(10, 60, 80, 25);
		pc.add(reme);
		// 自动登录
		auto = new JCheckBox("自动登录");
		auto.setFocusPainted(false);
		auto.setOpaque(false);
		auto.setFont(new Font("宋体", 0, 13));// 设置字体样式
		auto.setBounds(110, 60, 80, 25);
		pc.add(auto);

		// 注册账号
		reg = new JLabel("注册账号");
		reg.setBounds(200, 8, 80, 25);
		reg.setFont(new Font("黑体", 0, 14));// 初始化注册账号字体样式
		reg.setForeground(new Color(114, 184, 254));
		reg.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				reg.setForeground(Color.red);
			}

			public void mouseExited(MouseEvent e) {// 鼠标离开
				// TODO Auto-generated method stub
				reg.setForeground(new Color(114, 184, 254));
			}

			public void mouseClicked(MouseEvent e) {
				new Registered();
			}
		});
		pc.add(reg);
		// 找回密码
		find = new JLabel("找回密码");
		find.setBounds(200, 33, 80, 25);
		find.setFont(new Font("黑体", 0, 14));// 初始化注册账号字体样式
		find.setForeground(new Color(114, 184, 254));
		find.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				find.setForeground(Color.red);
			}

			public void mouseExited(MouseEvent e) {// 鼠标离开
				// TODO Auto-generated method stub
				find.setForeground(new Color(114, 184, 254));
			}

			public void mouseClicked(MouseEvent e) {

			}
		});
		pc.add(find);
		// 登录按钮
		login = new JButton("登   录");
		login.setBounds(10, 95, 170, 30);
		login.setFont(new Font("黑体", 0, 14));
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
						new OptionPane().GetQQFail("该账号已登录", "登录失败");
					}else {
						new OptionPane().GetQQFail("账号或密码错误", "登录失败");
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

	private JPanel creatPnor() throws IOException {// 北部面板
		// TODO Auto-generated method stub
		JPanel pn = new JPanel();
		pn.setLayout(null);
		pn.setPreferredSize(new Dimension(0, 180));
		// 设置属性设置按钮
		String setpath = "image/arrowback.png";
		set = new SetJLabel().GetJLabel(setpath, 0, 0, 30, 30);
		set.setBounds(340, 0, 30, 30);
		set.setToolTipText("设置");
		set.addMouseListener(new MouseAdapter() {// 设置点击监听
					public void mouseClicked(MouseEvent e) {
						// setExtendedState(JFrame.ICONIFIED);
					}
				});
		// 设置最小化
		String minpath = "image/minback.png";
		min = new SetJLabel().GetJLabel(minpath, 0, 0, 30, 30);
		min.setBounds(370, 0, 30, 30);
		min.setToolTipText("最小化");
		min.addMouseListener(new MouseAdapter() {// 设置最小化
					public void mouseClicked(MouseEvent e) {
						setExtendedState(JFrame.ICONIFIED);
					}
				});
		// 设置关闭
		String closepath = "image/closeback.png";
		close = new SetJLabel().GetJLabel(closepath, 0, 0, 30, 30);
		close.setBounds(400, 0, 30, 30);
		close.setToolTipText("关闭");
		close.addMouseListener(new MouseAdapter() {// 设置关闭
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
