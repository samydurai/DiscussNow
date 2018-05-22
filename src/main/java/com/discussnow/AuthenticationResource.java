package com.discussnow;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationResource {

  @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
  public Principal getUserInfo(Principal principal) {
    return principal;
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    // token can be revoked here if needed
    CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(
        AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
    cookieClearingLogoutHandler.logout(request, response, null);
    securityContextLogoutHandler.logout(request, response, null);
    try {
      //sending back to client app
      response.sendRedirect("http://localhost:8080");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
