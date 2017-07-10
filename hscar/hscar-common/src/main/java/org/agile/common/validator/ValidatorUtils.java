package org.agile.common.validator;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.agile.common.exception.RRException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * hibernate-validator校验工具类
 *
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws RRException  校验不通过，则报RRException异常
     */
	public static void validateEntity(Object object, Class<?>... groups) throws RRException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
//			List<String> errorMessages = ValidatorUtils.extractMessage(constraintViolations);
			Map<String, String> errorMessages = ValidatorUtils.extractPropertyAndMessage(constraintViolations);
			throw new RRException(String.format("类名【%s】：%s", object.getClass().getName(), errorMessages.toString()));
		}
	}
    
	/**
	 * 辅助方法, 转换Set<ConstraintViolation>为Map<property, message>.
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations) {
		Map<String, String> errorMessages = Maps.newHashMap();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return errorMessages;
	}
	
	/**
	 * 辅助方法, 转换Set<ConstraintViolation>为List<message>
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> extractMessage(Set<? extends ConstraintViolation> constraintViolations) {
		List<String> errorMessages = Lists.newArrayList();
		for (ConstraintViolation violation : constraintViolations) {
			String message = String.format("字段【%s】：%s", violation.getPropertyPath().toString(), violation.getMessage());
			errorMessages.add(message);
		}
		return errorMessages;
	}
}
