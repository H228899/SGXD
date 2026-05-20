package com.example.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // 放行H2数据库控制台
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            // 权限配置，所有接口暂时全部放开
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            // 关闭默认登录表单
            .formLogin(form -> form.disable())
            // 关闭CSRF校验，方便开发
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // 密码加密工具Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}