package Message;

import java.io.Serializable;

public class Message implements Serializable {
	int tcp_ip;
	UserBean ub;
	SendText st;
	public int getTcp_ip() {
		return tcp_ip;
	}

	public void setTcp_ip(int tcp_ip) {
		this.tcp_ip = tcp_ip;
	}

	public UserBean getUb() {
		return ub;
	}

	public void setUb(UserBean ub) {
		this.ub = ub;
	}

	public SendText getSt() {
		return st;
	}

	public void setSt(SendText st) {
		this.st = st;
	}
	
}
