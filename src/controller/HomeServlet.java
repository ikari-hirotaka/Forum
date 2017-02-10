package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Posts;
import beans.User;
//import beans.UserMessage;
//import service.MessageService;
import service.PostService;

@WebServlet(urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("loginUser");
		boolean isPosts;
		if (user != null) {
			isPosts = true;
		} else {
			isPosts = false;
		}

		List<Posts> posts = new PostService().getPosts();

		request.setAttribute("posts", posts);
		request.setAttribute("isPosts", isPosts);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

}
