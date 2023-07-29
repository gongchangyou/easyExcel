package com.mouse;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
public class ExcelData {
    @ExcelProperty(index = 0,value = "A")
    private int a;

    @ExcelProperty(index = 1, value = "B")
    private String chineseWord;

    @ExcelProperty(index = 2, value = "C")
    private double c;

    @ExcelProperty(index = 3, value = "D")
    private String englishWord;

    @ExcelProperty(index = 4, value = "时间")
    private String time;
}
