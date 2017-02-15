package beans;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String login_id;
	private String pass;
	private String name;
	private int store;
	private String store_name;
	private String department_name;
	private int dept;
	private int state;


	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getStore() {
		return store;
	}
	public void setStore(int store) {
		this.store = store;
	}

	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}

	public int getState() {
		return state;
	}
	public void setState(int user) {
		this.state = user;
	}



}
