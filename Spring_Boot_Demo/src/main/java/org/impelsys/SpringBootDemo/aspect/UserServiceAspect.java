package org.impelsys.SpringBootDemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserServiceAspect {
	
	@Pointcut("execution(* org.impelsys.SpringBootDemo.dao.impl.UserDaoImpl.*(..))")
	public void userDaoMapping() {
		
	}
	
	//Advice -> what functionality to be done/what is the action to be taken
//	@Before(value="execution(* org.impelsys.SpringBootDemo.dao.impl.UserDaoImpl.*(..))") //PointCut->the condition that we want to search for
	@Before("userDaoMapping()")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("before method:"+ joinPoint.getSignature());
	}
	//JoinPoint->it is a point during the execution of a program
//	@After(value="execution(* org.impelsys.SpringBootDemo.dao.impl.UserDaoImpl.*(..))")
	@After("userDaoMapping()")
	public void afterAdvice(JoinPoint joinPoint) {
		System.out.println("After method:"+ joinPoint.getSignature());
	}
	
}
