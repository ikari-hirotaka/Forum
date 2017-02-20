package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Comment;
import dao.CommentDao;

public class CommentService {

	public void newComment(Comment nc) {

		Connection connection = null;
		try {
			connection = getConnection();
			CommentDao cDao = new CommentDao();
			cDao.newComment(connection, nc);

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

	public  List<Comment> getComment() {
		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao cdao = new CommentDao();


			List<Comment> com =cdao.getComment(connection);

			commit(connection);

			return com;
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

	public void CommentDelete(int id) {
		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao comDao = new CommentDao();
			comDao.commentDelete(connection, id);

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
