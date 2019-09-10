package com.soft863.salary;

import java.util.HashMap;
import java.util.List;

/**
 * @author LiZeyu
 * @date ${DATA} 14:19
 **/
public interface EmploySalary {
    public HashMap<String,List<Double>> doEmploySalary(List<Work> works);
}
