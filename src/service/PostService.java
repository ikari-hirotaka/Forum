package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
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

	public List<Posts> getPosts() {

		Connection connection = null;
		try {
			connection = getConnection();
			PostDao pDao = new PostDao();
			List<Posts> ret = pDao.getPosts(connection);

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

}
