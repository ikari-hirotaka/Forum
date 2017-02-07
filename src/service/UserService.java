package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import beans.AllUser;
import beans.UpdateState;
import beans.User;
import dao.AllUserDao;
import dao.UserDao;
import utils.CipherUtil;
import utils.StreamUtil;

public class UserService {

	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPass());
			user.setPass(encPassword);



			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

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



	public void updateState(UpdateState us) {

		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.updateState(connection, us);

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

	public void updateStateRe(UpdateState us) {

		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.updateStateRe(connection, us);

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





	public List<AllUser> getUser() {

		Connection connection = null;
		try {
			connection = getConnection();

			AllUserDao alluserDao = new AllUserDao();

			List<AllUser> user = alluserDao.getUser(connection);

			commit(connection);

			return user;
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
