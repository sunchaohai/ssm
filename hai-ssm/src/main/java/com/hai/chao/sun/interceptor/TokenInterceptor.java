package com.hai.chao.sun.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 使用token的逻辑是，给所有的url加一个拦截器，在拦截器里面用java的UUID生成一个随机的UUID并把这个UUID放到session里面，
 * 然后在浏览器做数据提交的时候将此UUID提交到服务器。服务器在接收到此UUID后，检查一下该UUID是否已经被提交，如果已经被提交，则不让逻辑继续执行下去…
 * 
 * @author xiaohai
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = Logger.getLogger(Token.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
         if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    request.getSession(true).setAttribute("token", UUID.randomUUID().toString());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        LOG.warn("please don't repeat submit,url:" + request.getServletPath());
                        return false;
                    }
                    request.getSession(true).removeAttribute("token");
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(true).getAttribute("token");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }

}
