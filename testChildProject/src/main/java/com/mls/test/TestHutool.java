package com.mls.test;
import java.util.Date;

import cn.hutool.core.date.DateUtil;

public class TestHutool {

    public static void main(String[] args) {
        String dateStr = "2012-12-12 12:12:12";
        Date date = DateUtil.parse(dateStr);
        System.out.println(date);
    }
}

