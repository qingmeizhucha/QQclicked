/*
 * �������д򿪵�QQ�������
 */
package Therd;

import java.util.HashMap;


public class ManageClickTherd {
	private static HashMap hm = new HashMap<String , ClickTherd>();
	//����һ���¿����߳�
	public static void add(String id,ClickTherd chat){
		hm.put(id, chat);
	}
	//����һ���߳�
	public static void del(String id){
		hm.remove(id);
	}
	//����ID����߳�
	public static ClickTherd getTherd(String id){
		return (ClickTherd)hm.get(id);
	}
}
