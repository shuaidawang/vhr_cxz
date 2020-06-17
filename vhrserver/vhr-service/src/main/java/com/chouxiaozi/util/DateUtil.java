package com.chouxiaozi.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    /**
     * 获取日期之间相差n年
     * @param from
     * @param to
     * @return
     */
    public static double getBetweenYears(Date from, Date to){
        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
        LocalDate formD = date2LocalDate(from);
        LocalDate toD = date2LocalDate(to);
        Period p = Period.between(formD, toD);
        double years = p.getYears();
        double month = Double.valueOf(df.format((double) p.getMonths()/12));
        return years+month;
    }

    public static LocalDate date2LocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2019-09-02");
        Date date2 = simpleDateFormat.parse("2022-12-31");
        System.out.println(getBetweenYears(date,date2));
    }

}
