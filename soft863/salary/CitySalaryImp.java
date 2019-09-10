package com.soft863.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LiZeyu
 * @date ${DATA} 14:26
 **/
public class CitySalaryImp implements CitySalary {


    @Override
    public HashMap<String, List<Double>> doCitySalary(List<Work> works) {
        HashMap<String, List<Double>> hashMap_city_salary = new HashMap<>();
        String city;
        //将各个城市，对应的岗位存入HashMap，形成一对多的关系
        for (Work work : works) {
            city = work.getAddress();
            double employ_salary = Double.valueOf(work.getSalary());

            if (!hashMap_city_salary.containsKey(city)) {
                List<Double> salary = new ArrayList<>();
                salary.add(employ_salary);
                hashMap_city_salary.put(city, salary);
            } else {
                hashMap_city_salary.get(city).add(employ_salary);
            }
        }
        return hashMap_city_salary;
    }
    public void doSalaryTable(HashMap<String,List<Double>> hashMap_city_salary){
        HashMap<String, Double> salaryTable = new HashMap<>();      //城市薪资表
        for (String T_city : hashMap_city_salary.keySet()) {
            double city_salary = new DealFunctionImp().perSalary(hashMap_city_salary.get(T_city));
            salaryTable.put(T_city, city_salary);
        }

        String city_salary = "";
        for (String s : salaryTable.keySet()) {
            city_salary = city_salary + "城市：" + s + ",平均薪资：" + salaryTable.get(s)+"\r\n";
        }
        new IOUtilImp().writeLog("D:\\city_salary_table.txt", city_salary);
    }
}
