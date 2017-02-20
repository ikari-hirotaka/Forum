package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import beans.NewPost;
import beans.Posts;
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

	public List<Posts> getPosts(Connection connection, String category, String date1, String date2) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select ");
			sql.append(" posts.id , ");
			sql.append(" users.id as userid , ");
			sql.append(" users.store_id , ");
			sql.append(" users.department_id , ");
			sql.append(" posts.title , ");
			sql.append(" posts.text , ");
			sql.append(" users.name , ");
			sql.append(" posts.category , ");
			sql.append(" date_format(posts.insert_date,'%Y-%m-%d %k:%i')as insert_date ");
			sql.append(" from ");
			sql.append(" users,posts ");
			sql.append(" where ");
			sql.append(" posts.user_id = users.id ");
			sql.append(" and ");

			if(!StringUtils.isEmpty(category)){
				sql.append(" posts.category = ? ");
				sql.append(" and ");
			}

			sql.append(" posts.insert_date >= ? ");
			sql.append(" and ");
			sql.append(" posts.insert_date <= ? ");

			sql.append(" order by posts.id desc ");


			ps = connection.prepareStatement(sql.toString());

			if(!StringUtils.isEmpty(category)){
				ps.setString(1, category);
				ps.setString(2, date1);
				ps.setString(3, date2);
			}else{
				ps.setString(1, date1);
				ps.setString(2, date2);
			}
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
				int id= rs.getInt("id");
				int user_id=rs.getInt("userid");
				int dept=rs.getInt("department_id");
				int store=rs.getInt("store_id");
				String title=rs.getString("title");
				String text = rs.getString("text");
				String category= rs.getString("category");
				String name = rs.getString("name");
				String insert_date= rs.getString("insert_date");


				Posts posts = new Posts();
				posts.setId(id);
				posts.setUser_id(user_id);
				posts.setDept(dept);
				posts.setStore(store);
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

	public Date getMin(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select ");
			sql.append(" min(insert_date) ");
			sql.append(" from posts ");



			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			Date date1=null;
			while(rs.next()){
				date1 =rs.getDate("min(insert_date)");
			}

			return date1;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}


	}

	public List<Posts> getCategories(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select ");
			sql.append(" distinct ");
			sql.append(" category ");
			sql.append(" from ");
			sql.append(" posts ");



			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Posts> cate = cate(rs);

			return cate;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private  List<Posts> cate(ResultSet rs) throws SQLException {
		List<Posts> ret = new ArrayList<Posts>();
		try {
			while (rs.next()) {

				String category= rs.getString("category");



				Posts cate = new Posts();
				cate.setCategory(category);


				ret.add(cate);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void postDelete(Connection connection, int id) {
		PreparedStatement ps = null;
		try{
			String sql=" delete from posts where id=? ";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}


}
