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

import service.UserService;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/delete")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UserDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int id = 0;


		try{

			id=Integer.parseInt(request.getParameter("id"));

		}catch(NumberFormatException e){

			List<String> messages = new ArrayList<String>();
			messages.add("対象のユーザーは存在しません。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("userManage");
			return;

		}

		new UserService().userDelete(id);

		response.sendRedirect("userManage");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
