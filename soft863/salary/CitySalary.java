package com.soft863.salary;

import java.util.HashMap;
import java.util.List;

/**
 * @author LiZeyu
 * @date ${DATA} 9:00
 **/
public interface CitySalary {
    HashMap<String, List<Double>> doCitySalary(List<Work> works);


}
