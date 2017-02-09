package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.NewPost;
import exception.SQLRuntimeException;

public class PostDao {

	public void newPost(Connection connection, NewPost np) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO posts ( ");
			sql.append(" title ");
			sql.append(", text ");
			sql.append(",  user_id ");
			sql.append(", category");
			sql.append(", insert_date");
			sql.append(") VALUES (");
			sql.append(" ?"); // title
			sql.append(", ?"); // text
			sql.append(", ?"); // user_id
			sql.append(", ?"); // category
			sql.append(", current_timestamp");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, np.getTitle());
			ps.setString(2, np.getMain());
			ps.setInt(3, np.getId());
			ps.setString(4, np.getCategory());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
