package com.qq.clicked.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;

public class GetUserInfo {

	@SuppressWarnings("unchecked")
	public ArrayList<String> getAllGrop(Map<String ,Object> allFri) {
		// TODO Auto-generated method stub
		ArrayList<String> aList = new ArrayList<String>();
		if (allFri.size() > 0) {
			for (Entry<String, Object> entry : allFri
					.entrySet()) {
				Map<String, String> l = null;
				l = (Map<String, String>) entry.getValue();
				for (Map.Entry<String, String> el : l.entrySet()) {
					aList.add(el.getValue());
				}
			}
			LinkedHashSet<String> hSet = new LinkedHashSet<String>();
			hSet.addAll(aList);
			aList.clear();
			aList.addAll(hSet);
		}
		return aList;
	}
	public Map<String ,Object> getOneGropFriend(Map<String ,Object> allFri,String gropname){
		Map<String ,Object> text = new HashMap<String, Object>();
		for(Entry<String,Object> entry : allFri.entrySet()){//循环遍历所有的好友
			@SuppressWarnings("unchecked")
			Map<String ,String> t = (Map<String, String>) entry.getValue();//临时创建变量，存放每一个好友信息
			for(Entry<String,String> e1 : t.entrySet()){//遍历这个好友信息
				String fz = e1.getValue();//fz表示这个好友所在的分组
				if(fz.equals(gropname)){//如果是在所需要的分组
					text.put(entry.getKey(), t);//添加到需要return的MAP中
				}
			}
		}
		return text;
		
	}

}
