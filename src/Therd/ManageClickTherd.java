/*
 * 管理所有打开的QQ聊天界面
 */
package Therd;

import java.util.HashMap;


public class ManageClickTherd {
	private static HashMap hm = new HashMap<String , ClickTherd>();
	//加入一个新开的线程
	public static void add(String id,ClickTherd chat){
		hm.put(id, chat);
	}
	//结束一个线程
	public static void del(String id){
		hm.remove(id);
	}
	//根据ID获得线程
	public static ClickTherd getTherd(String id){
		return (ClickTherd)hm.get(id);
	}
}
