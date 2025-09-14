package com.star.customer.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caolp
 */
public class SortTestService {
    public static void main(String[] args) {
        List<Long> timestampList = new ArrayList<>();

        // 将提供的时间戳添加到列表中
        timestampList.add(1420073700000L);
        timestampList.add(1609459800000L);
        timestampList.add(1230770040000L);
        timestampList.add(1388536980000L);
        timestampList.add(1136075700000L);
        timestampList.add(1546303680000L);
        timestampList.add(1357001760000L);
        timestampList.add(1640996640000L);
        timestampList.add(1325378280000L);
        timestampList.add(1514766120000L);
        timestampList.add(978308040000L);
        timestampList.add(1009844220000L);
        timestampList.add(1293840780000L);
        timestampList.add(1640997660000L);
        timestampList.add(1451608920000L);
        timestampList.add(1546302240000L);
        timestampList.add(1546304040000L);
        timestampList.add(1104538320000L);
        timestampList.add(1230771060000L);
        timestampList.add(1041380160000L);
        timestampList.add(1230771360000L);
        timestampList.add(1451607660000L);
        timestampList.add(1640998200000L);
        timestampList.add(978309360000L);
        timestampList.add(1009846560000L);
        timestampList.add(1199146620000L);
        timestampList.add(946685460000L);
        timestampList.add(1072917840000L);
        timestampList.add(1136074680000L);
        timestampList.add(1546302360000L);
        timestampList.add(1357000980000L);
        timestampList.add(1609462020000L);
        timestampList.add(1672533420000L);
        timestampList.add(1640998560000L);
        timestampList.add(1577839920000L);
        timestampList.add(1420072200000L);
        timestampList.add(978308280000L);
        timestampList.add(1230768900000L);
        timestampList.add(1136074920000L);
        timestampList.add(946685640000L);
        timestampList.add(978307800000L);
        timestampList.add(1451607720000L);
        timestampList.add(1325379240000L);
        timestampList.add(1420072440000L);
        timestampList.add(1451608500000L);
        timestampList.add(1672533720000L);
        timestampList.add(1356999600000L);
        timestampList.add(1388537880000L);
        timestampList.add(1672533480000L);
        timestampList.add(1546302540000L);

        // 输出列表内容
        System.out.println("Timestamp List: " + timestampList);

        // 计算并输出两两相减的结果
        for (int i = 0; i < timestampList.size(); i++) {
            for (int j = i + 1; j < timestampList.size(); j++) {
                long numA = timestampList.get(i);
                long numB = timestampList.get(j);

                long aMinusB = numA - numB;
                long bMinusA = numB - numA;

                System.out.println(numA + " - " + numB + ": " + aMinusB);
                System.out.println(numB + " - " + numA + ": " + bMinusA);
            }
        }
    }



}
