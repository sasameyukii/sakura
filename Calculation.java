package com.psbc.interest;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/***
 * @author lqm
 * @version v0.0.1
 */

public class Calculation {
    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println(System.getProperty("file.encoding"));
        Count count = new Count();
//        long day = count.countDays();
        long day = 0;
        PrintStream ps = new PrintStream("D:\\1.txt"); //创建输出流
        System.setOut(ps);
        long numberSave = 0;
        double rate = 0.0005;
        String start = "2020-2-1";
        String end = "2021-1-1";
        while (true) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("请输入开始日期：");
            start = scanner1.next();
            System.out.println(start);
            System.out.println("请输入结束日期：");
            end = scanner1.next();
            System.out.println(end);
            day = count.countDays(start, end);
            if (day <= 0) {
                System.out.println("结束时间应大于起始时间，请重新输入");
            } else if (day == Integer.MAX_VALUE) {
            } else {
                break;
            }
        }
        while (true) {
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("请输入利率：");
            rate = scanner2.nextDouble();
            System.out.println(rate);
            if (rate < 0) {
                System.out.println("利率应为正数，请重新输入");
            } else {
                break;
            }
        }
        while(true) {
            Scanner scanner3 = new Scanner(System.in);
            System.out.println("请输入本金：");
            try {
                numberSave = scanner3.nextLong();
                long number = numberSave;
                System.out.println(numberSave);
                int bit = 0;
                if (number < 0) {
                    System.out.println("本金不能为负，请重新输入！");
                    continue;
                }
                do {
                    number = number / 10;
                    bit = bit + 1;
                } while (number > 0);
                if (bit < 13) {
                    break;
                } else {
                    System.out.println("本金位数不符合要求，请重新输入！");
                }
            } catch (InputMismatchException e) {
                System.out.println("您输入有误，请重试");
            }
        }
        long interest = count.countInterest(numberSave, rate, day);
        System.out.println("您的存款利息为：" + interest + "分");

        ps.close();
    }
}

class Count{
    public long countDays(){
        DateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        long countDays = 0;
        try {
            Date start = ymd.parse("2020-2-1");
            Date end = ymd.parse("2021-1-1");
            long startTime = start.getTime();
            long endTime = end.getTime();
            long num = endTime - startTime;
            System.out.println("存款天数为：" + num / 24 / 60 / 60 / 1000);
            countDays = num / 24 / 60 / 60 / 1000;
        } catch (ParseException e){
            e.printStackTrace();
        }
        return countDays;
    }

    public long countDays(String st, String en){
        DateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        ymd.setLenient(false); //判断日期是否合法
        Boolean flag = true;
        long countDays = 0;
        try {
            Date start = ymd.parse(st);
            Date end = ymd.parse(en);
//            System.out.println(start);
//            System.out.println(end);
            long startTime = start.getTime();
            long endTime = end.getTime();
            long num = endTime - startTime;
            System.out.println("您的存款自 " + ymd.format(start)
                    + " 起至" + ymd.format(end) + " 为止");
            System.out.println("总共的存款天数为：" + num / 24 / 60 / 60 / 1000 + "天");
            countDays = num / 24 / 60 / 60 / 1000;

        } catch (ParseException e) {
            System.out.println("日期格式错误，请重新输入！");
            countDays = Integer.MAX_VALUE;
        }

        return countDays;
    }

    public long countInterest(long number, double rate, long day) {
        double interest = 0;
        interest = number * rate * day * 100;
        interest = Math.ceil(interest);
        return (long) interest;
    }
}


