package telran.java47.security;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class ExpiredPasswoedFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request =(HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(chekEndPoint(request.getMethod(),request.getServletPath())&& authentication!=null
				&& authentication.getPrincipal() instanceof UserProfile) {
			UserProfile userProfile =(UserProfile) authentication.getPrincipal();
			if(!userProfile.isPasswordNotExpired()) {
				response.sendError(403, "password expired");
				return;
			}
		}
		chain.doFilter(request, response);
		
	}

	private boolean chekEndPoint(String method, String path) {
		return !(HttpMethod.PUT.matches(method) && path.matches("/accaunt/password/?"));
	}
	
	

}
