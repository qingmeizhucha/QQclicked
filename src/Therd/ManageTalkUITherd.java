/*
 * 管理所有打开的QQ聊天界面
 */
package Therd;

import java.util.HashMap;

import com.qq.clicked.UI.*;


public class ManageTalkUITherd {
	private static HashMap hm = new HashMap<String , TalkUI>();
	//加入一个新开的聊天
	public static void add(String id,TalkUI chat){
		hm.put(id, chat);
	}
	//结束一个聊天
	public static void del(String id){
		hm.remove(id);
	}
	//取出一个聊天->接受消息时需要取出
	public static TalkUI getTalk(String id){
		return (TalkUI)hm.get(id);
	}
}
