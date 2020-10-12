package com.fairmall.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2020/10/12 14:23
 * @Description: 日期工具类
 */
public class DateUtils {
    public static final String DATE_FMT_YMD = "yyyyMMdd";
    public static final String DATE_FMT_Y_M_D = "yyyy-MM-dd";
    public static final String DATE_FMT_YMDHMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String DATE_FMT_Y_M_D_HMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FMT_YMDHMS = "yyyyMMddHHmmss";
    public static final String DATE_FMT_YMDH = "yyyyMMddHH";
    public static final String BATCH_NUMBER_FMT_YMDHM = "yyMMddHHmm";
    public static final String DATE_FMT_HMS = "HHmmss";
    public static final String DATE_FMT_Y_M_D_HMS = DATE_FMT_Y_M_D_HMSS;
    public static final String DATE_SHORT_FMT_YMDHM = BATCH_NUMBER_FMT_YMDHM;
    public static final String DATE_SHORT_FMT_YMD = "yyMMdd";
    public static final String DATE_SHORT_FMT_YM = "yyMM";
    public static final String DATE_FMT_YM = "yyyyMM";
    public static final String DATE_FMT_YYYY = "yyyy";
    public static final String DATE_FMT_Y_M_D_HMSSS = "yyyy-MM-dd HH:mm:ss.SSS";

    private static long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;


    public static long getOneDayTimeMillseconds() {
        return MILLIS_PER_DAY;
    }

    /**
     * just use this method for test, in which case you need change a day length
     *
     * @param testValue
     */
    public static void setOneDayTimeMillSeconds(long testValue) {
        MILLIS_PER_DAY = testValue;
    }

    /**
     * Formats {@link java.util.Date} to string according to the specific format
     * passed in.
     *
     * @param date
     *            {@link java.util.Date}
     * @param format
     *            refer to {@link org.joda.time.format.DateTimeFormat}
     * @return the formatted date string.
     */
    public static String getDate(Date date, String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return dtf.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    /**
     * Formats {@link java.util.Date} to string as "yyyyMMdd".
     *
     * @param date
     *            {@link java.util.Date}
     * @return the formatted date string.
     */
    public static String getDate(Date date) {
        return getDate(date,DATE_FMT_YMD);
    }



    /**
     * Calculates months between two date.
     * @param from the from date
     * @param to
     *            the to date
     * @return the number of months
     */

    public static long daysBetween(Date startDate, Date endDate) {
        LocalDate startLocal = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocal = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(startLocal, endLocal);
    }


    public static long monthsBetween(Date startDate, Date endDate) {
        LocalDate startLocal = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocal = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.MONTHS.between(startLocal, endLocal);
    }



    public static long yearsBetween(Date startDate, Date endDate) {
        LocalDate startLocal = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocal = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.YEARS.between(startLocal, endLocal);
    }


    public static long hoursBetween(Date startDate, Date endDate) {
        LocalDateTime startLocal = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endLocal = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return ChronoUnit.HOURS.between(startLocal, endLocal);
    }

    public static long minutesBetween(Date startDate, Date endDate) {
        LocalDateTime startLocal = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endLocal = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return ChronoUnit.MINUTES.between(startLocal, endLocal);
    }

    public static long secondsBetween(Date startDate, Date endDate) {
        LocalDateTime startLocal = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endLocal = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return ChronoUnit.SECONDS.between(startLocal, endLocal);
    }


    public static Date getFutureNormalizedDay(int numOfdays) {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate date = LocalDate.ofYearDay(today.getYear(),numOfdays);
        ZonedDateTime zonedDateTime = date.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * Returns true if <code>targetDate</code> is in the range of [
     * <code>startDate</code>, <code>endDate</code>], otherwise false.
     *
     * @param startDate
     *            start date.
     * @param endDate
     *            end date.
     * @param targetDate
     *            the date to be calculated
     * @return true if <code>targetDate</code> is in the range of [
     *         <code>startDate</code>, <code>endDate</code>], otherwise false.
     */
    public static boolean isBetween(Date startDate, Date endDate,
                                    Date targetDate) {
        if (startDate == null || endDate == null || targetDate == null) {
            throw new NullPointerException("Parameter date shouldn't be null");
        }
        return startDate.getTime() <= targetDate.getTime()
                && targetDate.getTime() <= endDate.getTime();
    }

    /**
     * Returns the count of days between <code>startDate</code> and
     * <code>endDate</code>.
     *
     * @param startDate
     *            start date
     * @param endDate
     *            end date
     * @return the count of days between <code>startDate</code> and
     *         <code>endDate</code>.
     */
    public static long diffDays(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new NullPointerException("Parameter date shouldn't be null");
        }
        return (endDate.getTime() - startDate.getTime()) / MILLIS_PER_DAY;
    }

    /**
     * return the date of <code>days</code> before now.
     *
     * @param days
     * @return
     */
    public static Date daysBeforeNow(int days) {
        return Date.from(ZonedDateTime.now().minusDays(days).toInstant());
    }

    /**
     * return the date of <code>days</code> before <code>date</code>.
     *
     * @param date
     * @param days
     * @return
     */
    public static Date dateBefore(Date date, int days) {
        return Date.from(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).minusDays(days).toInstant());
    }

    /**
     * Formats {@link java.util.Date} to string as "yyyyMMddHHmmss".
     *
     * @param date
     *            {@link java.util.Date}
     * @return the formatted date string.
     */
    public static String getDateTime(Date date) {
        if(date==null) {
            return null;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FMT_YMDHMS);
        return dtf.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    /**
     * Formats {@link java.util.Date} to string as "yyyyMMddHHmmssSSS".
     *
     * @param date
     *            {@link java.util.Date}
     * @return the formatted date string.
     */
    public static String getDateTimeMillisecond(Date date) {
        if(date==null) {
            return null;
        }
        return getDate(date,DATE_FMT_YMDHMSSSSS);
    }

    /**
     * Formats {@link java.util.Date} to string as "HHmmss".
     *
     * @param date
     *            {@link java.util.Date}
     * @return the formatted date string.
     */
    public static String getHourMinuteOfTime(Date date) {
        if(date==null) {
            return null;
        }
        return  getDate(date,DATE_FMT_HMS);
    }

    /**
     * Formats {@link java.util.Date} to string as "yyyy-MM-dd HH:mm:ss".
     *
     * @param date
     *            {@link java.util.Date}
     * @return the formatted date string.
     */
    public static String getDateDetail(Date date) {
        if(date==null) {
            return null;
        }
        return  getDate(date,DATE_FMT_Y_M_D_HMSS);
    }

    public static Date addYears(Date date, int years) {
        return Date.from(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).plusYears(years).toInstant());
    }

    /**
     * Adds the specific months to date passed in.
     *
     * @param date
     *            {@link java.util.Date}
     * @param months
     *            the number of month
     * @return the new date plus the increased months
     */
    public static Date addMonths(Date date, int months) {
        return Date.from(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).plusMonths(months).toInstant());
    }

    /**
     * Adds the specific minutes to date passed in.
     *
     * @param date
     *            {@link java.util.Date}
     * @param minutes
     *            the number of minute
     * @return the new date plus the increased minutes
     */
    public static Date addMinutes(Date date, int minutes) {
        return Date.from(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).plusMinutes(minutes).toInstant());
    }

    public static Date addHours(Date date, int hours) {
        return Date.from(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).plusHours(hours).toInstant());
    }

    public static Date addSeconds(Date date, int seconds) {
        return Date.from(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).plusSeconds(seconds).toInstant());
    }

    /**
     * Parses a string formatted as "yyyy-MM-dd" to {@link java.util.Date}。
     *
     * @param time
     *            the time string
     * @return {@link java.util.Date} if succeed.
     * @throws UnsupportedOperationException
     *             if parsing is not supported
     * @throws IllegalArgumentException
     *             if the text to parse is invalid
     */
    public static Date parseDate(String time) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FMT_Y_M_D);
        LocalDateTime ldt = LocalDateTime.parse(time,dtf);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }


    public static String formatDate(Date date, String format) {
        if(date==null) {
            return null;
        }
        return getDate(date,format);
    }

    /**
     * Parses a time string to the format specified.
     *
     * @param time
     *            the time string
     * @param format
     *            refer to {@link org.joda.time.format.DateTimeFormat}
     * @throws UnsupportedOperationException
     *             if parsing is not supported
     * @throws IllegalArgumentException
     *             if the text to parse is invalid
     */
    public static Date parseDate(String time, String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime ldt = LocalDateTime.parse(time,dtf);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }



    public static Date daysAfterNow(int numOfdays) {
        return Date.from(ZonedDateTime.now().plusDays(numOfdays).toInstant());
    }

    public static Date monthsAfterNow(int num) {
        return Date.from(ZonedDateTime.now().plusMonths(num).toInstant());
    }

    // /////////////////////////////////////////
    // from SLDateUtils
    // ///////////////////////////////////////

    public static String getCurrentDate(String aFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(aFormat);
        return dtf.format(ZonedDateTime.now());
    }

    public static String getCurrentDate(String aFormat, long time) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(aFormat);
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(time),ZoneId.systemDefault());
        return dtf.format(ldt);
    }

    public static String getCurrentDate() {
        return getCurrentDate(DATE_FMT_YMD);
    }

    public static String getCurrentTime() {
        return getCurrentDate(DATE_FMT_HMS);
    }

    public static String getCurrentDateAndTime() {
        return getCurrentDate(DATE_FMT_YMDHMS);
    }


    public static int getDayOfYear() {
        return LocalDate.now().getDayOfYear();
    }

    public static int getDayOfMonth(Date date) {
        return LocalDate.now().getDayOfMonth();
    }



    public static boolean isWeekend(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
    }

    public static int getDayOfWeek(Date date) {
        return LocalDate.now().getDayOfWeek().getValue();
    }


    public static String getYearQuarterAndWeek(String date){

        LocalDate localDate = null;
        if(StringUtils.isEmpty(date)){
            localDate = LocalDate.now();
        }else{
            localDate = LocalDate.parse(date);
        }

        localDate = localDate.with(DayOfWeek.FRIDAY);
        String year = String.valueOf(localDate.getYear());
        String quarter = String.valueOf((localDate.getMonthValue()+2)/3);
        //获取当前季度第一天
        LocalDate lDate = getStartOrEndDayOfQuarter(localDate,true);
        //当前季度第一天是第几周
        WeekFields weekFields = WeekFields.of(DayOfWeek.SATURDAY,1);
        int qstartWeek = lDate.get(weekFields.weekOfYear());
        int nowWeek = localDate.get(weekFields.weekOfYear());
        int qWeekNo = nowWeek - qstartWeek +1;
        return year +"Q"+quarter+"W"+String.valueOf(qWeekNo);

    }


    public Date getStartDayOfWeek(TemporalAccessor date) {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate.with(fieldISO, 1);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    public Date getEndDayOfWeek(TemporalAccessor date) {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate.with(fieldISO, 7);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
    }

    private static LocalDate getStartOrEndDayOfQuarter(LocalDate today, Boolean isFirst){
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }
        Month month = today.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), firstMonthOfQuarter, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(today.isLeapYear()));
        }
        return resDate;
    }

    // public static void main(String[] args) {
    // 	String a = DateUtil.getYearQuarterAndWeek("2020-06-29");
    // 	System.out.println(a);
    // }
}
