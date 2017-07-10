package org.agile.common.utils.convert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ObjectUtils;

/**
 * Map与JavaBean之间的相互转化的工具类
 * @author zhangmm
 */
public class RedisHash_JavaBean_Convert_Utils {
	private static final Logger logger = LoggerFactory.getLogger(RedisHash_JavaBean_Convert_Utils.class);
	
	/**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                
                if (result != null) {
                	Field field;
    				try {
    					field = type.getDeclaredField(propertyName);
    					if(field!=null && field.isAnnotationPresent(DateTimeFormat.class)) {
    						DateTimeFormat annotation = field.getAnnotation(DateTimeFormat.class);
    						if( StringUtils.isNotEmpty(annotation.pattern()) && (result instanceof Date) ) {
    							String timeStr = new SimpleDateFormat(annotation.pattern()).format(result);
    							returnMap.put(propertyName, timeStr);
    						}
    					} else {
    						returnMap.put(propertyName, result.toString());
    					}
    				} catch (Exception e) {
    					logger.error("对象转化异常：", e);
    				}
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
    
    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InstantiationException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("rawtypes")
	public static Object convertMap(Class type, Map map) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            Class propertyType = descriptor.getPropertyType();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
            	Object value = map.get(propertyName);
            	
            	// 若值为空，则跳过
    			if(ObjectUtils.isEmpty(value)) continue;
    			
    			// 取value的String值
    			String map_value_str = value.toString();
    			
    			// 字段类型转换
    			if (propertyType == int.class) { //int.class等价于Integer.TYPE
    				value = Integer.parseInt(map_value_str);
				} else if (propertyType == long.class) { //long.class等价于Long.TYPE
					value = Long.parseLong(map_value_str);
				} else if (propertyType == float.class) { //float.class等价于Float.TYPE
					value = Float.parseFloat(map_value_str);
				} else if (propertyType == double.class) { //double.class等价于Double.TYPE
					value = Double.parseDouble(map_value_str);
				} else if (propertyType == boolean.class) { //boolean.class等价于Boolean.TYPE
					value = Boolean.parseBoolean(map_value_str);
				} else if (propertyType == Integer.class) {
					value = new Integer(map_value_str);
				} else if (propertyType == Long.class) {
					value = new Long(map_value_str);
				} else if (propertyType == Float.class) {
					value = new Float(map_value_str);
				} else if (propertyType == Double.class) {
					value = new Double(map_value_str);
				} else if (propertyType == BigDecimal.class) {
					value = new BigDecimal(map_value_str);
				} else if (propertyType == Boolean.class) {
					value = new Boolean(map_value_str);
				} else if (propertyType == Date.class) {
					Field field;
					try {
						field = type.getDeclaredField(propertyName);
						if(field!=null && field.isAnnotationPresent(DateTimeFormat.class)) {
							DateTimeFormat annotation = field.getAnnotation(DateTimeFormat.class);
							if( StringUtils.isNotEmpty(annotation.pattern()) ) {
								value = new SimpleDateFormat(annotation.pattern()).parse(map_value_str);
							}
						}
					} catch (Exception e) {
						logger.error("对象转化异常：", e);
					}
				} else {
					value = map_value_str;
				}
            	
                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }
}