package com.example.demo.auth.filters;

import static com.example.demo.util.Constants.UserSessionKey;

import com.example.demo.auth.user.User;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationFilter extends GenericFilterBean {

  @Override
  public void doFilter(
    ServletRequest request,
    ServletResponse response,
    FilterChain filterChain
  )
    throws IOException, ServletException {
    System.out.println("Invoke ***AuthenticationFilter*** -> doFilter");

    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();

    User user = (User) session.getAttribute(UserSessionKey);

    if (user != null) {
      System.out.println( "Invoke ***AuthenticationFilter*** -> user is not null");

      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        user,
        user.getPassword(),
        user.getAuthorities()
      );

      System.out.println( "Invoke ***AuthenticationFilter*** -> put authToken in securityContext");
      SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    // Either securityContext has authToken or not, we continue the filter chain
    filterChain.doFilter(request, response);
  }
}
