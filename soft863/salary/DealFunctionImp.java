package com.soft863.salary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LiZeyu
 * @date ${DATA} 9:19
 **/
public class DealFunctionImp implements Function {

    @Override
    public double formatDouble(double value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


    @Override
    public List<Work> averageSalary(List<Work> works) {

        //任务二：对每条数据进行处理，算出其平均薪资
        String[] str;
        for (Work work : works) {

            //处理以千为单位
            if (work.getSalary().contains("千") || work.getSalary().contains("K")) {
                Pattern pattern1 = Pattern.compile("[5-9]\\d+-[5-9]\\d+");
                Matcher matcher1 = pattern1.matcher(work.getSalary());
                if (matcher1.find()) {
                    str = matcher1.group().split("-");
                    Double min = Double.valueOf(str[0]);
                    Double max = Double.valueOf(str[1]);
                    double average = formatDouble((min + max) / 2);
                    work.setSalary(average * 1000 + "");

                }
            }

            //处理以万元/元为单位的
            Pattern pattern = Pattern.compile("([0-9]*\\.?[0-9]+)-([0-9]*\\.?[0-9]+)");
            Matcher matcher = pattern.matcher(work.getSalary());
            if (matcher.find()) {
                str = matcher.group().split("-");
                Double min = Double.valueOf(str[0]);
                Double max = Double.valueOf(str[1]);
                double average = formatDouble((min + max) / 2);

                if (average > 100000) {
                    work.setSalary((formatDouble(average / 12) + ""));
                } else if (average < 70 && average > 10) {
                    work.setSalary(formatDouble(average * 10000 / 12) + "");
                } else if (average < 10 && average > 0) {
                    work.setSalary(formatDouble(average * 10000) + "");
                } else
                    work.setSalary(formatDouble(average) + "");
            } else {
                pattern = Pattern.compile("[0-9]*");
                matcher = pattern.matcher(work.getSalary());
                if (matcher.find()) {
                    double daySalary = Double.valueOf(matcher.group());
                    if (daySalary < 2000 && daySalary > 500) {
                        work.setSalary(formatDouble(daySalary * 30) + "");
                    }
                    work.setSalary(matcher.group());
                }
            }
        }
        //输出清洗后各个岗位的信息
        String afterData="";
        for (Work work : works) {
            afterData=afterData+"\r\n职位：" + work.getEmployement() + ",公司：" + work.getFirm() + ",经验：" + work.getExperience() + ",薪资：" + work.getSalary() + ",城市:" + work.getAddress();
        }
        new IOUtilImp().writeLog("D:\\after_Deal.txt",afterData);
        return works;
    }

    @Override
    public double dataEISalary(List<Work> works) {
        List<Double> list = new ArrayList();
        for (Work work : works) {

            double salary = Double.valueOf(work.getSalary());

            list.add(formatDouble(salary));
        }
        double allSalary = 0.0;
        for (int i = 0; i < list.size(); i++) {
            allSalary += list.get(i);
        }
        if (list.size() < 1) return 0;
        return formatDouble(allSalary / list.size());
    }

    @Override
    public double perSalary(List<Double> salarys) {
        double allSalary = 0.0;
        for (int i = 0; i < salarys.size(); i++) {
            allSalary += salarys.get(i);
        }
        if (salarys.size() < 1) return 0;
        return formatDouble(allSalary / salarys.size());
    }


}
