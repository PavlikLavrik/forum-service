package telran.java47.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import telran.java47.security.model.Role;
import telran.java47.security.model.User;

@Component
@Order(40)
public class DeleteUserFilter implements Filter {


	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String path = request.getServletPath();
		if (checkEndPoint(request.getMethod(), path)) {
			User user = (User)request.getUserPrincipal();
			String[] arr = path.split("/");
			String userName = arr[arr.length - 1];
			
			if (!(user.getName().equalsIgnoreCase(userName)
					|| user.getRoles().contains(Role.ADMINISTRATOR))) {
				response.sendError(403);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean checkEndPoint(String method, String path) {
		return HttpMethod.DELETE.matches(method) && path.matches("/account/user/\\w+/?");
	}

}
