package cn.njiuyag.springboot.blog.configuration;

import cn.njiuyag.springboot.blog.constant.ConfigConsts;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hjx
 * @date 2020/12/28
 */
@Configuration
public class ServletRegistration {
    @Bean
    public ServletRegistrationBean kaptchaServletRegistration(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new KaptchaServlet(), "/common/kaptcha");
        servletRegistrationBean.addInitParameter("kaptcha.border", "no");
        servletRegistrationBean.addInitParameter("kaptcha.textproducer.font.color", "black");
        servletRegistrationBean.addInitParameter("kaptcha.image.width", "150");
        servletRegistrationBean.addInitParameter("kaptcha.image.height", "40");
        servletRegistrationBean.addInitParameter("kaptcha.textproducer.font.size", "30");
        servletRegistrationBean.addInitParameter("kaptcha.session.key", ConfigConsts.KAPTCHA_SESSION_KEY);
        servletRegistrationBean.addInitParameter("kaptcha.textproducer.char.space", "5");
        return servletRegistrationBean;
    }
}
