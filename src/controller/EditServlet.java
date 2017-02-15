package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/edit" })
@MultipartConfig(maxFileSize = 100000)
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id=Integer.parseInt(request.getParameter("id"));

		User user = (User) session.getAttribute("user");
		if(user.getDept()==1){
			user=new UserService().userEdit(id);
			request.setAttribute("userInf",user);
			request.getRequestDispatcher("edit.jsp").forward(request, response);
		} else {

			List<String> messages = new ArrayList<String>();
			messages.add("アクセス権を持っていません。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("home");
		}


	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		User up = new User();
		up.setId(Integer.parseInt(request.getParameter("id")));
		up.setLogin_id(request.getParameter("loginid"));
		up.setPass(request.getParameter("pass1"));
		up.setName(request.getParameter("name"));
		up.setStore(Integer.parseInt(request.getParameter("store")));
		up.setDept(Integer.parseInt(request.getParameter("dept")));

		if (isValid(request, messages) == true) {


			new UserService().userUpdate(up);
			response.sendRedirect("userManage");
		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("userInf", up);
			request.getRequestDispatcher("edit.jsp").forward(request, response);
		}

	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String id = request.getParameter("loginid");
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		String name = request.getParameter("name");

		if (StringUtils.isEmpty(id) || id.length() < 6) {
			messages.add("6文字以上のIDを入力してください");
		} else if (!id.matches("[0-9a-zA-Z]+")) {
			messages.add("IDに使用できるのは半角英数字のみです");
		}
		if (!pass1.isEmpty()&&!pass2.isEmpty()&&pass1.length() < 6) {
			messages.add("6文字以上のパスワードを入力してください");
		} else if (!pass1.equals(pass2)) {
			messages.add("パスワードが一致しません");
		}
		if (StringUtils.isEmpty(name)) {
			messages.add("名前を入力してください");
		}

		if (name.length() > 10) {
			messages.add("名前は10文字以下で入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

}
