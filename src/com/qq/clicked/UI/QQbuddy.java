/*
 * ��¼�ɹ�֮��������棬�����б����
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
		// ���ô�С���޲���
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
		/* ����������� */
		creatPnor(id);
		mainjf.add(pnor, BorderLayout.NORTH);
		pcen = creatPcen(id);
		mainjf.add(pcen, BorderLayout.CENTER);
		creatPsou(psou);
		mainjf.add(psou, BorderLayout.SOUTH);

		// �Զ����ƶ�����
		mainjf.setLocation(1300, 200);// ���ó���λ��
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
		//��Ӻ��ѱ�ǩ
		JLabel AddManage;
		String addpath = "image/addf.png";
		AddManage = new SetJLabel().GetJLabel(addpath, 0, 0, 30, 30);
		AddManage.setBounds(0, 0, 30, 30);
		AddManage.setToolTipText("��Ӻ���");
		AddManage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new AddFriend(newid);
			}
		});
		psou.add(AddManage);
		
		//QQ���ֱ�ǩ
		JLabel music = new SetJLabel().GetJLabel("image/qqmusic.png", 0, 0, 30,
				30);
		music.setBounds(30, 0, 30, 30);
		music.setToolTipText("QQ����");
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
		
		//��Ϣ���ӱ�ǩ
		final JLabel newMessage = new JLabel();
		final ImageIcon imm = new ImageIcon("image//newMessage.png");
		newMessage.setIcon(imm);
		newMessage.setBounds(60, 0, 30,30);
		psou.add(newMessage);
		
	}

	private JPanel creatPcen(String id) throws ClassNotFoundException,
			IOException {// �в��������
		// TODO Auto-generated method stub
		/*
		 * �����б�������Ϣ
		 */
		final JPanel pcc;
		Map<String, Object> AllFri = null;// ���˺ŵ����к���ID,���飬��ע
		pcc = new JPanel();
		// pcen.setBounds(0, 140,279, 510);
		pcc.setBackground(new Color(255, 255, 255));
		pcc.setPreferredSize(new Dimension(279, 510));
		AllFri = new GetFriend().GetFriend(id);// ��ȡ�����к��ѵ���Ϣ
		final ArrayList<String> allgrop = new GetFriend().getAllGrop(id);// ��ȡ���˺ŵ����з���
		final JPanel[] pc = new JPanel[allgrop.size() + 1];// ���еĿ�Ƭ
		JButton[] fb = new JButton[allgrop.size()];// ���еķ��鰴ť
		/** ��ʼ����һ�ſ�Ƭ->ֻ��������Ľ��� **/
		pc[0] = new JPanel();
		JPanel pp = new JPanel();// ��ʱ������
		if (allgrop.size() < 10) {
			pp.setLayout(new GridLayout(10, 1));
		} else {
			pp.setLayout(new GridLayout(allgrop.size(), 1));
		}
		JScrollPane jsp1 = new JScrollPane();// ��Ű�ť�Ĺ������
		jsp1
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		for (int i = 0; i < allgrop.size(); i++) {
			fb[i] = new JButton(allgrop.get(i), no);
			fb[i].setPreferredSize(new Dimension(279, 50));
			fb[i].setHorizontalAlignment(JButton.LEFT);
			fb[i].setBackground(new Color(255, 255, 255));
			fb[i].setBorderPainted(false);// ��ť�ޱ߿�
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
		// Dimension(279,510));//�����һ��������л�ʱ����һ�����������������ԵĲ�ͬ
		jsp1.setViewportView(pp);
		pc[0].add(jsp1);
		pc[0].setOpaque(false);
		/***************************/
		/** ������Ƭ��ÿ������һ�ſ�Ƭ **/
		for (int i = 1; i <= allgrop.size(); i++) {
			// ���Ȼ�ȡĳ�������ڵ����к���
			Map<String, Object> af = new GetUserInfo().getOneGropFriend(AllFri,
					allgrop.get(i - 1));
			// ��ʼ�����а�ť������������+������
			int n = af.size();
			JButton[] fq = new JButton[allgrop.size() + n];
			JPanel pi = new JPanel();// �����������һ����壬���ܵ�ͬ�ڵ�һ�ſ�Ƭ�е�pp���
			pc[i] = new JPanel();// ��ʼ����i�ſ�Ƭ
			JScrollPane jspp = new JScrollPane();// �����������JSP
			jspp
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			if (allgrop.size() + af.size() < 10) {
				pi.setLayout(new GridLayout(10, 1));
			} else {
				pi.setLayout(new GridLayout(allgrop.size() + af.size(), 1));
			}
			for (int j = 0; j < allgrop.size(); j++) {// ѭ���������з��飬��ÿ��������ӵ�ÿ�ſ�Ƭ��
				if (j == i - 1) {// ����÷����������⿨Ƭ
					fq[j] = new JButton(allgrop.get(j), yes);// ���ſ�Ƭ���ڴ�״̬��ͼƬָ���£�
					fq[j].setPreferredSize(new Dimension(279, 50));
				} else {
					fq[j] = new JButton(allgrop.get(j), no);// ��Ȼ���Ǵ��ڹر�״̬��ͼƬ����ͼƬ
					fq[j].setPreferredSize(new Dimension(279, 50));
				}
				fq[j].setHorizontalAlignment(JButton.LEFT);// ����ͼƬ����
				fq[j].setBackground(new Color(255, 255, 255));// ���ð�ť����ɫ
				fq[j].setBorderPainted(false);// ��ť�ޱ߿�
				fq[j].addMouseListener(new MouseAdapter() {// ��Ӽ���
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
							public void mouseClicked(MouseEvent e) {// �����������ť������ʾ���ſ�Ƭ
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
				pi.add(fq[j]);// ����ť��ӵ������
				if (allgrop.get(j).equals(allgrop.get(i - 1))) {// ����÷����Ǹÿ�Ƭ���ڷ��飬�������������ڵĺ���
					int fn = 0;
					if (af.size() > 0) {
						fn = af.size();
					} else {
						continue;
					}
					System.out.println(allgrop.get(j) + "�����ڵĺ���" + fn);
					JLabel[] flb = new JLabel[fn];
					int k = 0;
					for (final Entry<String, Object> entry : af.entrySet()) {// ���������ڵ����к�����Ϣ
						@SuppressWarnings("unchecked")
						Map<String, String> tt = (Map<String, String>) entry
								.getValue();// ��ʱ��ź��ѵķ��飬��ע
						String beizhu = "";
						for (Entry<String, String> tf : tt.entrySet()) {
							beizhu = tf.getKey();
						}
						flb[k] = new FriendModel(id, entry.getKey(), beizhu,
								allgrop);// �������ѱ�ǩ
						//creatFriendModel(flb[k],id,entry.getKey(),beizhu,allgrop);
						flb[k].setBorder(BorderFactory.createEmptyBorder(2, 2,
								2, 2));// ���ú��ѱ߿��
						flb[k].addMouseListener(new MouseAdapter() {// ��Ӽ�����������죬�޸���Ϣ��
									@Override
									public void mouseClicked(MouseEvent e) {
										// TODO Auto-generated method stub
										// super.mouseClicked(e);
										if (e.getClickCount() == 2) {
											System.out.println("ͨ����ǩ");
											System.out.println("������"
													+ entry.getKey() + "����");
											TalkUI talk = new TalkUI(newid, entry.getKey(),"123");//�½�һ�����촰��
											ManageTalkUITherd.add(newid+"@"+entry.getKey(), talk);//���½������촰�ڼ�����������
										}
									}
								});
						pi.add(flb[k]);// ���ú��Ѽ������
						k++;
					}

				}

			}
			pi.setPreferredSize(new Dimension(279, 510));
			jspp.setViewportView(pi);// ��pi�ŵ����������
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

	private void creatFriendModel(JLabel jLable, String id, String string, String beizhu, ArrayList<String> allgrop) throws IOException, ClassNotFoundException {//�������ѱ�ǩ
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
		myinfo = new SendMessage().SendMessage(id,mm).getUb();// ��ȡ���˵�������Ϣ
		// TODO Auto-generated method stub
		// ���û�������
		pnor = new JPanel();
		pnor.setLayout(null);
		pnor.setPreferredSize(new Dimension(279, 140));
		// pnor.setBounds(0, 0, 279, 140);
		/*********************/
		// ���ñ�����С����ǩ
		JLabel min;
		String minpath = "image/buddymin.png";
		min = new SetJLabel().GetJLabel(minpath, 0, 0, 30, 30);
		min.setBounds(219, 0, 30, 30);
		min.setToolTipText("��С��");
		min.addMouseListener(new MouseAdapter() {// ������С��
					public void mouseClicked(MouseEvent e) {
						mainjf.setExtendedState(JFrame.ICONIFIED);
					}
				});
		// �رձ�ǩ
		JLabel close;
		String closepath = "image/buddyclose.png";
		close = new SetJLabel().GetJLabel(closepath, 0, 0, 30, 30);
		close.setBounds(249, 0, 30, 30);
		close.setToolTipText("�ر�");
		close.addMouseListener(new MouseAdapter() {// ���ùر�
					public void mouseClicked(MouseEvent e) {
						int res = JOptionPane.showConfirmDialog(mainjf,"ȷ���˳�QQô","���ѣ�"
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
		// ͷ���ǩ
		/*
		 * ʹ�����ػ淽������һ�����Ϊ������������ʾ״̬��С��ǩ
		 */
		Image src = ImageIO.read(new File(myinfo.getIcon()));
		int w = 64;
		int h = src.getHeight(null) * w / src.getWidth(null);
		Image sma = src.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ImageIcon headicon = new ImageIcon(sma);// ����ͷ��
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

		// ������ǩ
		JLabel tp = new JLabel(new ImageIcon("image//tq.png"));
		tp.setBounds(185, 30, 66, 55);
		tp.setToolTipText("����鿴����");
		tp.addMouseListener(new MouseAdapter() {// ���õ���򿪲�ѯ��������ҳ
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						Desktop desktop;
						if (Desktop.isDesktopSupported()) {
							desktop = Desktop.getDesktop();
							try {
								// URIָ����ҳ�ĵ�ַ
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
		// �ǳ�
		JTextField nickname = new JTextField(myinfo.getNickname());
		nickname.setFont(new Font("΢���ź�", Font.BOLD, 16));
		nickname.setForeground(new Color(255, 255, 255));
		nickname.setEditable(false);
		nickname.setBounds(85, 30, 120, 30);
		nickname.setBorder(new EmptyBorder(0, 0, 0, 0));
		nickname.setOpaque(false);
		pnor.add(nickname);
		// ///////////////
		// ����ǩ��
		final JTextField mytitle = new JTextField(myinfo.getTitle());
		mytitle.setBounds(85, 62, 100, 20);
		mytitle.setBorder(new EmptyBorder(0, 0, 0, 0));
		mytitle.setFont(new Font("����", Font.PLAIN, 12));
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
		JMenuItem busym = new JMenuItem("æµ", busy);
		busym.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(busy);
				jcoc.setFocusPainted(false);
			}
		});
		JMenuItem hidem = new JMenuItem("����", hide);
		hidem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(hide);
				jcoc.setFocusPainted(false);
			}
		});
		JMenuItem notm = new JMenuItem("�������", not);
		notm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(not);
				jcoc.setFocusPainted(false);
			}
		});
		JMenuItem onlinem = new JMenuItem("��������", online);
		onlinem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(online);
				jcoc.setFocusPainted(false);
			}
		});
		JMenuItem awaym = new JMenuItem("�뿪", away);
		awaym.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcoc.setIcon(away);
				jcoc.setFocusPainted(false);
			}
		});
		JMenuItem Qmem = new JMenuItem("Q�Ұ�", Qme);
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
		 * �������ĵ����˵�
		 */
		if (e.getButton() == e.BUTTON3) {
			// TODO Auto-generated method stub
			JButton text = (JButton) e.getSource();
			Point po = java.awt.MouseInfo.getPointerInfo().getLocation();
			JMenuItem add = new JMenuItem("��ӷ���");
			add.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String newfenzu = JOptionPane.showInputDialog(fenzumanage,
							"�����µķ�����");
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
			JMenuItem del = new JMenuItem("ɾ������");
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
						JOptionPane.showMessageDialog(pcen, "����������Ϊ0");
					} else {
						int res = JOptionPane.showConfirmDialog(pcen,
								"ȷ��ɾ���÷���ô��ɾ����÷����ں��ѽ������ӵ�Ĭ�Ϸ���'" + newf + "'",
								"���ѣ�", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (res == 0) {// �������ǡ���ť
							UserBean ub = new UserBean();
							Message mm = new Message();
							ub.setId(newid);// �ҵ��˺�
							ub.setPass(string);// �ɵķ���
							ub.setName(newf);// ������ӵ�����
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

			JMenuItem update = new JMenuItem("�޸ķ���");
			update.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String newfenzu = JOptionPane.showInputDialog(fenzumanage,
							"�����µķ�����");
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
						ub.setPass(string);// �ɵķ���
						ub.setName(newfenzu);// �µķ���
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
