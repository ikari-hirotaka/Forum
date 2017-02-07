package beans;

import java.io.Serializable;


public class AllUser implements Serializable{
	private static final long serialVersionUID=1L;

	private int id;
	private String loginid;
	private String name;
	private int state;

	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



}
