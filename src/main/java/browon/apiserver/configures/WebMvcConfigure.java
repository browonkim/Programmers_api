package browon.apiserver.configures;

import browon.apiserver.configures.web.SimplePageRequestHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {
    // spring boot application을 만들면 기본적으로 WebMvcConfigurer을 따로 구현하지 않았음.
    // 그렇다면 default 액션은 어떻게 될까?
    // Jackson이 작동해서 resolving을 한다는데 이 원리를 자세하게 알아보자.
    // 어떻게 사용자의 parameter를 받을까? HTTP 요청을 보면 header와 body로 구성되겠지.
    // 이것을 제대로 파악해야 제대로 된 서비스를 제공할 수 있다고 생각했음.
    @Bean
    public SimplePageRequestHandlerMethodArgumentResolver simplePageRequestHandlerMethodArgumentResolver() {
        return new SimplePageRequestHandlerMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(simplePageRequestHandlerMethodArgumentResolver());
    }

}
