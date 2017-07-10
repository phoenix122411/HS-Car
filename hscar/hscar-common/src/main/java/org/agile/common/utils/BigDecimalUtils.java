package org.agile.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Java在java.math包中提供的API类BigDecimal，用来对超过16位有效位的数进行精确的运算。
 * 双精度浮点型变量double可以处理16位有效数。在实际应用中，需要对更大或者更小的数进行运算和处理。
 * float和double只能用来做科学计算或者是工程计算，在商业计算中要用java.math.BigDecimal。 
 * 用于对金额做加、减、乘、除的操作。
 * 
 * @author zhangmm
 */
public class BigDecimalUtils {
	/**
	 * 判断a是否在(start,end)开区间内
	 */
	public static boolean is_between_open_interval(BigDecimal a, BigDecimal start, BigDecimal end) {
		if(a.compareTo(start) <= 0) {
			return false;
		}
		if(a.compareTo(end) >= 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断a是否在[start,end]闭区间内
	 */
	public static boolean is_between_closed_interval(BigDecimal a, BigDecimal start, BigDecimal end) {
		if(a.compareTo(start) < 0) {
			return false;
		}
		if(a.compareTo(end) > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断a是否在(start,end]半开半闭区间内
	 */
	public static boolean is_between_open_closed_interval(BigDecimal a, BigDecimal start, BigDecimal end) {
		if(a.compareTo(start) <= 0) {
			return false;
		}
		if(a.compareTo(end) > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断a是否在[start,end)半闭半开区间内
	 */
	public static boolean is_between_closed_open_interval(BigDecimal a, BigDecimal start, BigDecimal end) {
		if(a.compareTo(start) < 0) {
			return false;
		}
		if(a.compareTo(end) >= 0) {
			return false;
		}
		return true;
	}

	/**
	 * 加法
	 * 
	 * @param firstNum 第一个数值（用于和第二个数相加）
	 * @param secondNum 第二个数值
	 * @return 返回计算结果: 如果需要double类型：直接调用.doubleValue(); 如果需要String类型直接.toString()
	 */
	public static BigDecimal add(String firstNum, String secondNum) {
		// 得到BigDecimal
		BigDecimal bigDecimal = new BigDecimal(firstNum);

		return bigDecimal.add(new BigDecimal(secondNum));
	}

	/**
	 * 减法
	 * 
	 * @param firstNum 第一个数值（用于和第二个数相减）
	 * @param secondNum 第二个数值
	 * @return 返回计算结果: 如果需要double类型：直接调用.doubleValue(); 如果需要String类型直接.toString()
	 */
	public static BigDecimal subtract(String firstNum, String secondNum) {
		// 得到BigDecimal
		BigDecimal bigDecimal = new BigDecimal(firstNum);

		return bigDecimal.subtract(new BigDecimal(secondNum));
	}

	/**
	 * 乘法
	 * 
	 * @param firstNum 第一个数值（用于和第二个数相计算）
	 * @param secondNum 第二个数值
	 * @return 返回计算结果: 如果需要double类型：直接调用.doubleValue(); 如果需要String类型直接.toString()
	 */
	public static BigDecimal multiply(String firstNum, String secondNum) {
		// 得到BigDecimal
		BigDecimal bigDecimal = new BigDecimal(firstNum);

		return bigDecimal.multiply(new BigDecimal(secondNum));
	}

	/**
	 * 除法
	 * 
	 * @param firstNum 第一个数值（用于和第二个数相计算），
	 * @param secondNum 第二个数值
	 * @return 返回计算结果(firstNum/secondNum): 如果需要double类型：直接调用.doubleValue(); 如果需要String类型直接.toString()
	 */
	public static BigDecimal divide(String firstNum, String secondNum) {
		// 得到BigDecimal
		BigDecimal bigDecimal = new BigDecimal(firstNum);

		return bigDecimal.divide(new BigDecimal(secondNum));
	}

	/**
	 * 除法
	 *
	 * @param firstNum 第一个数值（用于和第二个数相计算），
	 * @param secondNum 第二个数值
	 * @return 返回计算结果(firstNum/secondNum): 如果需要double类型：直接调用.doubleValue(); 如果需要String类型直接.toString() 保留两位小数
	 */
	public static String divide2(long firstNum, double secondNum) {
		double value = firstNum / secondNum;
		String reuslt = transForLateNum2(value);
		return reuslt;
	}

	/**
	 * 将String类型转化为 BigDecimal
	 */
	public static BigDecimal transForLateNum(String mNum) {
		// 得到BigDecimal
		BigDecimal bigDecimal = new BigDecimal(mNum);
		return bigDecimal;
	}

	/**
	 * 将long类型转化为小数，保留两位数
	 */
	public static String transForLateNum(long mNumn) {
		BigDecimal b = new BigDecimal(mNumn);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(f1);
	}

	/**
	 * 将double类型转化为小数，保留两位数
	 */
	public static String transForLateNum2(double mNumn) {
		BigDecimal b = new BigDecimal(mNumn);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(f1);
	}
	
	/***
	 * 保留两位小数 num
	 */
	public static String saveDecimalTwo(double num) {
		String baifenbiTemp = "";
		DecimalFormat df1 = new DecimalFormat("0.00");
		baifenbiTemp = df1.format(num);
		return baifenbiTemp;
	}

	/**
	 * 两数相除，四舍五入
	 */
	public static int getMathRound(long time, double num) {
		double value = time / num;
		int result = (int) Math.round(value);
		return result;
	}
	public static int getMathRound(int num1, int num2) {
		double value = num1 / num2;
		int result = (int) Math.round(value);
		return result;
	}
}
