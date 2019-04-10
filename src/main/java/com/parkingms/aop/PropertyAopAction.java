package com.parkingms.aop;


import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 性能统计AOP
 * @author 张怡
 *
 */
@Aspect
@Component
public class PropertyAopAction {
	/**
	 * 自动注入IPropertyService接口，用来将性能信息保存到数据库
	 */
	//@Autowired
	//private IPropertyService service;
	
	// 环绕通知
//	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
//	public Object doAroundMethod(ProceedingJoinPoint pjp) throws Throwable {
//		PropertyBean property = new PropertyBean();
//		// 获取访问的URI
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//		String uri = request.getServletPath();
//		property.setUri(uri);
//		// 记录请求开始时间
//		Long start = System.currentTimeMillis();
//		
//		// 执行方法
//		Object result = pjp.proceed();
//		
//		//记录请求结束时间
//		Long end = System.currentTimeMillis();
//		//计算请求响应时间
//		int responseTime = (int)(end-start);
//		property.setAverageTime(responseTime);
//		property.setCount(1);
//		property.setTotalTime(responseTime);
//		//保存到数据库
//		service.updateProperty(property);
////		System.out.println(property);
//		return result;
//	}
}
