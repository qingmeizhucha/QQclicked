/*
 * 登录成功之后的主界面，好友列表界面
 */
package com.qq.clicked.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Message.Message;
import Message.UserBean;
import Therd.ManageTalkUITherd;

import com.qq.clicked.dao.AddFriend;
import com.qq.clicked.dao.FriendModel;
import com.qq.clicked.dao.GetFriend;
import com.qq.clicked.dao.GetUserInfo;
import com.qq.clicked.tools.SendMessage;
import com.qq.clicked.tools.SetJLabel;

public class QQbuddy {
	public static JFrame mainjf;
	Image image2;
	BorderLayout b1;
	CardLayout c1;
	JPanel pnor;
	static JPanel pcen;
	JPanel psou;
	int xold = 0, yold;
	ImageIcon yes, no;
	JPopupMenu pop;
	ImageIcon away;
	ImageIcon busy;
	ImageIcon hide;
	ImageIcon not;
	ImageIcon online;
	ImageIcon Qme;
	JButton jcoc;
	JPanel jpin;
	JPopupMenu fenzumanage;
	static String newid;

	public QQbuddy(String id) throws IOException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		// 设置大小，无布局
		newid = id;
		c1 = new CardLayout();
		yes = new ImageIcon("image//yes.png");
		no = new ImageIcon("image//no.png");
		mainjf = new JFrame();
		b1 = new BorderLayout();
		mainjf.setLayout(b1);
		mainjf.setBackground(new Color(4, 181, 207));
		JPanel contentPanel = (JPanel) mainjf.getContentPane();
		contentPanel.setOpaque(false);
		/*******/
		/* 创建北部面板 */
		creatPnor(id);
		mainjf.add(pnor, BorderLayout.NORTH);
		pcen = creatPcen(id);
		mainjf.add(pcen, BorderLayout.CENTER);
		creatPsou(psou);
		mainjf.add(psou, BorderLayout.SOUTH);

		// 自定义移动方法
		mainjf.setLocation(1300, 200);// 设置出现位置
		mainjf.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				int xOn = e.getXOnScreen();
				int yOn = e.getYOnScreen();
				mainjf.setLocation(xOn, yOn);
			}
		});
		mainjf.setUndecorated(true);
		mainjf.setSize(279, 698);
		mainjf.setVisible(true);
		

	}

	private void creatPsou(JPanel psou2) throws IOException {
		// TODO Auto-generated method stub
		psou = new JPanel();
		psou.setLayout(null);
		psou.setBackground(new Color(255, 255, 255));
		psou.setPreferredSize(new Dimension(279, 52));
		//添加好友标签
		JLabel AddManage;
		String addpath = "image/addf.png";
		AddManage = new SetJLabel().GetJLabel(addpath, 0, 0, 30, 30);
		AddManage.setBounds(0, 0, 30, 30);
		AddManage.setToolTipText("添加好友");
		AddManage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new AddFriend(newid);
			}
		});
		psou.add(AddManage);
		
		//QQ音乐标签
		JLabel music = new SetJLabel().GetJLabel("image/qqmusic.png", 0, 0, 30,
				30);
		music.setBounds(30, 0, 30, 30);
		music.setToolTipText("QQ音乐");
		music.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					Desktop
							.getDesktop()
							.open(
									new File(
											"QQMusic//QQMusic1598.12.03.14//QQMusic.exe"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		psou.add(music);
		
		//消息盒子标签
		final JLabel newMessage = new JLabel();
		final ImageIcon imm = new ImageIcon("image//newMessage.png");
		newMessage.setIcon(imm);
		newMessage.setBounds(60, 0, 30,30);
		psou.add(newMessage);
		
	}

	private JPanel creatPcen(String id) throws ClassNotFoundException,
			IOException {// 中部好友面板
		// TODO Auto-generated method stub
		/*
		 * 好友列表，分组信息
		 */
		final JPanel pcc;
		Map<String, Object> AllFri = null;// 本账号的所有好友ID,分组，备注
		pcc = new JPanel();
		// pcen.setBounds(0, 140,279, 510);
		pcc.setBackground(new Color(255, 255, 255));
		pcc.setPreferredSize(new Dimension(279, 510));
		AllFri = new GetFriend().GetFriend(id);// 获取的所有好友的信息
		final ArrayList<String> allgrop = new GetFriend().getAllGrop(id);// 获取本账号的所有分组
		final JPanel[] pc = new JPanel[allgrop.size() + 1];// 所有的卡片
		JButton[] fb = new JButton[allgrop.size()];// 所有的分组按钮
		/** 初始化第一张卡片->只包含分组的界面 **/
		pc[0] = new JPanel();
		JPanel pp = new JPanel();// 临时存放面板
		if (allgrop.size() < 10) {
			pp.setLayout(new GridLayout(10, 1));
		} else {
			pp.setLayout(new GridLayout(allgrop.size(), 1));
		}
		JScrollPane jsp1 = new JScrollPane();// 存放按钮的滚动面板
		jsp1
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		for (int i = 0; i < allgrop.size(); i++) {
			fb[i] = new JButton(allgrop.get(i), no);
			fb[i].setPreferredSize(new Dimension(279, 50));
			fb[i].setHorizontalAlignment(JButton.LEFT);
			fb[i].setBackground(new Color(255, 255, 255));
			fb[i].setBorderPainted(false);// 按钮无边框
			fb[i].addMouseListener(new MouseAdapter() {

				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					JButton t1 = (JButton) e.getSource();
					t1.setBackground(new Color(235, 235, 235));
				}

				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					JButton t = (JButton) e.getSource();
					t.setBackground(new Color(255, 255, 255));
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if (e.getButton() == MouseEvent.BUTTON1) {
						int jj = 0;
						JButton te = (JButton) e.getSource();
						if (te.getIcon() == no) {
							String tname = te.getText();
							for (jj = 0; jj < allgrop.size(); jj++) {
								if (allgrop.get(jj).equals(tname)) {
									break;
								}
							}
							c1.show(pcc, Integer.toString(jj + 1));
						} else {
							c1.show(pcc, "0");
						}
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						JButton tt = (JButton) e.getSource();
						showPopupMenu(e, tt.getText(), allgrop);
					}
				}
			});
			pp.add(fb[i]);
		}
		pp.setPreferredSize(new Dimension(279, 510));
		pp.setBackground(new Color(255, 255, 255));
		jsp1.setBounds(0, 140, 279, 510);
		// jsp1.setPreferredSize(new
		// Dimension(279,510));//添加这一句在面板切换时，第一张面板和其他的有明显的不同
		jsp1.setViewportView(pp);
		pc[0].add(jsp1);
		pc[0].setOpaque(false);
		/***************************/
		/** 其他卡片，每个分组一张卡片 **/
		for (int i = 1; i <= allgrop.size(); i++) {
			// 首先获取某个分组内的所有好友
			Map<String, Object> af = new GetUserInfo().getOneGropFriend(AllFri,
					allgrop.get(i - 1));
			// 初始化所有按钮，包括分组数+好友数
			int n = af.size();
			JButton[] fq = new JButton[allgrop.size() + n];
			JPanel pi = new JPanel();// 创建存放面板的一个面板，功能等同于第一张卡片中的pp面板
			pc[i] = new JPanel();// 初始化第i张卡片
			JScrollPane jspp = new JScrollPane();// 创建滚动面板JSP
			jspp
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			if (allgrop.size() + af.size() < 10) {
				pi.setLayout(new GridLayout(10, 1));
			} else {
				pi.setLayout(new GridLayout(allgrop.size() + af.size(), 1));
			}
			for (int j = 0; j < allgrop.size(); j++) {// 循环遍历所有分组，将每个分组添加到每张卡片上
				if (j == i - 1) {// 如果该分组名等于这卡片
					fq[j] = new JButton(allgrop.get(j), yes);// 这张卡片处于打开状态（图片指向下）
					fq[j].setPreferredSize(new Dimension(279, 50));
				} else {
					fq[j] = new JButton(allgrop.get(j), no);// 不然就是处于关闭状态，图片横向图片
					fq[j].setPreferredSize(new Dimension(279, 50));
				}
				fq[j].setHorizontalAlignment(JButton.LEFT);// 设置图片靠左
				fq[j].setBackground(new Color(255, 255, 255));// 设置按钮背景色
				fq[j].setBorderPainted(false);// 按钮无边框
				fq[j].addMouseListener(new MouseAdapter() {// 添加监听
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub
								JButton t1 = (JButton) e.getSource();
								t1.setBackground(new Color(235, 235, 235));
							}

							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub
								JButton t = (JButton) e.getSource();
								t.setBackground(new Color(255, 255, 255));
							}

							@Override
							public void mouseClicked(MouseEvent e) {// 如果点击这个按钮，就显示这张卡片
								// TODO Auto-generated method stub
								// super.mouseClicked(e);
								if (e.getButton() == e.BUTTON1) {
									int jj = 0;
									JButton te = (JButton) e.getSource();
									if (te.getIcon() == no) {
										String tname = te.getText();
										for (jj = 0; jj < allgrop.size(); jj++) {
											if (allgrop.get(jj).equals(tname)) {
												break;
											}
										}
										c1.show(pcc, Integer.toString(jj + 1));
									} else {
										c1.show(pcc, "0");
									}
								} else if (e.getButton() == e.BUTTON3) {
									JButton tt = (JButton) e.getSource();
									showPopupMenu(e, tt.getText(), allgrop);
								}
							}
						});
				pi.add(fq[j]);// 将按钮添加到面板上
				if (allgrop.get(j).equals(allgrop.get(i - 1))) {// 如果该分组是该卡片所在分组，就添加这个分组内的好友
					int fn = 0;
					if (af.size() > 0) {
						fn = af.size();
					} else {
						continue;
					}
					System.out.println(allgrop.get(j) + "分组内的好友" + fn);
					JLabel[] flb = new JLabel[fn];
					int k = 0;
					for (final Entry<String, Object> entry : af.entrySet()) {// 遍历分组内的所有好友信息
						@SuppressWarnings("unchecked")
						Map<String, String> tt = (Map<String, String>) entry
								.getValue();// 临时存放好友的分组，备注
						String beizhu = "";
						for (Entry<String, String> tf : tt.entrySet()) {
							beizhu = tf.getKey();
						}
						flb[k] = new FriendModel(id, entry.getKey(), beizhu,
								allgrop);// 创建好友标签
						//creatFriendModel(flb[k],id,entry.getKey(),beizhu,allgrop);
						flb[k].setBorder(BorderFactory.createEmptyBorder(2, 2,
								2, 2));// 设置好友边框等
						flb[k].addMouseListener(new MouseAdapter() {// 添加监听，点击聊天，修改信息等
									@Override
									public void mouseClicked(MouseEvent e) {
										// TODO Auto-generated method stub
										// super.mouseClicked(e);
										if (e.getClickCount() == 2) {
											System.out.println("通过标签");
											System.out.println("你想与"
													+ entry.getKey() + "聊天");
											TalkUI talk = new TalkUI(newid, entry.getKey(),"123");//新建一个聊天窗口
											ManageTalkUITherd.add(newid+"@"+entry.getKey(), talk);//将新建的聊天窗口加入聊天组里
										}
									}
								});
						pi.add(flb[k]);// 将该好友加入面板
						k++;
					}

				}

			}
			pi.setPreferredSize(new Dimension(279, 510));
			jspp.setViewportView(pi);// 讲pi放到滚动面板上
			jspp.setBounds(0, 140, 279, 510);
			// jspp.setPreferredSize(new Dimension(279,510));
			pi.setBackground(new Color(255, 255, 255));
			pc[i].add(pi);
			pc[i].setOpaque(false);
		}

		pcc.setLayout(c1);
		pcc.setOpaque(false);
		for (int i = 0; i <= allgrop.size(); i++) {
			pcc.add(pc[i], Integer.toString(i));
		}
		return pcc;
	}

	private void creatFriendModel(JLabel jLable, String id, String string, String beizhu, ArrayList<String> allgrop) throws IOException, ClassNotFoundException {//创建好友标签
		// TODO Auto-generated method stub
		jLable = new FriendModel(id, string, beizhu,
				allgrop);
	}

	private void creatPnor(String id) throws IOException,
			ClassNotFoundException {
		Message mm = new Message();
		UserBean myinfo = new UserBean();
		myinfo.setId(id);
		mm.setUb(myinfo);
		mm.setTcp_ip(8);
		myinfo = new SendMessage().SendMessage(id,mm).getUb();// 获取个人的所有信息
		// TODO Auto-generated method stub
		// 设置基本属性
		pnor = new JPanel();
		pnor.setLayout(null);
		pnor.setPreferredSize(new Dimension(279, 140));
		// pnor.setBounds(0, 0, 279, 140);
		/*********************/
		// 设置北部最小化标签
		JLabel min;
		String minpath = "image/buddymin.png";
		min = new SetJLabel().GetJLabel(minpath, 0, 0, 30, 30);
		min.setBounds(219, 0, 30, 30);
		min.setToolTipText("最小化");
		min.addMouseListener(new MouseAdapter() {// 设置最小化
					public void mouseClicked(MouseEvent e) {
						mainjf.setExtendedState(JFrame.ICONIFIED);
					}
				});
		// 关闭标签
		JLabel close;
		String closepath = "image/buddyclose.png";
		close = new SetJLabel().GetJLabel(closepath, 0, 0, 30, 30);
		close.setBounds(249, 0, 30, 30);
		close.setToolTipText("关闭");
		close.addMouseListener(new MouseAdapter() {// 设置关闭
					public void mouseClicked(MouseEvent e) {
						int res = JOptionPane.showConfirmDialog(mainjf,"确定退出QQ么","提醒！"
								,JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
						if(res == 0){
							Message mm = new Message();
							UserBean uub = new UserBean();
							uub.setId(newid);
							mm.setTcp_ip(0);
							mm.setUb(uub);
							try {
								mm = new SendMessage().SendMessage(newid, mm);
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.exit(0);
						}
					}
				});
		// 头像标签
		/*
		 * 使用了重绘方法，以一个面板为画布，用来显示状态的小标签
		 */
		Image src = ImageIO.read(new File(myinfo.getIcon()));
		int w = 64;
		int h = src.getHeight(null) * w / src.getWidth(null);
		Image sma = src.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ImageIcon headicon = new ImageIcon(sma);// 个人头像
		image2 = headicon.getImage();
		online = new ImageIcon("image//state_online.png");
		jpin = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(image2, 0, 0, null);
			}
		};
		jpin.setPreferredSize(new Dimension(64, 64));
		jpin.setLayout(null);
		jpin.setOpaque(false);
		jcoc = new JButton();
		jcoc.setIcon(online);
		jcoc.setBounds(51, 51, 13, 13);
		jcoc.setFocusPainted(false);
		creatpop();
		jcoc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pop.show(jcoc, 13, 13);
				jcoc.setFocusPainted(false);
			}
		});
		jpin.setBounds(10, 30, 64, 64);
		// jpin.setLocation(0,0);
		pnor.add(jpin);
		jpin.add(jcoc);
		/******** over *********/

		// 天气标签
		JLabel tp = new JLabel(new ImageIcon("image//tq.png"));
		tp.setBounds(185, 30, 66, 55);
		tp.setToolTipText("点击查看天气");
		tp.addMouseListener(new MouseAdapter() {// 设置点击打开查询天气的网页
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						Desktop desktop;
						if (Desktop.isDesktopSupported()) {
							desktop = Desktop.getDesktop();
							try {
								// URI指定网页的地址
								desktop.browse(new URI(
										"http://www.weather.com.cn"));
							} catch (Exception e1) {
								// TODO: handle exception
								e1.printStackTrace();
							}
						}
					}
				});
		pnor.add(tp);
		// 昵称
		JTextField nickname = new JTextField(myinfo.getNickname());
		nickname.setFont(new Font("微软雅黑", Font.BOLD, 16));
		nickname.setForeground(new Color(255, 255, 255));
		nickname.setEditable(false);
		nickname.setBounds(85, 30, 120, 30);
		nickname.setBorder(new EmptyBorder(0, 0, 0, 0));
		nickname.setOpaque(false);
		pnor.add(nickname);
		// ///////////////
		// 个性签名
		final JTextField mytitle = new JTextField(myinfo.getTitle());
		mytitle.setBounds(85, 62, 100, 20);
		mytitle.setBorder(new EmptyBorder(0, 0, 0, 0));
		mytitle.setFont(new Font("宋体", Font.PLAIN, 12));
		mytitle.setForeground(new Color(255, 255, 255));
		mytitle.setToolTipText(myinfo.getTitle());
		mytitle.setOpaque(false);
		mytitle.setEditable(false);
		mytitle.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mouseEntered(e);
				// mytitle.setBorder(new EmptyBorder(10,10,10,10));
				mytitle.setBorder(BorderFactory.createLineBorder(Color.black));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mouseExited(e);
				mytitle.setBorder(new EmptyBorder(0, 0, 0, 0));
				mytitle.setOpaque(false);
			}

		});
		pnor.add(mytitle);
		pnor.add(min);
		pnor.add(close);
		pnor.setOpaque(false);
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
				jcoc.setFocusPainted(false);
			}
		});
		JMenuItem hidem = new JMenuItem("隐身", hide);
		hidem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(hide);
				jcoc.setFocusPainted(false);
			}
		});
		JMenuItem notm = new JMenuItem("请勿打扰", not);
		notm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(not);
				jcoc.setFocusPainted(false);
			}
		});
		JMenuItem onlinem = new JMenuItem("我在线上", online);
		onlinem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(online);
				jcoc.setFocusPainted(false);
			}
		});
		JMenuItem awaym = new JMenuItem("离开", away);
		awaym.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(away);
				jcoc.setFocusPainted(false);
			}
		});
		JMenuItem Qmem = new JMenuItem("Q我吧", Qme);
		Qmem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(Qme);
				jcoc.setFocusPainted(false);
			}
		});
		pop = new JPopupMenu();
		pop.setBackground(new Color(255, 255, 255));
		pop.add(busym);
		pop.add(hidem);
		pop.add(notm);
		pop.add(onlinem);
		pop.add(awaym);
		pop.add(Qmem);

	}

	private void showPopupMenu(MouseEvent e, final String string,
			final ArrayList<String> allgrop1) {
		/*
		 * 分组管理的弹出菜单
		 */
		if (e.getButton() == e.BUTTON3) {
			// TODO Auto-generated method stub
			JButton text = (JButton) e.getSource();
			Point po = java.awt.MouseInfo.getPointerInfo().getLocation();
			JMenuItem add = new JMenuItem("添加分组");
			add.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String newfenzu = JOptionPane.showInputDialog(fenzumanage,
							"输入新的分组名");
					UserBean ub = new UserBean();
					Message mm = new Message();
					ub.setId(newid);
					ub.setName(newfenzu);
					mm.setUb(ub);
					mm.setTcp_ip(12);
					try {
						mm = new SendMessage().SendMessage(newid,mm);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						vil();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			JMenuItem del = new JMenuItem("删除分组");
			del.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String newf = string;
					int f = 0;
					for (int i = 0; i < allgrop1.size(); i++) {
						if (allgrop1.get(i).equals(string)) {
							continue;
						} else {
							newf = allgrop1.get(i);
							f = 1;
							break;
						}
					}
					if (f == 0) {
						JOptionPane.showMessageDialog(pcen, "分组数不能为0");
					} else {
						int res = JOptionPane.showConfirmDialog(pcen,
								"确定删除该分组么，删除后该分组内好友将随机添加到默认分组'" + newf + "'",
								"提醒！", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (res == 0) {// 单机“是”按钮
							UserBean ub = new UserBean();
							Message mm = new Message();
							ub.setId(newid);// 我的账号
							ub.setPass(string);// 旧的分组
							ub.setName(newf);// 好友添加到分组
							mm.setUb(ub);
							mm.setTcp_ip(14);
							try {
								mm = new SendMessage().SendMessage(newid,mm);
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								vil();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			});

			JMenuItem update = new JMenuItem("修改分组");
			update.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String newfenzu = JOptionPane.showInputDialog(fenzumanage,
							"输入新的分组名");
					int f = 0;
					for(int i=0; i<allgrop1.size(); i++){
						if(newfenzu.equals(allgrop1.get(i))){
							f = 1;
							break;
						}
					}
					if (newfenzu != null && !newfenzu.equals("") && f == 0) {
						UserBean ub = new UserBean();
						Message mm = new Message();
						ub.setId(newid);
						ub.setPass(string);// 旧的分组
						ub.setName(newfenzu);// 新的分组
						mm.setUb(ub);
						mm.setTcp_ip(13);
						try {
							mm = new SendMessage().SendMessage(newid,mm);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							vil();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			fenzumanage = new JPopupMenu();
			fenzumanage.add(add);
			fenzumanage.add(del);
			fenzumanage.add(update);
			fenzumanage.setBackground(new Color(255, 255, 255));
			fenzumanage.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	public void vil() throws ClassNotFoundException, IOException {
		//System.exit(0);
		mainjf.dispose();
		new QQbuddy(newid);
	}
	public static void getF(){
		mainjf.requestFocusInWindow();
	}
}
