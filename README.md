这是一个基于easyexcel的封装，可以返回excel的列表和键值对形式
```
List<ExcelData> list = ExcelUtils.getListFromResourceFile("test.xlsx", ExcelData.class);
System.out.println(list);

Map<String, ExcelData> map = ExcelUtils.getMapFromResourceFile("test.xlsx", ExcelData.class, true);
System.out.println(map);

Map<String, ExcelData> map1 = ExcelUtils.getMapFromResourceFile("test.xlsx", ExcelData.class, false);
System.out.println(map1);
```

如果要返回键值对，请在ExcelData 的 field上添加@Key注解，支持多个key，在map中会使用":"作为连接符拼接
```
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
```

其中 getMapFromResourceFile 第三个参数是 是否覆盖。

如果设置成true，相同key的值会被覆盖。

如果设置成false，那么当键值冲突时，会抛异常。

