package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.AllUser;
import exception.SQLRuntimeException;

public class AllUserDao {

	public  List<AllUser> getUser(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "select * from all_user";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<AllUser> ret = toUserList(rs);

			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private  List<AllUser> toUserList(ResultSet rs) throws SQLException {
		List<AllUser> ret = new ArrayList<AllUser>();
		try {
			while (rs.next()) {
				int id=rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				int state = rs.getInt("state");

				AllUser user = new AllUser();
				user.setId(id);
				user.setLoginid(login_id);
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
