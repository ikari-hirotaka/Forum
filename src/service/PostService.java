package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import beans.NewPost;
import beans.Posts;
import dao.PostDao;

public class PostService {

	public void newPost(NewPost np) {

		Connection connection = null;
		try {
			connection = getConnection();
			PostDao pDao = new PostDao();
			pDao.newPost(connection, np);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Posts> getPosts(String category, String date1, String date2) {

		Connection connection = null;
		try {
			connection = getConnection();
			PostDao pDao = new PostDao();
			List<Posts> ret = pDao.getPosts(connection,category,date1,date2);

			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}

	}

	public Date getMin() {
		Connection connection = null;
		try {
			connection = getConnection();
			PostDao pDao = new PostDao();
			Date date1 = pDao.getMin(connection);

			commit(connection);
			return date1;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Posts> getCategories() {
		Connection connection = null;
		try {
			connection = getConnection();
			PostDao pDao = new PostDao();
			List<Posts> ret = pDao.getCategories(connection);

			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void PostDelete(int id) {
		Connection connection = null;
		try {
			connection = getConnection();

			PostDao postDao = new PostDao();
			postDao.postDelete(connection, id);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}



}
