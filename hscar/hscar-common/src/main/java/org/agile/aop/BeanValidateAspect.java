package org.agile.aop;

import org.agile.annotation.BeanValidate;
import org.agile.common.validator.ValidatorUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeanValidateAspect {
	
	@Around("@annotation(validate)")
	public Object doAround(final ProceedingJoinPoint pjp, BeanValidate validate) throws Throwable {
		Object value = null;
		
		Object[] args = pjp.getArgs();
		Object entity = null;
		if (!ArrayUtils.isEmpty(args)) {
			entity = args[0];
		}
		
		/**
		 * 校验，出错抛异常
		 */
		beanValidator(entity);
		
		/**
		 * 执行新增或修改操作
		 */
		value = pjp.proceed();
		
		return value;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	public void beanValidator(Object object, Class<?>... groups) {
		ValidatorUtils.validateEntity(object, groups);
	}
}