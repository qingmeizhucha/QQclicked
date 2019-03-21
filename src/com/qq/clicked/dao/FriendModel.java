package com.qq.clicked.dao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.qq.clicked.UI.QQbuddy;
import com.qq.clicked.tools.SendMessage;
import com.qq.clicked.UI.*;
import Message.Message;
import Message.UserBean;
import Therd.ManageTalkUITherd;
import Therd.QQbuddyManage;

public class FriendModel extends JLabel{
	public JPanel jPanel = new JPanel();
	public JButton jButton = null;
	private JLabel lb_nickName;
	private String icon;
	private String nickname;
	private String beizhu;
	private JLabel lb_mod;
	private String title;
	private String id;
	private String myid;
	private UserBean myub;
	private JPopupMenu frimenu;
	private String showname;
	private ArrayList<String> allgrop;
	public FriendModel(String myid, String id, String beizhu,ArrayList<String> allgrop)
			throws IOException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		UserBean ub = new UserBean();
		Message mm = new Message();
		ub.setId(id);
		mm.setUb(ub);
		mm.setTcp_ip(8);
		mm = new SendMessage().SendMessage(myid,mm);
		ub = mm.getUb();
		this.myid = myid;
		this.id = id;
		this.icon = ub.getIcon();
		this.nickname = ub.getNickname();
		this.title = ub.getTitle();
		this.beizhu = beizhu;
		this.myub = ub;
		this.allgrop = allgrop;
		initialize();
		this.add(jPanel);
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount() == 2){
					System.out.println("dadwqdq");
				}
			}
		});
	}
	public void update(){
		this.repaint();
		this.validate();
	}
	private void initialize() throws IOException {
		// TODO Auto-generated method stub
		lb_mod = new JLabel();
		lb_mod.setBounds(new Rectangle(57, 30, 131, 20));
		lb_mod.setFont(new Font("Dialog", Font.PLAIN, 12));
		lb_mod.setText(title);
		lb_mod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mouseEntered(e);
				lb_mod.setToolTipText(lb_mod.getText());
				exchangeEnter();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mouseExited(e);
				exchangeExited();
			}

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					buildTalk();
				} else if (e.getButton() == e.BUTTON3) {
					updateFriend(e);
				}
			}
		});
		lb_nickName = new JLabel();
		lb_nickName.setBounds(new Rectangle(57, 10, 80, 20));
		lb_nickName.setFont(new Font("Dialog", Font.BOLD, 14));
		if (beizhu != null) {
			showname = beizhu + "(" + nickname + ")";
			lb_nickName.setText(showname);
		}else{
			lb_nickName.setText(nickname);
		}
		
		lb_nickName.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mouseEntered(e);
				lb_nickName.setToolTipText(id);
				exchangeEnter();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mouseExited(e);
				exchangeExited();
			}

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					buildTalk();
				}else if(e.getButton() == e.BUTTON3){
					updateFriend(e);
				}
			}
		});
		jPanel.setSize(new Dimension(297, 60));
		jPanel.setLayout(null);
		jPanel.add(getJButton(), null);
		jPanel.add(lb_nickName, null);
		jPanel.add(lb_mod, null);
		jPanel.setBackground(new Color(255, 255, 255));
		jPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseExited(java.awt.event.MouseEvent e) {
				exchangeExited();// 鼠标移出模板区，改变背景颜色；
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				exchangeEnter();// 鼠标移进模板区，改变背景颜色；
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					buildTalk();
				}else if(e.getButton() == e.BUTTON3){
					updateFriend(e);
				}
			}
		});
	}

	public void buildTalk() {
		// TODO Auto-generated method stub
		System.out.println("你想和" + lb_nickName.getText() + "聊天");
		TalkUI talk = new TalkUI(myid, id,beizhu);//新建一个聊天窗口
		ManageTalkUITherd.add(myid+"@"+id, talk);//将新建的聊天窗口加入聊天组里
		QQbuddy.getF();
		
	}

	private JButton getJButton() throws IOException {
		// TODO Auto-generated method stub
		Image src = ImageIO.read(new File(myub.getIcon()));
		int w = 40;
		int h = src.getHeight(null) * w / src.getWidth(null);
		Image sma = src.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ImageIcon hm = new ImageIcon(sma);
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(8, 5, 40, 40));
			jButton.setBackground(new Color(236, 255, 236));
			jButton.setIcon(hm);
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseExited(java.awt.event.MouseEvent e) {
					exchangeExited();// 鼠标移出模板区，改变背景颜色；
				}

				public void mouseEntered(java.awt.event.MouseEvent e) {
					exchangeEnter();// 鼠标移进模板区，改变背景颜色；
				}

				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					// super.mouseClicked(e);
					if (e.getClickCount() == 2) {
						buildTalk();
					}else if(e.getButton() == e.BUTTON3){
						updateFriend(e);
					}
				}
			});

		}
		jButton.setBorderPainted(false);
		return jButton;
	}

	private void exchangeEnter() {
		jPanel.setBackground(new Color(235, 235, 235));
	}

	private void exchangeExited() {
		jPanel.setBackground(new Color(255, 255, 255));
	}

	private void updateFriend(MouseEvent e) {
		JMenuItem add = new JMenuItem("修改备注");
		add.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String newbeizhu = JOptionPane.showInputDialog(jPanel,"输入新备注",beizhu);
				beizhu = newbeizhu;
				UserBean ub = new UserBean();
				Message mm = new Message();
				ub.setId(myid);
				ub.setPass(id);
				ub.setName(newbeizhu);
				mm.setUb(ub);
				mm.setTcp_ip(9);
				try {
					mm = new SendMessage().SendMessage(myid,mm);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(beizhu.equals("")){
					lb_nickName.setText(nickname);
				}else{
					lb_nickName.setText(newbeizhu + "(" + nickname + ")");
				}
				try {
					QQbuddyManage.getTalk(myid).vil();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		
		add.setOpaque(false);
		JMenuItem del = new JMenuItem("删除好友");
		del.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int res = JOptionPane.showConfirmDialog(jPanel,"确定删除该好友么","提醒！",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(res == 0){//单机“是”按钮
					UserBean ub = new UserBean();
					Message mm = new Message();
					ub.setId(myid);
					ub.setPass(id);
					mm.setUb(ub);
					mm.setTcp_ip(10);
					try {
						mm = new SendMessage().SendMessage(myid,mm);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						jPanel = new FriendModel(myid, id, beizhu, allgrop).jPanel;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						QQbuddyManage.getTalk(myid).vil();
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
		del.setOpaque(false);
		JMenuItem update = new JMenuItem("移动分组");
		update.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object fenzu[] = allgrop.toArray();
				Object newfenzu = JOptionPane.showInputDialog(jPanel,"请选择新的分组","提示信息",
						JOptionPane.INFORMATION_MESSAGE,null,fenzu,fenzu[0]);
				if(newfenzu!=null){
					String newf = newfenzu.toString();
					UserBean ub = new UserBean();
					Message mm = new Message();
					ub.setId(myid);
					ub.setPass(id);
					ub.setName(newf);//暂时借用Name传送分组名
					mm.setUb(ub);
					mm.setTcp_ip(11);
					try {
						mm = new SendMessage().SendMessage(myid,mm);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						jPanel = new FriendModel(myid, id, beizhu, allgrop).jPanel;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						QQbuddyManage.getTalk(myid).vil();
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
		update.setOpaque(false);
		frimenu = new JPopupMenu();
		frimenu.add(add);
		frimenu.add(del);
		frimenu.add(update);
		frimenu.setBackground(new Color(255, 255, 255));
		frimenu.show(e.getComponent(), e.getX(), e.getY());
	}
}
