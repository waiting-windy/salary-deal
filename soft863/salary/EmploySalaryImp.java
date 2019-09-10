package com.soft863.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LiZeyu
 * @date ${DATA} 14:17
 **/
public class EmploySalaryImp implements EmploySalary{

    @Override
    public HashMap<String,List<Double>> doEmploySalary(List<Work> works) {

        HashMap<String, List<Double>> hashMap_employ_salary = new HashMap<>();
        String employ;
        for (Work work : works) {
            employ = work.getEmployement();

            double countrySalary = Double.valueOf(work.getSalary());;
            if (!hashMap_employ_salary.containsKey(employ)) {
                List<Double> salary = new ArrayList<>();
                salary.add(countrySalary);
                hashMap_employ_salary.put(employ, salary);
            }
            else {
                hashMap_employ_salary.get(employ).add(countrySalary);
            }
        }
        return hashMap_employ_salary;
    }
    public void doSalaryTable(HashMap<String,List<Double>> hashMap_employ_salary){

        HashMap<String, Double> salaryTable = new HashMap<>();
        //遍历一对多的hashMap，形成一对一的工资表
        for (String T_employ : hashMap_employ_salary.keySet()) {
            double ep_to_slary = new DealFunctionImp().perSalary(hashMap_employ_salary.get(T_employ));
            salaryTable.put(T_employ,ep_to_slary);
        }
        //将全国工资表写入文件
        String employ_salary = "";
        for (String s : salaryTable.keySet()) {
            employ_salary = employ_salary + "\r\n职位：" + s + ",平均薪资：" + salaryTable.get(s);
        }
        new IOUtilImp().writeLog("D:\\all_salary_table.txt",employ_salary);

    }
}
