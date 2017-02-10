package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.NewPost;
import beans.Posts;
import beans.User;
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

	public List<Posts> getPosts(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select ");
			sql.append(" posts.id , ");
			sql.append(" posts.title , ");
			sql.append(" posts.text , ");
			sql.append(" users.name , ");
			sql.append(" posts.category , ");
			sql.append(" posts.insert_date ");
			sql.append(" from ");
			sql.append(" users,posts ");
			sql.append(" where ");
			sql.append(" users.id=posts.user_id ");

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<Posts> ret = posts(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private  List<Posts> posts(ResultSet rs) throws SQLException {
		List<Posts> ret = new ArrayList<Posts>();
		try {
			while (rs.next()) {
				String title=rs.getString("title");
				String text = rs.getString("text");
				String category= rs.getString("category");
				String name = rs.getString("name");
				String insert_date= rs.getString("insert_date");


				Posts posts = new Posts();
				posts.setTitle(title);
				posts.setText(text);
				posts.setCategory(category);
				posts.setName(name);
				posts.setInsert_date(insert_date);

				ret.add(posts);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
