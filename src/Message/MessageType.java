/*
 * 本类为通信协议类
 */
package Message;


public class MessageType {
	private int user_out=0;//用户退出
	private int user_login=1;//用户登录
	private int user_reg=2;  //用户注册
	private int reg_suc=3;//用户注册成功
	private int user_log_suc=4;//登录成功
	private int user_log_filed=5;//登录失败
	private int get_user_friend=6;//获取所有的分组情况
	private int get_user_grop=7;//获取所有的分组
	private int get_user_info=8;//获取某个ID的所有信息
	private int update_friend_beizhu=9;//修改好友备注
	private int del_friend=10;//删除好友
	private int move_friend=11;//移动好友分组
	private int add_newgrop=12;//添加新的分组
	private int update_gropname=13;//修改分组名
	private int del_grop=14;//删除分组
	private int sendmessage=15;//发送消息
	private int add_friend=16;//添加好友
}
