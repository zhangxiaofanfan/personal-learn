package com.zhangxiaofanfan.transaction.aspect;

import com.zhangxiaofanfan.transaction.connection.MyConnection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-31 下午2:18
 * @description TODO
 */
@Aspect
@Component
public class MyDateSourceAspect {
    // 包名: * 任意包, 方法名 getConnection(), 参数列表任意
    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Connection around(ProceedingJoinPoint point) throws Throwable {
        Connection connection = (Connection) point.proceed();
        return new MyConnection(connection);
    }
}
