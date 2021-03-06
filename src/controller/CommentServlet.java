package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Comment;
import beans.User;
import service.CommentService;

@WebServlet(urlPatterns = { "/comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int post_id = Integer.parseInt(request.getParameter("post_id"));

		List<String> c_messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		if (isValid(request, c_messages) == true) {

			User user = (User) session.getAttribute("loginUser");

			Comment nc = new Comment();

			nc.setPost_id(post_id);
			nc.setUser_id(user.getId());
			nc.setText(request.getParameter("comment"));

			new CommentService().newComment(nc);

			response.sendRedirect("./");

		} else {
			session.setAttribute("errorMessages", c_messages);

			response.sendRedirect("./");
		}

	}

	private boolean isValid(HttpServletRequest request, List<String> c_messages) {

		String text = request.getParameter("comment");

		if (StringUtils.isBlank(text)) {
			c_messages.add("コメント本文を入力してください。");
		} else if (text.length() > 50) {
			c_messages.add("コメント本文は500文字以下で入力してください。");
		}

		if (c_messages.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

}
