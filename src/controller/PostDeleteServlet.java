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

import service.PostService;

@WebServlet("/postDelete")
public class PostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public PostDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = 0;

		try{

			id=Integer.parseInt(request.getParameter("postId"));

		}catch(NumberFormatException e){

			List<String> messages = new ArrayList<String>();
			messages.add("対象の投稿は存在しません。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("userManage");
			return;
		}

		new PostService().PostDelete(id);

		response.sendRedirect("home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
