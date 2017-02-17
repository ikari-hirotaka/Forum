package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/edit" })
@MultipartConfig(maxFileSize = 100000)
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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


		User user = (User) session.getAttribute("loginUser");
		int user_id=user.getId();
		user=new UserService().ReGet(user_id);
		session.setAttribute("loginUser",user);


		User edit=new UserService().userEdit(id);

		if(edit.getLogin_id()==null){
			List<String> messages = new ArrayList<String>();
			messages.add("対象のユーザーは存在しません。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("userManage");
		}else if(user.getDept()==1){

			request.setAttribute("userInf",edit);
			request.getRequestDispatcher("edit.jsp").forward(request, response);

		}




	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		User up = new User();
		up.setId(Integer.parseInt(request.getParameter("id")));
		up.setLogin_id(request.getParameter("loginid"));
		up.setPass(request.getParameter("pass1"));
		up.setName(request.getParameter("name"));
		if(request.getParameter("store")==null&&request.getParameter("dept")==null){
			up.setStore(0);
			up.setDept(0);
		}else{
			up.setStore(Integer.parseInt(request.getParameter("store")));
			up.setDept(Integer.parseInt(request.getParameter("dept")));
		}


		if (isValid(request, messages) == true) {


			new UserService().userUpdate(up);
			response.sendRedirect("userManage");
		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("userInf", up);
			request.getRequestDispatcher("edit.jsp").forward(request, response);
		}

	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String login_id = request.getParameter("loginid");
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		String name = request.getParameter("name");
		User check = (User) new UserService().CheckUser(login_id);
		System.out.println("++"+check.getLogin_id());
		System.out.println(login_id);
		if(check!=null){
			messages.add("そのIDはすでに利用されています。");
		} else if (StringUtils.isEmpty(login_id) || login_id.length() < 6) {
			messages.add("6文字以上のIDを入力してください");
		} else if (!login_id.matches("[0-9a-zA-Z]+")) {
			messages.add("IDに使用できるのは半角英数字のみです");
		}
		if (!pass1.isEmpty()&&!pass2.isEmpty()&&pass1.length() < 6) {
			messages.add("6文字以上のパスワードを入力してください");
		} else if (!pass1.equals(pass2)) {
			messages.add("パスワードが一致しません");
		}
		if (StringUtils.isEmpty(name)) {
			messages.add("名前を入力してください");
		}

		if (name.length() > 10) {
			messages.add("名前は10文字以下で入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

}
