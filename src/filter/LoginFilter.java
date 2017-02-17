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




@WebFilter(urlPatterns={"/*"})
public class LoginFilter implements Filter {



	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();


		if(session.getAttribute("loginUser")==null){
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