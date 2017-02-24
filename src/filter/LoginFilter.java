package filter;

import java.io.IOException;

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




@WebFilter(urlPatterns={"/*"})
public class LoginFilter implements Filter {



	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();

		User user = (User) session.getAttribute("loginUser");
		if(user==null){
			request.getRequestDispatcher("login").forward(request, response);
			return;
		}else if(user.getState()==1){
			request.getRequestDispatcher("login").forward(request, response);
			return;
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