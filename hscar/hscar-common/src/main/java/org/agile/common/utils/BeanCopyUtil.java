package org.agile.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * 复制Java Bean的工具类
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public class BeanCopyUtil {
	/**
	 * 把orig的属性复制给dest
	 * @param orig
	 * @param dest
	 * @return boolean
	 */
	public static boolean copyProperties(Object orig, Object dest) {
		try {
			/**
			 * 不支持转换类型，但是速度更快
			 */
			PropertyUtils.copyProperties(dest, orig);
			return true;
		} catch (Exception e) {
			try {
				/**
				 * 提供转换类型的功能（即发现两个JavaBean的同名属性为不同类型时，在支持的数据类型范围内进行转换）：
				 * boolean、byte、char、double、float、long、int、short、
				 * java.lang.BigDecimal、java.lang.BigInteger、java.lang.Boolean、 java.lang.Byte、java.lang.Character、 
				 * java.lang.Double、java.lang.Float、java.lang.Long、 java.lang.Integer、java.lang.Short、
				 * java.lang.String、java.lang.Class、java.sql.Date、java.sql.Time、java.sql.Timestamp
				 */
				BeanUtils.copyProperties(dest, orig);
				return true;
			} catch (Exception e1) {
				return false;
			}
		}
	}
}