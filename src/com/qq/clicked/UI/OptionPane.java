/*
 * ����ʽ�Ի���
 */
package com.qq.clicked.UI;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class OptionPane {
	public void GetQQSucceesed(String msg,String title){
		Container container = new Container();
		msg = "��ϲ�����QQ�ţ�" + msg + "���μ�";
		JOptionPane.showOptionDialog(container, msg, title, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
	}
	public void GetQQFail(String msg,String title){
		Container container = new Container();
		JOptionPane.showOptionDialog(container, msg, title, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
	}
}
