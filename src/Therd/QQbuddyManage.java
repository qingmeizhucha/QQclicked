package Therd;

import java.util.HashMap;

import com.qq.clicked.UI.QQbuddy;

public class QQbuddyManage {
	private static HashMap<String, QQbuddy> hm = new HashMap<String , QQbuddy>();
	//����һ���¿�������
	public static void add(String id,QQbuddy chat){
		hm.put(id, chat);
	}
	//����һ������
	public static void del(String id){
		hm.remove(id);
	}
	//ȡ��һ������->������Ϣʱ��Ҫȡ��
	public static QQbuddy getTalk(String id){
		return (QQbuddy)hm.get(id);
	}
}
