package com.edu.action.user;

import javax.annotation.Resource;

import com.edu.service.user.IUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.edu.entity.user.User;
import com.edu.service.user.UserService;
import com.opensymphony.xwork2.ActionSupport;

//@Controller("userAction")
//@Scope("prototype")
public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	private String re_password;
	private String msg;
	//@Resource
	private IUserService iUserService;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRe_password() {
		return re_password;
	}
	public void setRe_password(String re_password) {
		this.re_password = re_password;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public IUserService getiUserService() {
		return iUserService;
	}

	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}

	public String userLogin() throws Exception{
		String forward = null;
		User user2 = iUserService.findBynameAndPassword(user);
		if (user2 !=null) {
			user.setUserRealName(user2.getUserRealName());
			forward = "success";
		}else{
			msg = "用户不存在或密码不正确，登录失败，请重新登录或注册！";
			forward = "failure";
		}
		return forward;
	}
	
	public String userRegister() throws Exception{
		String forward = null;
		int flag = 0;
		User user2 = iUserService.findBynameAndPassword(user);
		if (user2 != null && (user2.getUserName().trim()).equals((user.getUserName().trim()))) {
			msg = "该用户名已存在，请重新注册！";
			forward = "error";
		}else {
			flag = iUserService.insert(user);
			if (flag == 1) {
				forward = "success";
			}else {
				msg = "数据库写错误！！";
				forward = "error";
			}
		}
		return forward;
	}

}
