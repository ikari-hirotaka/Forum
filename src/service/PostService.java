package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.NewPost;
import beans.UpdateState;
import dao.PostDao;
import dao.UserDao;

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

}
