package com.parkingms.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义注解（AOP日志功能需要用到）
 * @author 张怡
 *
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface ParkingmsLog {
	/**
	 * 操作模块
	 * @return
	 */
    String module()  default "";  
    /**
     * 操作方法
     * @return
     */
    String method()  default ""; 
    /**
     * 标记前台日志还是后台日志：0前台，1后台
     * @return
     */
    int plantform() default 0;
    /**
     * 标记是登录日志还是操作日志：0登录，1操作
     * @return
     */
    int type() default 0;
}
