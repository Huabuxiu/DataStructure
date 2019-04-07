package com.company;

import java.util.Comparator;

/**
 * @program: sortSingle
 * @description:    排序接口
 * @author: Huabuxiu
 * @create: 2019-04-03 23:45
 **/
public class LineComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        o1 = o1.trim();
        o2 = o2.trim();
        // 从小到大
        return Integer.parseInt(o1) - Integer.parseInt(o2);
    }
}
