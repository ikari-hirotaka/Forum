package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.SQLRuntimeException;

public class AllUserDao {

	public  List<User> getUser(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select users.id, ");
			sql.append(" users.login_id, ");
			sql.append(" users.name, ");
			sql.append(" stores.name, ");
			sql.append(" departments.name, ");
			sql.append(" users.state ");
			sql.append(" from ");
			sql.append(" users,stores,departments ");
			sql.append(" where ");
			sql.append(" users.store_id=stores.id ");
			sql.append(" and ");
			sql.append(" users.department_id=departments.id ");
			sql.append(" order by users.id ");
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);

			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private  List<User> toUserList(ResultSet rs) throws SQLException {
		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id=rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				String store_name = rs.getString("stores.name");
				String department_name = rs.getString("departments.name");
				int state = rs.getInt("state");

				User user = new User();
				user.setId(id);
				user.setLogin_id(login_id);
				user.setName(name);
				user.setStore_name(store_name);
				user.setDepartment_name(department_name);
				user.setState(state);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
