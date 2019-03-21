/*
 * 弹出式对话框
 */
package com.qq.clicked.UI;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class OptionPane {
	public void GetQQSucceesed(String msg,String title){
		Container container = new Container();
		msg = "恭喜您获得QQ号：" + msg + "请牢记";
		JOptionPane.showOptionDialog(container, msg, title, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
	}
	public void GetQQFail(String msg,String title){
		Container container = new Container();
		JOptionPane.showOptionDialog(container, msg, title, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
	}
}
