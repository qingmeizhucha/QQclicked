package Therd;

import java.util.HashMap;

import com.qq.clicked.UI.QQbuddy;

public class QQbuddyManage {
	private static HashMap<String, QQbuddy> hm = new HashMap<String , QQbuddy>();
	//加入一个新开的聊天
	public static void add(String id,QQbuddy chat){
		hm.put(id, chat);
	}
	//结束一个聊天
	public static void del(String id){
		hm.remove(id);
	}
	//取出一个聊天->接受消息时需要取出
	public static QQbuddy getTalk(String id){
		return (QQbuddy)hm.get(id);
	}
}
