/*
 * �������д򿪵�QQ�������
 */
package Therd;

import java.util.HashMap;

import com.qq.clicked.UI.*;


public class ManageTalkUITherd {
	private static HashMap hm = new HashMap<String , TalkUI>();
	//����һ���¿�������
	public static void add(String id,TalkUI chat){
		hm.put(id, chat);
	}
	//����һ������
	public static void del(String id){
		hm.remove(id);
	}
	//ȡ��һ������->������Ϣʱ��Ҫȡ��
	public static TalkUI getTalk(String id){
		return (TalkUI)hm.get(id);
	}
}
