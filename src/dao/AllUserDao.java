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
			String sql = "select * from all_user";
			ps = connection.prepareStatement(sql);
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
				int state = rs.getInt("state");

				User user = new User();
				user.setId(id);
				user.setLogin_id(login_id);
				user.setName(name);
				user.setState(state);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
