package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.UpdateState;
import beans.User;
import beans.UserEdit;
import beans.UserUpdate;
import exception.SQLRuntimeException;

public class UserDao {

	public User getUser(Connection connection, String loginID, String password) {
		PreparedStatement ps = null;
		try {
			String sql = "select * from users where login_id = ? and password = ? ";
			ps = connection.prepareStatement(sql);

			ps.setString(1, loginID);
			ps.setString(2, password);


			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id=rs.getInt("id");
				String login_id = rs.getString("login_id");
				String pass = rs.getString("password");
				String name = rs.getString("name");
				int store = rs.getInt("store_id");
				int dept = rs.getInt("department_id");
				int state = rs.getInt("state");

				User user = new User();
				user.setId(id);
				user.setLogin_id(login_id);
				user.setPass(pass);
				user.setName(name);
				user.setStore(store);
				user.setDept(dept);
				user.setState(state);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append(" login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", store_id");
			sql.append(", department_id");
			sql.append(", insert_date");
			sql.append(") VALUES (");
			sql.append("?"); // login_id
			sql.append(", ?"); // pass
			sql.append(", ?"); // name
			sql.append(", ?"); // store
			sql.append(", ?"); // dept
			sql.append(", current_timestamp");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_id());
			ps.setString(2, user.getPass());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getStore());
			ps.setInt(5, user.getDept());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void updateState(Connection connection,UpdateState us) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update users set");
			if(us.getState()==0){
				sql.append(" state = 1");
			}else{
				sql.append(" state = 0");
			}

			sql.append(" where id= ? ");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, us.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public UserEdit userEdit(Connection connection, int id) {
		PreparedStatement ps = null;
		try {
			String sql = " select  id,login_id,name,store_id,department_id from users where id=? ";
			ps = connection.prepareStatement(sql);

			ps.setInt(1,id);



			ResultSet rs = ps.executeQuery();
			UserEdit edit = edit(rs);
			return edit;


		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private UserEdit edit(ResultSet rs) throws SQLException {

		UserEdit ue = new UserEdit();
		try {
			while (rs.next()) {
				int id=rs.getInt("id");
				String loginid = rs.getString("login_id");
				String name = rs.getString("name");
				int store = rs.getInt("store_id");
				int dept = rs.getInt("department_id");



				ue.setId(id);
				ue.setLogin_id(loginid);
				ue.setName(name);
				ue.setStore(store);
				ue.setDept(dept);



			}
			return ue;
		} finally {
			close(rs);
		}
	}


	public void userUpdate(Connection connection, UserUpdate up) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql=new StringBuilder();

			sql.append("update users set");
			sql.append(" login_id = ?");
			if(!up.getPass().isEmpty()){
				sql.append(" ,password = ?");
			}
			sql.append(" ,name = ?");
			sql.append(" ,store_id = ?");
			sql.append(" ,department_id = ?");
			sql.append(" where id = ?");



			ps = connection.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			System.out.println("1="+up.getLoginid());
			System.out.println("2="+up.getPass());
			System.out.println("3="+up.getName());
			System.out.println("4="+up.getStore());
			System.out.println("5="+up.getDept());
			System.out.println("6="+up.getId());

			ps.setString(1, up.getLoginid());
			if(!up.getPass().isEmpty()){
				ps.setString(2, up.getPass());
				ps.setString(3, up.getName());
				ps.setInt(4, up.getStore());
				ps.setInt(5, up.getDept());
				ps.setInt(6, up.getId());
			}else{
				ps.setString(2, up.getName());
				ps.setInt(3, up.getStore());
				ps.setInt(4, up.getDept());
				ps.setInt(5, up.getId());
			}

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


}