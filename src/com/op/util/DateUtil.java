package com.op.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：DateUtil   
* 类描述：   
* 创建人：Win Zhong   
* 创建时间：2015年11月5日 下午2:54:53   
* 修改人：Win Zhong   
* 修改时间：2015年11月5日 下午2:54:53   
* 修改备注：   
* @version    
*
 */
public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
	"yyyyMMdd");
	
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat(
	"yyyyMMddHHmmss");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private final static SimpleDateFormat sdfE = new SimpleDateFormat("E");
	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String YYYY_MM_DDgetDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String YYYY_MM_DDgetDay(Date date) {
		return sdfDay.format(date);
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * 获取YYYYMMDDHHmmss格式
	 * 
	 * @return
	 */
	public static String getTimes() {
		return sdfTimes.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime(Date date) {
		return sdfTime.format(date);
	}
	
	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		try {
			return sdfDay.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 格式化日期 YYYY_MM_DD
	 * 
	 * @return
	 */
	public static Date YYYY_MM_DDfomatDate(String date) {
		try {
			return sdfDay.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
	  /**
     * <li>功能描述：两个日期中间间隔的天数（不包括这两天在内）
     * @param beginDate
     * @param endDate
     * @return
     * long 
     */
    public static long getDaySub(Date beginDate,Date endDate){
        long day=0;
        day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        return day;
    }
    
    /**
     * 得到n天之后的日期  yyyy-MM-dd HH:mm:ss
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        String dateStr = sdfTime.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后的日期  yyyy-MM-dd
     * @param days
     * @return
     */
    public static String YYYY_MM_DDgetAfterDay(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        String dateStr = sdfDay.format(date);
        
        return dateStr;
    }    
    /**
     * 得到n天之后的日期  yyyyMMdd
     * @param days
     * @return
     */
    public static String YYYYMMDDgetAfterDay(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        String dateStr = sdfDays.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n小时之后的日期  yyyyMMdd HH:mm:ss
     * @param days
     * @return
     */
    public static String YYYYMMDDgetAfterHour(Date date,String hour) {
    	int hourInt = Integer.parseInt(hour);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.setTime(date);
        canlendar.add(Calendar.HOUR, hourInt); // 日期减 如果不够减会将月变动
        
        date = canlendar.getTime();
        String dateStr = sdfTime.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        String dateStr = sdfE.format(date);
        
        return dateStr;
    }
  
    
    /**
     * 转换日期为周几
     * @param days
     * @return
     */
    public static String getDayWeek(Date date) {
        
        String dateStr = sdfE.format(date);
        
        return dateStr;
    }
    
    /**
     * Solr 日期查询格式化
     * @param date
     * @return
     */
    public static String UTCFormat(Date date){
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd^hh:mm:ss");
		format.setTimeZone(timeZone);
		String temp = format.format(date).replace("^", "T");
		temp+="Z";
		return temp;
	}
    
    /**
     * 将传入的日期转换为"日期当天"的最后一秒.
     * @param date
     * @return
     */
    public static Date  ENDDayTime(Date date){
    	Calendar calendar=Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.HOUR_OF_DAY, 23);
    	calendar.set(Calendar.MINUTE, 59);
    	calendar.set(Calendar.SECOND, 59);
    	return calendar.getTime();
    }
    
    /**
     * 计算时间差   秒
     * @param begin 开始时间
     * @param end   结束时间
     * @return
     */
    public static long Sdifference(Date begin,Date end){
    	return (end.getTime()-begin.getTime())/1000;
    }
    
    /**
     * 得到n年之后的日期  yyyy-MM-dd
     * @param days
     * @return
     */
    public static String YYYY_MM_DDgetAfterYear(int count) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.YEAR, count); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        String dateStr = sdfDay.format(date);
        
        return dateStr;
    }   
    
    public static void main(String[] args) throws Exception{
    	/*System.out.println(getDays());
    	System.out.println(getAfterDayWeek("4"));
    	System.out.println(getDayWeek(new Date()));*/
    	
    	
    	Date endDate = sdfDay.parse("2015-12-16");
    	System.out.println(DateUtil.YYYYMMDDgetAfterDay("1"));
    	System.out.println(DateUtil.getDays());
    	for(int i = 0 ;i <10;i++){
    		System.out.println(DateUtil.getTimes()+ Tools.getRandomNum());
    	}
    }

}
