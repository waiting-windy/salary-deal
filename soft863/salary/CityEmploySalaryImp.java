package com.soft863.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LiZeyu
 * @date ${DATA} 14:38
 **/
public class CityEmploySalaryImp implements CityEmploySalary {
    @Override
    public void doCityEmploySalary(List<Work> works) {
        HashMap<String, HashMap<String, List<Double>>> hashMap_EC_salary = new HashMap<>();
        String city;
        //将各个城市，对应的岗位存入HashMap，形成一对多的关系
        for (Work work : works) {
            city = work.getAddress();
            double employ_salary = Double.valueOf(work.getSalary());
            List<Double> salary;
            String employ = work.getEmployement();
            salary = new ArrayList<>();
            HashMap<String, List<Double>> hashMap_employ_salary = new HashMap<>();
            //如果不含有主键“城市”，将空的工资表的HashMap以及空的工资List加入
            if (!hashMap_EC_salary.containsKey(city)) {
                salary.add(employ_salary);
                hashMap_employ_salary.put(employ, salary);
                hashMap_EC_salary.put(city, hashMap_employ_salary);
            //城市存在、职业不存在
            } else if (!hashMap_EC_salary.get(city).containsKey(employ)) {
                hashMap_employ_salary = hashMap_EC_salary.get(city);
                salary.add(employ_salary);
                hashMap_employ_salary.put(employ, salary);
                hashMap_EC_salary.put(city, hashMap_employ_salary);
            } else {
                //城市和职业都存在
                hashMap_employ_salary = hashMap_EC_salary.get(city);
                salary = hashMap_employ_salary.get(employ);
                salary.add(employ_salary);
                hashMap_employ_salary.put(employ, salary);
                hashMap_EC_salary.put(city, hashMap_employ_salary);
            }


        }

        //将薪水算出平均值存入新的数据结构存储
        HashMap<String, HashMap<String, Double>> citySalaryTable = new HashMap<>();
        for (
                String keyCity : hashMap_EC_salary.keySet()) {

            HashMap<String, Double> employSalary = new HashMap<>();
            HashMap<String, List<Double>> employ_table = hashMap_EC_salary.get(keyCity);
            double city_per_employ = 0;
            for (String keyEmploy : employ_table.keySet()) {
                city_per_employ = new DealFunctionImp().perSalary(employ_table.get(keyEmploy));
                employSalary.put(keyEmploy, city_per_employ);
                citySalaryTable.put(keyCity, employSalary);
            }
        }

        //存入文件
        String city_employ_salary = "";
        for (String city_stock : citySalaryTable.keySet()) {
            city_employ_salary = city_employ_salary + "城市：" + city_stock;
            for (String employ_stock : citySalaryTable.get(city_stock).keySet()) {
                city_employ_salary = city_employ_salary + "\r\n职位：" + employ_stock + "\t平均薪资：" + citySalaryTable.get(city_stock).get(employ_stock);
            }
            city_employ_salary = city_employ_salary+ "\r\n";
        }
        new IOUtilImp().writeLog("D:\\city_employ_salary_table.txt", city_employ_salary);

    }
}
