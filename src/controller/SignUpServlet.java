package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		if(user.getDept()==1){
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		} else {

			List<String> messages = new ArrayList<String>();
			messages.add("アクセス権を持っていません。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("home");
		}



	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {

			User user = new User();
			user.setLogin_id(request.getParameter("id"));
			user.setPass(request.getParameter("pass"));
			user.setName(request.getParameter("name"));
			user.setStore(Integer.parseInt(request.getParameter("store")));
			user.setDept(Integer.parseInt(request.getParameter("dept")));

			new UserService().register(user);

			response.sendRedirect("userManage");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");

		if (StringUtils.isEmpty(id)||id.length()<6) {
			messages.add("6文字以上のIDを入力してください");
		}else if(!id.matches("[0-9a-zA-Z]+")){
			messages.add("IDに使用できるのは半角英数字のみです。");
		}
		if (StringUtils.isEmpty(pass) ||pass.length()<6) {
			messages.add("6文字以上のパスワードを入力してください");
		}
		if (StringUtils.isEmpty(name)) {
			messages.add("名前を入力してください");
		}

		if(name.length()>10){
			messages.add("名前は10文字以下で入力してください");
		}

		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
