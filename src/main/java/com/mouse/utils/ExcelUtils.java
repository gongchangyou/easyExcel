package com.mouse.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private static String getFilePathFromResource(String fileName) {
        ClassLoader classLoader = ExcelUtils.class.getClassLoader();
        URL resourceURL = classLoader.getResource(fileName);
        return resourceURL.getPath();
    }

    public static <T> Map<String, T> getMapFromResourceFile(String fileName, Class<T> clazz, boolean isOverride) {
        return getMapFromFile(getFilePathFromResource(fileName), clazz, isOverride);
    }

    public static <T> List<T> getListFromResourceFile(String fileName, Class<T> clazz) {
        return getListFromFile(getFilePathFromResource(fileName), clazz);
    }
    public static <T> List<T> getListFromFile(String filePath, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        EasyExcel.read(filePath, clazz, new ReadListener<T>() {

            @Override
            public void invoke(T data, AnalysisContext context) {
                list.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }
        }).sheet().doRead();

        return list;
    }
    public static <T> Map<String, T>  getMapFromFile(String filePath, Class<T> clazz, boolean isOverride) {
        Map<String, T> map = new LinkedHashMap<>();
        EasyExcel.read(filePath, clazz, new ReadListener<T>() {

            @Override
            public void invoke(T data, AnalysisContext context) {
                //get key
                List<String> keyList = new ArrayList<>();
                Field[] fields = data.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.getAnnotation(Key.class) != null) {
                        field.setAccessible(true);
                        try {
                            keyList.add(field.get(data).toString());
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if (keyList.size() == 0) {
                    throw new RuntimeException("no key" + data);
                } else {
                    String key = String.join(":", keyList);
                    if (isOverride) {
                        map.put(key, data);
                    } else {
                        if (map.containsKey(key)) {
                            throw new RuntimeException("duplicate key=" + key + " data:" +data);
                        } else {
                            map.put(key, data);
                        }
                    }
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }
        }).sheet().doRead();

        return map;
    }

}
