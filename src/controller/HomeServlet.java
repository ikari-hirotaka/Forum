package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Comment;
import beans.Posts;
import beans.User;
import service.CommentService;
import service.PostService;
import service.UserService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("loginUser");
		int id=user.getId();
		user=new UserService().ReGet(id);

		boolean isPosts;
		if (user != null) {
			isPosts = true;
		} else {
			isPosts = false;
		}
		String category=request.getParameter("category");
		String sdate = request.getParameter("date1");
		String gdate = request.getParameter("date2");
		Date today = new Date();
		String date1 = null;
		String date2 = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(new PostService().getMin()!=null&&StringUtils.isEmpty(sdate)){
			date1= sdf.format(new PostService().getMin());
		}else{
			date1 = sdate+" 00:00:00";
		}


		if(StringUtils.isEmpty(gdate)){
			date2= sdf.format(today).toString();
		}else{
			date2 = gdate+" 23:59:59";
		}

		List<Posts> posts = new PostService().getPosts(category,date1,date2);



		List<Posts> categories = new PostService().getCategories();

		List<Comment> com = new CommentService().getComment();

		request.setAttribute("user", user);
		request.setAttribute("cate", categories);
		request.setAttribute("posts", posts);
		request.setAttribute("com", com);
		request.setAttribute("defcate",category);
		request.setAttribute("sdate", sdate);
		request.setAttribute("gdate", gdate);
		request.setAttribute("isPosts", isPosts);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

}
