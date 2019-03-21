package Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class UserBean implements Serializable{
	String id;
	String pass;
	String nickname;
	String sex;
	String bir;
	String title;
	String icon;
	String name;
	String tel;
	Map<String ,Object> friend;
	ArrayList<String> grop;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBir() {
		return bir;
	}
	public void setBir(String bir) {
		this.bir = bir;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Map<String ,Object> getFriend() {
		return friend;
	}
	public void setFriend(Map<String ,Object> friend) {
		this.friend = friend;
	}
	public ArrayList<String> getGrop() {
		return grop;
	}
	public void setGrop(ArrayList<String> grop) {
		this.grop = grop;
	}
}
