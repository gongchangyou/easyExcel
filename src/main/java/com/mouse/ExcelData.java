package com.mouse;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.localdatetime.LocalDateTimeDateConverter;
import com.alibaba.excel.converters.localdatetime.LocalDateTimeNumberConverter;
import com.alibaba.excel.converters.localdatetime.LocalDateTimeStringConverter;
import com.mouse.utils.Key;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class ExcelData {
    @ExcelProperty("A")
    private int a;

    @ExcelProperty("B")
    @Key
    private String chineseWord;

    @ExcelProperty("C")
    private double c;

    @ExcelProperty("D")
    @Key
    private String englishWord;

    @ExcelProperty( value = "时间", converter = LocalDateTimeNumberConverter.class)
    private LocalDateTime time;
}
