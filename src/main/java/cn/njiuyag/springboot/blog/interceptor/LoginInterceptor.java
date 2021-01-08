package cn.njiuyag.springboot.blog.interceptor;

import cn.njiuyag.springboot.blog.constant.ConfigConsts;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台系统身份验证拦截器
 *
 * @author hjx
 * @date 2020/12/25
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            request.getSession().removeAttribute("errorMsg");
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        NeedLogin clazzNeedLogin = handlerMethod.getBeanType().getAnnotation(NeedLogin.class);
        NeedLogin methodNeedLogin = handlerMethod.getMethodAnnotation(NeedLogin.class);
        if (clazzNeedLogin == null && methodNeedLogin == null) {
            request.getSession().removeAttribute("errorMsg");
            return true;
        }

        boolean isLogin = request.getSession().getAttribute(ConfigConsts.LOGIN_USER_ID_ATTR) != null;
        if (!isLogin) {
            request.getSession().setAttribute(ConfigConsts.ERROR_MSG, "请重新登陆");
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        } else {
            request.getSession().removeAttribute("errorMsg");
            return true;
        }
    }
}
