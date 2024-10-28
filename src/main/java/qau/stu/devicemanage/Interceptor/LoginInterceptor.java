package qau.stu.devicemanage.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object obj = request.getSession().getAttribute("current");
        if (obj != null) {
            return true;
        } else {
            response.sendRedirect("/login");
            return false;
        }

    }
}
