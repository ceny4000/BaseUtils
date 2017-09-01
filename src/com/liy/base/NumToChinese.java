package com.liy.base;

import java.math.BigDecimal;

/**
 * NumToChinese 數字轉中文金額
 *
 * @version 1.0 
 * @author Liyard
 */
public class NumToChinese {

	private static String chineseNum[] = { "零", "壹", "貳", "參", "肆", "伍", "陸", "柒", "捌", "玖" };
	private static String xx[] = { "拾", "佰", "仟" };
	private static String yy[] = { "萬", "億", "兆" };
	private static String suffix = "元整";

	public static String getChineseNumber(BigDecimal number) {
		String msg = "";
		if (number == null)
			return msg;
		long v = number.abs().longValue();
		int i, temp;
		boolean first = true, zero = false;
		for (i = 0; (int) (v / (long) Math.pow(10, i)) != 0; i++)
			;
		if (i == 0)
			return chineseNum[0];
		for (int k = (i - 1) / 4; k >= 0; k--) {
			temp = (int) ((v / (long) Math.pow(10, 4 * k)) % (int) Math.pow(10,
					4));
			if (temp == 0) {
				zero = true;
				continue;
			}
			for (int x = 3; x >= 0; x--) {
				if (first) {
					x = (i - 1) % 4;
					first = false;
				}
				if (temp / (int) Math.pow(10, x) % 10 == 0)
					zero = true;
				else {
					if (zero) {
						msg += chineseNum[0];
						zero = false;
					}
					msg += chineseNum[temp / (int) Math.pow(10, x) % 10];
					if (x > 0)
						msg += xx[x - 1];
				}
			}
			if (k > 0)
				msg = msg + yy[k - 1];
		}
		msg = msg + suffix;
		if (number.compareTo(BigDecimal.ZERO) < 0)
			msg = "(負)" + msg;
		return msg;
	}
}
