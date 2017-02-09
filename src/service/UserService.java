package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.AllUser;
import beans.UpdateState;
import beans.User;
import beans.UserEdit;
import beans.UserUpdate;
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

	public UserEdit userEdit(int id) {

		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			UserEdit useredit=userDao.userEdit(connection, id);

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


	public void userUpdate(UserUpdate up) {

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

}
