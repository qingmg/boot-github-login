package cn.qingmg.demoboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(a -> a
                        .antMatchers("/login", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(c -> c
                        // 认证成功处理
                        .successHandler((req, resp, authentication) -> resp.sendRedirect("/api/oath2/client"))
                        // 认证失败处理
                        .failureHandler((req, resp, authentication) -> {
                            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
                            resp.setContentType("text/plain");
                            resp.getWriter().write("认证失败");
                        })
                        // 认证请求地址
                        .loginProcessingUrl("/api/login/oauth2/code/github")
                        // 配置授权服务器端点信息
                        .authorizationEndpoint(ce -> ce
                                // 授权端点的前缀基础url
                                .baseUri("/api/oauth2/authorization"))
                )

                .anonymous().and()

                .exceptionHandling(eh -> eh
                        .authenticationEntryPoint((req, resp, e) -> {
                            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
                            resp.setContentType("text/plain");
                            resp.getWriter().write("用户未认证");
                        })
                        .accessDeniedHandler((req, resp, e) -> {
                            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
                            resp.setContentType("text/plain");
                            resp.getWriter().write("用户未授权");
                        })
                )
        ;
    }
}
