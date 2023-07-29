package com.mouse;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;

import java.net.URL;
import java.util.Map;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        String fileName = "test.xlsx";
        // 使用 ClassLoader 获取资源文件的 URL
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resourceURL = classLoader.getResource(fileName);
        EasyExcel.read(resourceURL.getPath(), ExcelData.class, new ReadListener<ExcelData>() {

            @Override
            public void onException(Exception exception, AnalysisContext context) throws Exception {
                ReadListener.super.onException(exception, context);
            }

            @Override
            public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
                ReadListener.super.invokeHead(headMap, context);
            }

            @Override
            public void invoke(ExcelData data, AnalysisContext context) {
                System.out.println(data);
            }

            @Override
            public void extra(CellExtra extra, AnalysisContext context) {
                ReadListener.super.extra(extra, context);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }

            @Override
            public boolean hasNext(AnalysisContext context) {
                return ReadListener.super.hasNext(context);
            }

        }).sheet().doRead();
    }
}