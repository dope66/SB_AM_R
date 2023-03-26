package com.example.demo.config;

import com.example.demo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final UserService userService;

    public CustomAuthenticationFailureHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "아이디 또는 비밀번호가 일치하지 않습니다.";

        if (exception instanceof UsernameNotFoundException) {
            // 입력한 아이디가 존재하지 않는 경우
            errorMessage = "존재하지 않는 아이디입니다.";
        } else {
            // 입력한 아이디와 데이터베이스 내의 아이디가 일치하지 않는 경우
            String username = request.getParameter("username");
            UserDetails userDetails;
            try {
                userDetails = userService.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                errorMessage = "아이디를 확인해주세요.";
                request.getSession().setAttribute("errorMessage", errorMessage);
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            if (userDetails == null) {
                errorMessage = "아이디를 확인해주세요.";
            }
        }

        request.getSession().setAttribute("errorMessage", errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}
