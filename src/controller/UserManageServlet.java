package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AllUser;
import beans.UpdateState;
import service.UserService;

@WebServlet(urlPatterns = { "/userManage" })
public class UserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		List<AllUser> users=new UserService().getUser();

		request.setAttribute("users", users);

		request.getRequestDispatcher("userManage.jsp").forward(request, response);
//		response.sendRedirect("userManage");
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));
		int state = Integer.parseInt(request.getParameter("state"));

		UpdateState us= new UpdateState();
		us.setId(id);
		us.setState(state);


			new UserService().updateState(us);
			response.sendRedirect("userManage");


	}

}
