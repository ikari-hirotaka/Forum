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

import beans.User;
import service.CommentService;


@WebServlet("/CommentDelete")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public CommentDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id =Integer.parseInt(request.getParameter("com_id"));
		int user_id =Integer.parseInt(request.getParameter("com_user_id"));
		int store =Integer.parseInt(request.getParameter("com_store"));

		User user = (User) session.getAttribute("loginUser");

		if(user.getDept()==2||user_id==user.getId()){

			new CommentService().CommentDelete(id);

		}else if(user.getStore()==store&&user.getDept()==3){

			new CommentService().CommentDelete(id);

		}else{
			List<String> messages = new ArrayList<String>();
			messages.add("エラーが発生しました。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
			return;
		}
		new CommentService().CommentDelete(id);

		response.sendRedirect("./");
	}

}
