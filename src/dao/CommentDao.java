package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Comment;
import exception.SQLRuntimeException;

public class CommentDao {

	public void newComment(Connection connection, Comment nc) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append(" post_id ");
			sql.append(", text ");
			sql.append(",  user_id ");
			sql.append(", insert_date");
			sql.append(") VALUES (");
			sql.append(" ?"); // post_id
			sql.append(", ?"); // text
			sql.append(", ?"); // user_id
			sql.append(", current_timestamp");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, nc.getPost_id());
			ps.setString(2, nc.getText());
			ps.setInt(3, nc.getUser_id());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public  List<Comment> getComment(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "select comments.post_id,comments.text,users.name,comments.insert_date from users,comments where users.id= "
					+ "comments.user_id";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Comment> ret = toCommentList(rs);

			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private   List<Comment> toCommentList(ResultSet rs) throws SQLException {
		List<Comment> ret = new ArrayList<Comment>();
		try {
			while (rs.next()) {
				int id=rs.getInt("comments.post_id");
				int post_id = rs.getInt("comments.post_id");
				String text = rs.getString("comments.text");
				String user_name = rs.getString("users.name");
				String insert_date = rs.getString("comments.insert_date");

				Comment com = new Comment();
				com.setId(id);
				com.setPost_id(post_id);
				com.setText(text);
				com.setUser_name(user_name);
				com.setInsert_date(insert_date);

				ret.add(com);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
