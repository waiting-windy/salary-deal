package com.soft863.salary;


import java.util.HashMap;
import java.util.List;

/**
 * @author LiZeyu
 * @date ${DATA} 9:17
 **/
 interface Function {
     double formatDouble(double value);
     List<Work> averageSalary(List<Work> works);
     double dataEISalary(List<Work> works);
     double perSalary(List<Double> salarys);
}
