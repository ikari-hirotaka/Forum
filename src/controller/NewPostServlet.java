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

import beans.NewPost;
import beans.User;
import service.LoginService;
import service.PostService;

@WebServlet(urlPatterns = { "/newPost" })
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("newPost.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {
			User user = (User) session.getAttribute("loginUser");
			NewPost np = new NewPost();
			np.setTitle(request.getParameter("title"));
			np.setMain(request.getParameter("text"));
			np.setCategory(request.getParameter("category"));
			np.setId(user.getId());

			new PostService().newPost(np);
			response.sendRedirect("home");

		} else {
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("newPost.jsp").forward(request, response);
		}

	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");

		if (StringUtils.isEmpty(title)) {
			messages.add("件名を入力してください。");
		}else if(title.length()>50){
			messages.add("件名は50文字以下で");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

}
