package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.User;
import dao.AllUserDao;
import dao.UserDao;
import utils.CipherUtil;

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



	public void updateState(User us) {

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

	public User userEdit(int id) {

		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			User useredit=userDao.userEdit(connection, id);

			commit(connection);

			return useredit;
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





	public List<User> getUser() {

		Connection connection = null;
		try {
			connection = getConnection();

			AllUserDao alluserDao = new AllUserDao();

			List<User> user = alluserDao.getUser(connection);

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


	public void userUpdate(User up) {

		Connection connection = null;
		try {
			connection = getConnection();

			if(!up.getPass().isEmpty()){
				String encPassword = CipherUtil.encrypt(up.getPass());
				up.setPass(encPassword);
			}

			UserDao userDao = new UserDao();
			userDao.userUpdate(connection, up);

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



	public void userDelete(int id) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.userDelete(connection, id);

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
