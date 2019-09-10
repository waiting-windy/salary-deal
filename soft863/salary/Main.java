package com.soft863.salary;

import java.io.IOException;

import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\salaryall.txt";
        IOUtilImp util = new IOUtilImp();
        List<String> salary = new ArrayList<String>();
        List<Work> works = new ArrayList<>();
        String[] str;

        //1.清洗数据
        salary = util.readLines(filePath);

        //将每条数据存入对象
        for (String s : salary) {
            str = s.split(",");
            Work work = new Work();
            work.setEmployement(str[0]);
            work.setFirm(str[1]);
            work.setExperience(str[2]);
            work.setSalary(str[3]);
            work.setAddress(str[4]);
            work.setCitycode(str[5]);
            works.add(work);
        }

        //2.进行平均薪资计算
        Function function = new DealFunctionImp();
        function.averageSalary(works);

        //3.输出大数据工程师的平均薪资

        System.out.println("大数据工程师的平均薪资"+function.dataEISalary(works));

        //4.全国各个岗位的平均薪资

        new EmploySalaryImp().doEmploySalary(works);

        //5.某个城市的平均薪资
        new CitySalaryImp().doCitySalary(works);

        //6.某个城市、某个岗位的平均薪资
        new CityEmploySalaryImp().doCityEmploySalary(works);
    }
}
