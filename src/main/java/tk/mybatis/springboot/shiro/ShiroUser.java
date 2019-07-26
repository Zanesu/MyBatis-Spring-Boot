package tk.mybatis.springboot.shiro;

import java.io.Serializable;

import tk.mybatis.springboot.model.UserInfo;

public class ShiroUser implements Serializable {
	private String name;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ShiroUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShiroUser(UserInfo user) {
		super();
		this.name = user.getUsername();
		this.id = user.getId();
	}

	public ShiroUser(String username) {
		super();
		this.name = username;
	}

}
