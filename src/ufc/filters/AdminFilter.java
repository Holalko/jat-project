package ufc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufc.managedBeans.LoginMB;
import ufc.models.enums.Role;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest r = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String loginURL = r.getContextPath() + "/public/login.xhtml";
		String logged = (String)r.getSession().getAttribute("loggedUser");
		if(logged != null) {
			Role role = (Role)r.getSession().getAttribute("role");
			if(Role.ADMIN.equals(role)) {
				chain.doFilter(r, res);
			} else {
				res.sendRedirect(loginURL);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
