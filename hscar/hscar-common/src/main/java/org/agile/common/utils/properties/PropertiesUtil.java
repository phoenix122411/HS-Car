package org.agile.common.utils.properties;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 参数
 */
public class PropertiesUtil {

	public static Object getPorjectProperty(String key) throws IOException {
		return getPorperty(key);
	}

	public static <T> T getPorjectProperty(String key, Class<T> clazz) throws IOException {
		return getPorperty(key, clazz);
	}

	public static Object getPorperty(String key) {
		try {
			return CustomizedPropertyPlaceholderConfigurer.getContextProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T getPorperty(String key, Class<T> clazz) throws IOException {
		Object result = getPorperty(key);
		return result == null ? null : (T) result;
	}

	public static String getPorpertyString(String key) {
		return ObjectUtils.toString(getPorperty(key));
	}

	public static List<String> initPropList(List<String> list, String key) {
		if (CollectionUtils.isEmpty(list)) {
			String tempStr = PropertiesUtil.getPorpertyString(key);
			if (!StringUtils.isEmpty(tempStr)) {
				list = Arrays.asList(tempStr.split(","));
			}
		}
		return list;
	}
}