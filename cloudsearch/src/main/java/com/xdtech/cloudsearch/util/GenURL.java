package com.xdtech.cloudsearch.util;

import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;


/**
 * @author:Fire-King
 * @descript:地址生成器
 */

public class GenURL implements Generator {
	private PatternCompiler compiler = null;

	private Pattern pattern = null;

	private PatternMatcher matcher = null;

	private MatchResult results = null;

	private Pattern tagPa = makePattern("[^$]*(\\$\\{([^\\}^\\[^\\}^\\]]+)\\[([^\\]^\\}]+)\\]\\})");
	private Map<String, String> filterChar = new HashMap<String, String>();

	public GenURL() {
		compiler = new Perl5Compiler();
		matcher = new Perl5Matcher();
		filterChar.put("&", "\\&");
	}

	/**
	 * 生成正则
	 * 
	 * @param regex
	 * @return
	 */
	private static Pattern makePattern(String regex) {
		try {
			PatternCompiler cp = new Perl5Compiler();
			Pattern poro = null;
			poro = cp.compile(regex, Perl5Compiler.CASE_INSENSITIVE_MASK);
			return poro;
		} catch (MalformedPatternException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 进行匹配
	 * 
	 * @param input
	 * @param poro
	 * @return
	 */
	private MatchResult match(String input, Pattern poro) {
		PatternMatcher matcher = new Perl5Matcher();

		return matcher.matchesPrefix(input, poro) ? matcher.getMatch() : null;
	}

	/**
	 * 根据数学规则生成地址串
	 * 
	 * @param text
	 * @param UrlParserConext
	 *            context
	 * @return
	 * @throws Exception
	 */
	private List<String> genUrlbyRange(String text) throws Exception {
		List<String> nvalues = new ArrayList<String>();
		if (text != null && text.trim().length() > 0) {
			String[] arr = text.split("-");
			if (arr.length == 2) {
				int start = tryParseInt(arr[0], 0);
				int end = tryParseInt(arr[1], 0);
				int max = 100000;
				if (end - start > max) {
					throw new Exception("生成地址数量可能超过"+max+"，生成失败！");
				}
				for (int i = start; i <= end; i++) {
					nvalues.add(String.valueOf(i));
				}
			}
		}
		return nvalues;
	}

	/**
	 * @param v
	 * @param defaultv
	 * @return int
	 */
	private static int tryParseInt(String v, int defaultv) {
		try {
			defaultv = Integer.parseInt(v);
		} catch (Exception ex) {

		}
		return defaultv;
	}

	/**
	 * 根据枚举规则生成地址
	 * 
	 * @param url
	 * @param prep
	 * @return List<String>
	 */
	private List<String> genUrlbyEnum(String text) {
		List<String> nvalues = new ArrayList<String>();
		if (text != null && text.trim().length() > 0) {
			String[] arr = text.split("@");
			for (String s : arr) {
				s = s.trim();
				nvalues.add(s);

			}
		}
		return nvalues;

	}

	/***************************************************************************
	 * 根据日期格式生成地址
	 * 
	 * @param url
	 *            URL地址
	 * @param prep
	 *            预留字段
	 * @return 地址数组
	 */
	private List<String> genUrlByeDate(String text) {
		List<String> nvalues = new ArrayList<String>();
		if (text != null && text.trim().length() > 0) {
			String[] arr = text.split(":");
			SimpleDateFormat df = new SimpleDateFormat(arr[1]);

			Date theday = new Date();
			Date monthFirstDay = StaticsUtils.changeMonthFirstDay(theday);// 得到当前月份的第一天
			int datnum = StaticsUtils.getMonthDay(theday);// 得到当前月份的天数
			// Date weekFirstDay =
			// changeWeekFirstDay(theday);//得到当前周到第一天
			int weekDay = 7;// 每周天数
			Date quartFirstDay = changeQuartFirstDay(theday);// 本季度的第一天
			Date quartLastDay = StaticsUtils.changeQuartLastDay(theday);// 本季度最后一天
			int quartDayNum = (int) ((quartLastDay.getTime() - quartFirstDay.getTime()) / (24 * 3600 * 1000)) + 1;// 得到当前季度的天数
			Date yearLastDay = StaticsUtils.changeYearLastDay(theday);// 当前年
			// 的最后一天
			Date yearFirstDay = StaticsUtils.changeYearFirstDay(theday);// 当前年
			// 的第一天
			int yearDayNum = (int) ((yearLastDay.getTime() - yearFirstDay.getTime()) / (24 * 3600 * 1000)) + 1;// 得到当前季度的天数
			// 按照当天计算
			if (text.indexOf("day") > 0) {
				if (arr[0].equals("theday")) {
					nvalues.add(df.format(theday));
				} else if (arr[0].indexOf("+") > 0) {
					String[] arr1 = arr[0].split("\\+");
					for (int i = 0; i <= Integer.parseInt(arr1[1]); i++) {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
					}

				} else if (arr[0].indexOf("-") > 0) {
					String[] arr1 = arr[0].split("\\-");
					for (int i = 0; i <= Integer.parseInt(arr1[1]); i++) {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DATE, -i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
					}
				}
			}
			// 按照当月计算
			else if (text.indexOf("month") > 0) {
				if (arr[0].equals("themonth")) {
					for (int i = 0; i < datnum; i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(monthFirstDay.getYear() + 1900, monthFirstDay.getMonth(), monthFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
					}

				} else if (arr[0].indexOf("+") > 0) {
					String[] arr1 = arr[0].split("\\+");
					for (int i = 0; i < datnum + Integer.parseInt(arr1[1]); i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(monthFirstDay.getYear() + 1900, monthFirstDay.getMonth(), monthFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
					}

				} else if (arr[0].indexOf("-") > 0) {
					String[] arr1 = arr[0].split("\\-");
					for (int i = 0; i < datnum - Integer.parseInt(arr1[1]); i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(monthFirstDay.getYear() + 1900, monthFirstDay.getMonth(), monthFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
					}
				}

			}
			// 按照当周计算
			else if (text.indexOf("week") > 0) {
				if (arr[0].equals("theweek")) {
					for (int i = 0; i < weekDay; i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(quartFirstDay.getYear() + 1900, quartFirstDay.getMonth(), quartFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
					}
				} else if (arr[0].indexOf("+") > 0) {
					String[] arr1 = arr[0].split("\\+");
					for (int i = 0; i < weekDay + Integer.parseInt(arr1[1]); i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(quartFirstDay.getYear() + 1900, quartFirstDay.getMonth(), quartFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
						// System.out.println(df.format(changeWeekFirstDay(theday)));
					}

				} else if (arr[0].indexOf("-") > 0) {
					String[] arr1 = arr[0].split("\\-");
					for (int i = 0; i < weekDay - Integer.parseInt(arr1[1]); i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(quartFirstDay.getYear() + 1900, quartFirstDay.getMonth(), quartFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
					}
				}

			}
			// 按照当季度计算
			else if (text.indexOf("quart") > 0) {
				if (arr[0].equals("thequart")) {
					for (int i = 0; i < quartDayNum; i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(quartFirstDay.getYear() + 1900, quartFirstDay.getMonth(), quartFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
					}
				} else if (arr[0].indexOf("+") > 0) {
					String[] arr1 = arr[0].split("\\+");
					for (int i = 0; i < quartDayNum + Integer.parseInt(arr1[1]); i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(quartFirstDay.getYear() + 1900, quartFirstDay.getMonth(), quartFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
						// System.out.println(df.format(changeWeekFirstDay(theday)));
					}

				} else if (arr[0].indexOf("-") > 0) {
					String[] arr1 = arr[0].split("\\-");
					for (int i = 0; i < quartDayNum - Integer.parseInt(arr1[1]); i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(quartFirstDay.getYear() + 1900, quartFirstDay.getMonth(), quartFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
					}
				}
			}
			// 按照当年计算
			else if (text.indexOf("year") > 0) {
				if (arr[0].equals("theyear")) {
					for (int i = 0; i < yearDayNum; i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(yearFirstDay.getYear() + 1900, yearFirstDay.getMonth(), yearFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
					}
				} else if (arr[0].indexOf("+") > 0) {
					String[] arr1 = arr[0].split("\\+");
					for (int i = 0; i < yearDayNum + Integer.parseInt(arr1[1]); i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(yearFirstDay.getYear() + 1900, yearFirstDay.getMonth(), yearFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
						// System.out.println(df.format(changeWeekFirstDay(theday)));
					}

				} else if (arr[0].indexOf("-") > 0) {
					String[] arr1 = arr[0].split("\\-");
					for (int i = 0; i < yearDayNum - Integer.parseInt(arr1[1]); i++) {
						Calendar cal = Calendar.getInstance();
						cal.set(yearFirstDay.getYear() + 1900, yearFirstDay.getMonth(), yearFirstDay.getDate());
						cal.add(Calendar.DATE, i);
						String tenDaysAfterDate = df.format(cal.getTime());
						nvalues.add(tenDaysAfterDate);
					}
				}
			}
		}
		return nvalues;
	}

	/**
	 * 改变传入日期为本季度第一天
	 * 
	 * @param currentdate
	 * @param offset
	 * @return
	 */
	public static Date changeQuartFirstDay(Date currentdate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentdate);
		for (int i = 0; i < 3; i++) {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 改变传入日期为当前周的第一天
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date changeWeekFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH));
		calendar.set(Calendar.DAY_OF_WEEK, 1);

		return calendar.getTime();
	}

	/**
	 * @param source
	 * @param context
	 * @return
	 * @throws Exception 
	 */
	public String[] parse(String source) throws Exception {
		List<String> newUrl = new ArrayList<String>(2);
		Map<String, Boolean> charInclude = new HashMap<String, Boolean>();

		for (Map.Entry<String, String> entry : filterChar.entrySet()) {
			String include = entry.getKey();
			if (source.indexOf(include) >= 0) {
				charInclude.put(include, true);
				source = source.replace("&", "\\&");
			}
		}

		if (source == null || "".equals(source.trim())) {
			return new String[0];
		}
		source = source.trim();
		newUrl.add(source);

		parseResolve(newUrl);
		if (charInclude.size() > 0) {
			for (Map.Entry<String, Boolean> entry : charInclude.entrySet()) {
				String[] urls = newUrl.toArray(new String[newUrl.size()]);
				newUrl.clear();
				String include = entry.getKey();
				String repl = filterChar.get(include);
				if (ValidateObject.hasValue(repl)) {
					for (String url : urls) {
						newUrl.add(url.replace(repl, include));
					}
				}
			}
		}
		return newUrl.toArray(new String[newUrl.size()]);
	}

	/**
	 * resolve call and genertor the new urls
	 * 
	 * @param context
	 * @param newUrls
	 * @throws Exception 
	 */
	private void parseResolve(List<String> newUrls) throws Exception {
		MatchResult m = null;
		List<String> n_urls = new ArrayList<String>();
		for (String url : newUrls) {
			if ((m = match(url, tagPa)) != null) {
				String ruleName = m.group(2);
				List<String> values = null;
				if ("enum".equalsIgnoreCase(ruleName)) {
					values = genUrlbyEnum(m.group(3));
				}
				if ("date".equalsIgnoreCase(ruleName)) {
					values = genUrlByeDate(m.group(3));
				}
				if ("range".equalsIgnoreCase(ruleName)) {
					values = genUrlbyRange(m.group(3));
				}

				// genertor new values
				if (m != null && (values != null && values.size() > 0)) {

					String p1 = url.substring(0, m.beginOffset(1));
					String p2 = url.substring(m.beginOffset(1) + m.group(1).length());

					for (String v : values) {
						StringBuilder sb = new StringBuilder();
						sb.append(p1);
						sb.append(v);
						sb.append(p2);
						n_urls.add(sb.toString());
					}
				}
			}
		}
		// if genertor new urls the next
		if (n_urls.size() > 0) {
			newUrls.clear();
			newUrls.addAll(n_urls);
			parseResolve(newUrls);
		}

	}

	public static void main(String args[]) throws Exception {
		GenURL bo = new GenURL();
		String url = "http://club.dayoo.com/topic_list_www.dy?b=lhzq&page=${range[2-5]}&n=50";
		String[] newurls = bo.parse(url);
		int i = 0;
		for (String s : newurls) {
			s = s.replace("\\&", "&");
			System.out.println(s + " " + i++);
		}

	}

}
