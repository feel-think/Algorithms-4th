package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

public class SmartDate {

    private final int year;
    private final int month;
    private final int day;

    public SmartDate(int month, int day, int year) {
        // 判断日期是否合法
        if (!isLegalDate(month, day, year)) throw new RuntimeException("Illegal date!");
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public SmartDate(String s) {
        // 解析日期字符串 month/day/year
        String[] dateStrings = s.split("/");
        if (dateStrings.length != 3) throw new RuntimeException("Illegal date string: " + s);
        int[] date = new int[3];
        for (int i = 0; i < date.length; i++) {
            date[i] = Integer.parseInt(dateStrings[i]);
        }
        int month = date[0], day = date[1], year = date[2];
        // 判断日期是否合法
        if (!isLegalDate(month, day, year)) throw new RuntimeException("Illegal date!");
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private static boolean isLegalDate(int month, int day, int year) {
        // 先判断年份是否合法
        if (year < 0) return false;
        // 然后判断是否为闰年，根据是否为闰年，后面判断 2 月份日期的合法性会有所不同
        boolean isLeapYear = isLeapYear(year);
        // 判断月份是否合法
        if (month > 12 || month < 1) return false;
        // 判断日期是否合法
        if (month == 2) {
            if (isLeapYear && (day > 29 || day < 1))  return false;
            if (!isLeapYear && (day > 28 || day < 1)) return false;
        }
        else if ("135781012".indexOf(month + "") != -1) {
            if (day > 31 || day < 1) return false;
        }
        else {
            if (day > 30 || day < 1) return false;
        }
        return true;
    }

    private static boolean isLeapYear(int year) {
        // 年份可以被 4 整除但不能被 100 整除，或是年份可以被 400 整除
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public int year() {
        return year;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public String dayOfTheWeek() {
        // 12/31/1999 是周五，计算该日期对象相对于 12/31/1999 多了多少天，再计算是周几，假设日期为 21 世纪
        int days = 0;
        // 累加 2000 年至 year - 1 年所包含的天数
        for (int i = 2000; i < year; i++) {
            if (isLeapYear(i)) days += 366;
            else               days += 365;
        }
        // 累加 year 年里 month 月之前的天数
        for (int i = 1; i < month; i++) {
            if (i == 2) {
                if (isLeapYear(year)) days += 29;
                else                  days += 28;
            }
            else if ("1357810".indexOf(i + "") != -1) {
                days += 31;
            }
            else {
                days += 30;
            }
        }
        // 累加 month 月内的天数
        days += day;
        int weekDay = (days % 7 + 5) % 7;
        String[] week = {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        return week[weekDay];
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public static void main(String[] args) {
        SmartDate date;
//        date = new SmartDate(6, 31, 2009);
//        date = new SmartDate(2, 29, 2009);
//        date = new SmartDate(2, 29, 2004);
        date = new SmartDate(10, 18, 2017);
        StdOut.printf("Month of the date: %d\n", date.month());
        StdOut.printf("Day of the date: %d\n", date.day());
        StdOut.printf("Year of the date: %d\n", date.year());
        StdOut.println(date.dayOfTheWeek());
        date = new SmartDate("10/20/2017");
        StdOut.println(date);
    }

}
