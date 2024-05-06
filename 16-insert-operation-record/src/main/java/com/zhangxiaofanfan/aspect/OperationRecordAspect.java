package com.zhangxiaofanfan.aspect;

import cn.hutool.json.JSONObject;
import com.zhangxiaofanfan.aop.OperationRecord;
import com.zhangxiaofanfan.enums.OperationEntryEnum;
import com.zhangxiaofanfan.enums.OperationEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * OperationRecord 注解的操作实现
 *
 * @author zhangxiaofanfan
 * @date 2023-10-23 14:35:07
 */
@Component
@Aspect
@Slf4j
public class OperationRecordAspect {
    /**
     * 声明需要实现的切点, 使用了 com.zhangxiaofanfan.aop.OperationRecord 注解的方法都被作为切点使用
     *
     * @param operationRecord   切点需要代理的注解
     */
    @Pointcut("@annotation(operationRecord)")
    public void apiRecordAnnotatedMethods(OperationRecord operationRecord) {
    }

    @Around(value = "apiRecordAnnotatedMethods(operationRecord)")
    public Object invoke(ProceedingJoinPoint joinPoint, OperationRecord operationRecord) throws Throwable {
        // 启动一个 stop watch
        StopWatch sw = new StopWatch();

        // 执行业务方法
        Object returnValue = null;

        // 获取方法名字 与 请求对象
        String methodName = joinPoint.getSignature().getName();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        // 获取代理地址、请求地址、请求类名、方法名
        String trigger = Strings.isEmpty(request.getHeader("trigger")) ? "system" : request.getHeader("trigger");
        OperationEnum operation = operationRecord.operation();
        OperationEntryEnum operationType = operationRecord.operationType();
        // 运行计时器
        sw.start(methodName);

        try {
            // 执行代理的函数方法
            returnValue = joinPoint.proceed();
        } finally {
            // 停止计时器
            sw.stop();

            // 将需要保存的数据进行记录格式化
            JSONObject json = new JSONObject();
            json.set("trigger", trigger);
            json.set("type", operationType.getName());
            json.set("operation", operation.getDesc());
            json.set("executionTime", sw.getTotalTimeMillis());

            // 通过打印日志来模拟记录持久化过程
            log.info("record is {}", json);
        }
        // 返回业务方法返回值
        return returnValue;
    }

}
