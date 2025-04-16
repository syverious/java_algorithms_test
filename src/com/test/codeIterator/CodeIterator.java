package com.test.codeIterator;

/**
 * 代码生成器 (生成重复的迭代代码)
 * 2023-2-16
 */
public class CodeIterator {

    // 替换符(只要匹配到这个就替换)
    private static final String replacement = "${}";

    // 代码格式
    private static String codePattern = "    $('input[name=\"pscpt${}\"]').off().click(function() {\n" +
            "        if(clicked${}==-1){\n" +
            "            clicked${} = $(\"input[name='pscpt${}']:checked\").val();\n" +
            "        } else {\n" +
            "            $('input[name=\"pscpt${}\"][value=' + clicked${} + ']').attr('checked',false);\n" +
            "            clicked${} = -1;\n" +
            "        }\n" +
            "    });\n";

    private static String codePattern2 = "var clicked${} = -1;\n";

    private static String codePattern(){
        String result = "";

        for(int i = 1; i<=15; i ++){
            result += codePattern.replace(replacement, i+"");
        }

        System.out.println(result);
        return result;
    }

    public static void main(String args[]){

        //SQLGen();

        String filePath = "D:\\\\ltsjsp\\\\hd-local-file\\\\upload\\\\\\manager\\pdf\\20254\\14\\S2025000014\\2025_174461994752131736099177076.pdf";

        filePath = filePath.replace("\\\\", "\\");
        filePath = filePath.replace("\\\\", "\\");
        filePath = filePath.replace("\\", "/");

        String directory = filePath.substring(0, filePath.lastIndexOf("/"));
        String name = filePath.substring(filePath.lastIndexOf("/"));

        System.out.println(directory);
        System.out.println(name);

    }

    private static String SQLGen(){
        String sql = "";
        String tables = "DYAQ,DYIQ,FDCQ1,FDCQ2,HYSYQ,JSYDSYQ,LQ,NYDSYQ,TDSYQ,YGDJ,YYDJ,ZDJBXX,ZHJBXX,ZRZ";
        String line = "create table {$}_QLK as select * from {$};";
        line = "update {$}_QLK set qxdm = '360729';\n";  // 2024-11-23 10:15:42
        line = "update {$}_QLK set rksj = to_date('2025-03-13 16:38:29', 'yyyy-mm-dd hh24:mi:ss');\n";
        //line = "truncate table {$}_QLK;\n";
        //line = "INSERT INTO {$}_QLK SELECT * FROM {$};\n";
        String[] array = tables.split(",");
        for(String table: array){
            sql += line.replace("{$}", table);
        }
        System.out.println(sql);
        return sql;
    }

    // 使用清单、汇总表改成固定列展示，
    // 汇总表拆分出 可使用 与 已使用分表, 更新测试系统并配置页面。

}
