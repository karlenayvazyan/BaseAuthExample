package am.ak.base.auth.config;

import am.ak.base.auth.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new AuthInterceptor())
               .pathMatcher(new AntPathMatcher("/api/v1/*"));
    }
} 