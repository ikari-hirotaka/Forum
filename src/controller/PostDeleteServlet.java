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
import service.PostService;

@WebServlet("/postDelete")
public class PostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public PostDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id =Integer.parseInt(request.getParameter("post_id"));
		int user_id =Integer.parseInt(request.getParameter("post_user_id"));
		int store =Integer.parseInt(request.getParameter("post_store"));

		User user = (User) session.getAttribute("loginUser");

		if(user.getDept()==2||user_id==user.getId()){

			new PostService().PostDelete(id);

		}else if(user.getStore()==store&&user.getDept()==3){

			new PostService().PostDelete(id);

		}else{
			List<String> messages = new ArrayList<String>();
			messages.add("エラーが発生しました。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
			return;
		}
		new PostService().PostDelete(id);

		response.sendRedirect("./");
	}

}
