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
				String id = rs.getString("id");
				String pass = rs.getString("password");
				String name = rs.getString("name");
				int store = rs.getInt("store_id");
				int dept = rs.getInt("department_id");
				int state = rs.getInt("state");

				User user = new User();
				user.setId(id);
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
			sql.append("?"); // id
			sql.append(", ?"); // pass
			sql.append(", ?"); // name
			sql.append(", ?"); // store
			sql.append(", ?"); // dept
			sql.append(", current_timestamp");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getId());
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
			sql.append(" state = 1");
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

	public void updateStateRe(Connection connection,UpdateState us) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update users set ");
			sql.append(" state = 0");
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



}
