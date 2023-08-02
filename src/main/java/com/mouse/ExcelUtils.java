package com.mouse;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static <T> List<T> getListFromResourceFile(String fileName, Class<T> clazz) {
        ClassLoader classLoader = ExcelUtils.class.getClassLoader();
        URL resourceURL = classLoader.getResource(fileName);

        return getListFromFile(resourceURL.getPath(), clazz);
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
}
