package com.soft863.salary;

import java.io.IOException;
import java.util.List;

/**
 * @author LiZeyu
 * @date ${DATA} 15:15
 **/
public interface Util {
    void writeLog(String path, String content);
    List<String> readLines(String path) throws IOException;
}
