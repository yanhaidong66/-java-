package top.haidong556.oauth.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import top.haidong556.oauth.security.key.JwtFactory;

import java.io.IOException;

@Component
public class MyAuthenticationHandler implements AuthenticationSuccessHandler
        , AuthenticationFailureHandler
        , LogoutSuccessHandler
        , AccessDeniedHandler
        , AuthenticationEntryPoint
         {
             private static final String RESPONSE_CONTEXT_TYPE="application/json;charset=UTF-8";

            /**
             * 登录成功后返回jwt
             * **/
             @Override
             public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                 addJwtToResponse(response);
                 response.setContentType(RESPONSE_CONTEXT_TYPE);
                 response.setStatus(HttpStatus.OK.value());
                 // SecurityContext在设置Authentication的时候并不会自动写入Session，读的时候却会根据Session判断，所以需要手动写入一次，否则下一次刷新时SecurityContext是新创建的实例。
                 //  https://yangruoyu.blog.csdn.net/article/details/128276473
                 request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());


             }
             private void addJwtToResponse(HttpServletResponse response){
                 try {
                     response.getWriter().write("{\""+"jwt"+"\""+":"+"\""+JwtFactory.getPublicKeyJson()+"\"}");
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }
             }

             @Override
             public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                 response.setContentType(RESPONSE_CONTEXT_TYPE);
                 response.setStatus(HttpStatus.UNAUTHORIZED.value());
             }

             @Override
             public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                 response.setContentType(RESPONSE_CONTEXT_TYPE);
                 response.setStatus(HttpStatus.OK.value());
             }

             @Override
             public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                 response.setContentType(RESPONSE_CONTEXT_TYPE);
                 response.setStatus(HttpStatus.FORBIDDEN.value());
             }

             @Override
             public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                 response.setContentType(RESPONSE_CONTEXT_TYPE);
                 response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
                 response.sendRedirect("/login");
             }
         }
