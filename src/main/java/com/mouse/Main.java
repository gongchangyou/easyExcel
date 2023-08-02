package com.mouse;

import com.mouse.utils.ExcelUtils;

import java.util.List;
import java.util.Map;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        List<ExcelData> list = ExcelUtils.getListFromResourceFile("test.xlsx", ExcelData.class);
        System.out.println(list);

        Map<String, ExcelData> map = ExcelUtils.getMapFromResourceFile("test.xlsx", ExcelData.class, true);
        System.out.println(map);

        Map<String, ExcelData> map1 = ExcelUtils.getMapFromResourceFile("test.xlsx", ExcelData.class, false);
        System.out.println(map1);
    }
}