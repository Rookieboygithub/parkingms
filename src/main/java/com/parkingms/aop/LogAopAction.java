package com.parkingms.aop;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.parkingms.bean.LogBean;
import com.parkingms.service.ILogServcie;
/**
 * 日志管理AOP
 * @author 张怡
 *
 */
@Aspect
@Component
public class LogAopAction {
	/**
	 * 自动注入ILogServcie接口，用来将日志信息保存到数据库
	 */
	@Autowired
	private ILogServcie service;

	// 配置切入点
//	@Pointcut(" within(com.parkingms.service..*) && @annotation(SystemLog)")
//	private void serviceAspect() {
//	}
	

	// 环绕通知
//	@Around("within(com.parkingms.service..*) && @annotation(parkingmsLog)")
	@Around("within(com.parkingms..*) && @annotation(parkingmsLog)")
	public Object doAroundMethod(ProceedingJoinPoint pjp,ParkingmsLog parkingmsLog) throws Throwable {
		LogBean log = new LogBean();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取当前时间
        log.setTime(new Date());
        // 获取系统IP
        String ip = request.getRemoteAddr();
        log.setIp(ip);
        // 方法开始时间，统计模块执行效率使用
        long start = System.currentTimeMillis();
        // 获取方法名
//        String methodName = pjp.getSignature().getName();
        // 获取操作模块
        String module = parkingmsLog.module();
        log.setModule(module);
        // 获取操作方法
        String method = parkingmsLog.method();
        log.setMethod(method);
        // 获取前后台日志类型标记
        int plantformType = parkingmsLog.plantform();
        log.setPlantformType(plantformType);
        // 获取登录操作日志类型标记
        int type = parkingmsLog.type();
        log.setType(type);
        
        // 执行方法，获取返回参数
        Object result = pjp.proceed();
        
        // 方法结束时间，统计模块执行效率使用
        long end = System.currentTimeMillis();
        // 方法执行时间
        int responseTime = (int) (end-start);
        log.setResponseTime(responseTime);
        // 获取当前登录用户名
        // 注意：要和登录后保存在session的键名字对应
     	String username = (String) request.getSession().getAttribute("username");
        log.setUsername(username);
        // 保存数据库
//        System.out.println(log);
        service.saveLog(log);
        
        return result;
	}
}
