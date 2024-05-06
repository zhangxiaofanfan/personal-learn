package com.zhangxiaofanfan.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 创建用户
 *
 * @author zhangxiaofanfan
 * @date 2024-03-01 14:17:19
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    /**
     * 向容器中注入基于内存策略的用户数据
     *
     * @return 管理内存用户的 manager 对象
     */
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     // 创建基于内存的用户信息管理器
    //     InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    //     // 使用 manager 管理 UserDetailsService 对象
    //     manager.createUser(
    //             // 创建用户对象, 管理账号、密码和角色等属性信息
    //             User.withDefaultPasswordEncoder()
    //                     .username("zhangxiaofanfan")
    //                     .password("yangxiaomianmian")
    //                     .roles("USER")
    //                     .build()
    //     );
    //     return manager;
    // }
}
