package com.zhangxiaofanfan.filter;

import com.zhangxiaofanfan.context.UserThreadLocal;
import com.zhangxiaofanfan.db.UserData;
import com.zhangxiaofanfan.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器, 通过拦截请求, 将用户信息封装在 ThreadLocal 中
 *
 * @author zhangxiaofanfan
 * @date 2023-10-24 16:31:32
 */
@Slf4j
public class UserFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Api-Token");
        User curUser = new User();
        log.info("token is {}", token);
        // 模拟对 token 进行解析获取到用户主键
        if (Strings.isNotBlank(token) && StringUtils.isNumeric(token)) {
            int userId = Integer.parseInt(token) % 10;
            curUser = UserData.users.get(userId);
        }
        // 将 user 注入到线程对象中
        UserThreadLocal.set(curUser);
        filterChain.doFilter(request, response);
        // 在 ThreadLocal 中清楚调用户信息
        UserThreadLocal.clear();
    }
}
