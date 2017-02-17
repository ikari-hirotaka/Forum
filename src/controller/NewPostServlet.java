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

import beans.NewPost;
import beans.User;
import service.PostService;
import service.UserService;

@WebServlet(urlPatterns = { "/newPost" })
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		int id=user.getId();
		user=new UserService().ReGet(id);

		request.getRequestDispatcher("newPost.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		NewPost np = new NewPost();
		np.setTitle(request.getParameter("title"));

		String regex = "\\r\\n";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(request.getParameter("text"));

		np.setMain(m.replaceAll("<br/>"));
		np.setCategory(request.getParameter("category"));

		if (isValid(request, messages) == true) {
			User user = (User) session.getAttribute("loginUser");

			np.setId(user.getId());

			new PostService().newPost(np);
			response.sendRedirect("./");

		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("newTitle", request.getParameter("title"));
			request.setAttribute("newMain", request.getParameter("text"));
			request.setAttribute("newCategory", request.getParameter("category"));
			request.getRequestDispatcher("newPost.jsp").forward(request, response);
		}

	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");

		if (StringUtils.isBlank(title)) {
			messages.add("件名を入力してください。");
		}else if(title.length()>50){
			messages.add("件名は50文字以下で入力してください。");
		}
		if (StringUtils.isBlank(text)){
			messages.add("本文を入力してください。");
		}else if(title.length()>50){
			messages.add("本文は1000文字以下で入力してください。");
		}

		if(StringUtils.isBlank(category)){
			messages.add("カテゴリーを入力してください。");
		}else if(title.length()>50){
			messages.add("カテゴリーは10文字以下で入力してください。");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

}
