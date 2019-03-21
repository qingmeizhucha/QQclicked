package com.qq.clicked.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Message.Message;
import Message.UserBean;



public class TalkUI {
	  JFrame jf;
	  JScrollPane scrollPane = null;
	  JTextPane text = null;
	  Box box = null; // ���������������
	  JButton b_insert = null, b_remove = null, b_icon = null; // ���밴ť;�����ť;����ͼƬ��ť
	  JTextField addText = null; // ���������
	  JComboBox fontName = null, fontSize = null, fontStyle = null,
			fontColor = null, fontBackColor = null; // ��������;�ֺŴ�С;������ʽ;������ɫ;���ֱ�����ɫ

	 StyledDocument doc = null;
	 String mid,fid;
	public TalkUI(String myid,String friendid,String beizhu) {
		//super("JTextP");
		jf = new JFrame();
		mid = myid;
		fid = friendid;
		Message m = new Message();
		UserBean ub = new UserBean();
		/*
		try { // ʹ��Windows�Ľ�����
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		text = new JTextPane();
		text.setEditable(false);
		doc = text.getStyledDocument(); // ���JTextPane��Document
		scrollPane = new JScrollPane(text);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		addText = new JTextField(18);
		String[] str_name = { "����", "����", "Dialog", "Gulim" };
		String[] str_Size = { "18", "22", "30", "40" };
		String[] str_Style = { "����", "б��", "����", "��б��" };
		String[] str_Color = { "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ" };
		String[] str_BackColor = { "��ɫ", "��ɫ", "����", "����", "����", "����" };
		fontName = new JComboBox(str_name); // ��������
		fontSize = new JComboBox(str_Size); // �ֺ�
		fontStyle = new JComboBox(str_Style); // ��ʽ
		fontColor = new JComboBox(str_Color); // ��ɫ
		fontBackColor = new JComboBox(str_BackColor); // ������ɫ
		b_insert = new JButton("����"); // ����
		b_remove = new JButton("���"); // ���
		b_icon = new JButton("ͼƬ"); // ����ͼƬ

		b_insert.addActionListener(new ActionListener() { // �������ֵ��¼�
					public void actionPerformed(ActionEvent e) {
						insert(getFontAttrib());
						addText.setText("");
					}
				});

		b_remove.addActionListener(new ActionListener() { // ����¼�
					public void actionPerformed(ActionEvent e) {
						text.setText("");
					}
				});

		b_icon.addActionListener(new ActionListener() { // ����ͼƬ�¼�
					public void actionPerformed(ActionEvent arg0) {
						JFileChooser f = new JFileChooser(); // �����ļ�
						f.showOpenDialog(null);
						insertIcon(f.getSelectedFile()); // ����ͼƬ
					}
				});
		box = Box.createVerticalBox(); // ���ṹ
		Box box_1 = Box.createHorizontalBox(); // ��ṹ
		Box box_2 = Box.createHorizontalBox(); // ��ṹ
		box.add(box_1);
		box.add(Box.createVerticalStrut(8)); // ���еļ��
		box.add(box_2);
		box.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); // 8���ı߾�
		// ��ʼ�����������������

		box_1.add(new JLabel("���壺")); // �����ǩ
		box_1.add(fontName); // �������
		box_1.add(Box.createHorizontalStrut(8)); // ���
		box_1.add(new JLabel("��ʽ��"));
		box_1.add(fontStyle);
		box_1.add(Box.createHorizontalStrut(8));
		box_1.add(new JLabel("�ֺţ�"));
		box_1.add(fontSize);
		box_1.add(Box.createHorizontalStrut(8));
		box_1.add(new JLabel("��ɫ��"));
		box_1.add(fontColor);
		box_1.add(Box.createHorizontalStrut(8));
		box_1.add(new JLabel("������"));
		box_1.add(fontBackColor);
		box_1.add(Box.createHorizontalStrut(8));
		box_1.add(b_icon);
		box_2.add(addText);
		box_2.add(Box.createHorizontalStrut(8));
		box_2.add(b_insert);
		box_2.add(Box.createHorizontalStrut(8));
		box_2.add(b_remove);
		jf.getRootPane().setDefaultButton(b_insert); // Ĭ�ϻس���ť
		jf.getContentPane().add(scrollPane);
		jf.getContentPane().add(box, BorderLayout.SOUTH);
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		addText.requestFocus();
	}

	 void insertIcon(File file) {
		text.setCaretPosition(doc.getLength()); // ���ò���λ��
		text.insertIcon(new ImageIcon(file.getPath())); // ����ͼƬ
		insert(new FontAttrib()); // ���������Ի���
	}

	 void insert(FontAttrib attrib) {
		try { // �����ı�
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
			String time = df.format(new Date());
			doc.insertString(doc.getLength(), time+":"+attrib.getText() + "\n", attrib
					.getAttrSet());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	 FontAttrib getFontAttrib() {
		FontAttrib att = new FontAttrib();
		att.setText(addText.getText());
		att.setName((String) fontName.getSelectedItem());
		att.setSize(Integer.parseInt((String) fontSize.getSelectedItem()));
		String temp_style = (String) fontStyle.getSelectedItem();
		if (temp_style.equals("����")) {
			att.setStyle(FontAttrib.GENERAL);
		} else if (temp_style.equals("����")) {
			att.setStyle(FontAttrib.BOLD);
		} else if (temp_style.equals("б��")) {
			att.setStyle(FontAttrib.ITALIC);
		} else if (temp_style.equals("��б��")) {
			att.setStyle(FontAttrib.BOLD_ITALIC);
		}
		String temp_color = (String) fontColor.getSelectedItem();
		if (temp_color.equals("��ɫ")) {
			att.setColor(new Color(0, 0, 0));
		} else if (temp_color.equals("��ɫ")) {
			att.setColor(new Color(255, 0, 0));
		} else if (temp_color.equals("��ɫ")) {
			att.setColor(new Color(0, 0, 255));
		} else if (temp_color.equals("��ɫ")) {
			att.setColor(new Color(255, 255, 0));
		} else if (temp_color.equals("��ɫ")) {
			att.setColor(new Color(0, 255, 0));
		}
		String temp_backColor = (String) fontBackColor.getSelectedItem();
		if (!temp_backColor.equals("��ɫ")) {
			if (temp_backColor.equals("��ɫ")) {
				att.setBackColor(new Color(200, 200, 200));
			} else if (temp_backColor.equals("����")) {
				att.setBackColor(new Color(255, 200, 200));
			} else if (temp_backColor.equals("����")) {
				att.setBackColor(new Color(200, 200, 255));
			} else if (temp_backColor.equals("����")) {
				att.setBackColor(new Color(255, 255, 200));
			} else if (temp_backColor.equals("����")) {
				att.setBackColor(new Color(200, 255, 200));
			}
		}
		return att;
	}
	 class FontAttrib {
		public static final int GENERAL = 0; // ����
		public static final int BOLD = 1; // ����
		public static final int ITALIC = 2; // б��
		public static final int BOLD_ITALIC = 3; // ��б��
		 SimpleAttributeSet attrSet = null; // ���Լ�
		 String text = null, name = null; // Ҫ������ı�����������
		 int style = 0, size = 0; // ��ʽ���ֺ�
		 Color color = null, backColor = null; // ������ɫ�ͱ�����ɫ

		public FontAttrib() {
		}

		public SimpleAttributeSet getAttrSet() {
			attrSet = new SimpleAttributeSet();
			if (name != null) {
				StyleConstants.setFontFamily(attrSet, name);
			}
			if (style == FontAttrib.GENERAL) {
				StyleConstants.setBold(attrSet, false);
				StyleConstants.setItalic(attrSet, false);
			} else if (style == FontAttrib.BOLD) {
				StyleConstants.setBold(attrSet, true);
				StyleConstants.setItalic(attrSet, false);
			} else if (style == FontAttrib.ITALIC) {
				StyleConstants.setBold(attrSet, false);
				StyleConstants.setItalic(attrSet, true);
			} else if (style == FontAttrib.BOLD_ITALIC) {
				StyleConstants.setBold(attrSet, true);
				StyleConstants.setItalic(attrSet, true);
			}
			StyleConstants.setFontSize(attrSet, size);
			if (color != null) {
				StyleConstants.setForeground(attrSet, color);
			}
			if (backColor != null) {
				StyleConstants.setBackground(attrSet, backColor);
			}
			return attrSet;
		}
		
		public void setAttrSet(SimpleAttributeSet attrSet) {
			this.attrSet = attrSet;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		public Color getBackColor() {
			return backColor;
		}

		public void setBackColor(Color backColor) {
			this.backColor = backColor;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getStyle() {
			return style;
		}

		public void setStyle(int style) {
			this.style = style;
		}
	}
}
