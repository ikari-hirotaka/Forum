package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

import beans.User;



@WebFilter(urlPatterns={"/signup","/userManage","/edit"})
public class AccessFilter implements Filter {



	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();

		User user = (User) session.getAttribute("loginUser");

		if(user.getDept()!=1){
			List<String> messages = new ArrayList<String>();
			messages.add("アクセス権を持っていません。");
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("./").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}




	}

	@Override
	public void init(FilterConfig config) {
		System.out.println("LoginFilter# ログイン情報を取得します。");

	}

	@Override
	public void destroy() {
	}



}