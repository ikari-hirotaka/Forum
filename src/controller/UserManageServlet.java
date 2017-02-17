package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/userManage" })
public class UserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("loginUser");
		int id=user.getId();
		user=new UserService().ReGet(id);
		session.setAttribute("loginUser",user);

		if(user!=null){
			List<User> users=new UserService().getUser();

			request.setAttribute("users", users);

			request.getRequestDispatcher("userManage.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));
		int state = Integer.parseInt(request.getParameter("state"));

		User us= new User();
		us.setId(id);
		us.setState(state);


		new UserService().updateState(us);
		response.sendRedirect("userManage");


	}

}
