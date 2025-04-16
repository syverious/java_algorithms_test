package com.test.codeIterator;

/**
 * xml SQL语句自动生成
 */
public class CodeIterator2 {

    public static void main(String[] args){
        //selectAs();
        //updateSQL();
        //insertVal();
        fullSql();
    }

    private static final String codePatternBak = "#{id,jdbcType=VARCHAR},\n" +
            "            #{createUserId,jdbcType=VARCHAR},\n" +
            "            #{createDate,jdbcType=TIMESTAMP},\n" +
            "            #{updateUserId,jdbcType=VARCHAR},\n" +
            "            #{updateDate,jdbcType=TIMESTAMP},\n" +
            "            #{isvalid,jdbcType=VARCHAR},\n" +
            "            #{note,jdbcType=VARCHAR},\n" +
            "            #{lb,jdbcType=VARCHAR},\n" +
            "            #{xzqmc,jdbcType=VARCHAR},\n" +
            "            #{xzqdm,jdbcType=VARCHAR},\n" +
            "            #{mj,jdbcType=VARCHAR},\n" +
            "            #{rq,jdbcType=TIMESTAMP},\n" +
            "            #{pc,jdbcType=VARCHAR}";

    private static final String codePatternBak2 = "#{id,jdbcType=VARCHAR}, \n" +
            "#{createUserId,jdbcType=VARCHAR}, \n" +
            "#{createDate,jdbcType=TIMESTAMP}, \n" +
            "#{updateUserId,jdbcType=VARCHAR}, \n" +
            "#{updateDate,jdbcType=TIMESTAMP}, \n" +
            "#{isvalid,jdbcType=VARCHAR}, \n" +
            "#{note,jdbcType=VARCHAR}, \n" +
            "#{lb,jdbcType=VARCHAR}, \n" +
            "#{xmmc,jdbcType=VARCHAR}, \n" +
            "#{mj,jdbcType=VARCHAR}, \n" +
            "#{sy,jdbcType=VARCHAR}, \n" +
            "#{rq,jdbcType=TIMESTAMP}, \n" +
            "#{pc,jdbcType=VARCHAR}, \n" +
            "#{zt,jdbcType=VARCHAR}, \n" +
            "#{xzqmc,jdbcType=VARCHAR}, \n" +
            "#{xzqdm,jdbcType=VARCHAR}";

    // insert 转 merge select
    public static String selectAs(String itemName){
        String out = "";
        int start = -1;
        int end = -1;
        for(int i = 0; i < codePattern.length(); i++){
            out += codePattern.charAt(i);
            if(i > 0 && codePattern.charAt(i)=='{' && codePattern.charAt(i-1)=='#'){
                out += itemName + ".";
                start = i+1;
            } else if(start!=-1){
                if(codePattern.charAt(i)==','){
                    end = i;
                }
                else if(codePattern.charAt(i)=='}'){
                    out += " as " + codePattern.substring(start, end);
                    start = -1;
                }
            }
        }
        System.out.println(out);
        return out;
    }


    // insert 转 merge select
    public static String updateSQL(String exclude){
        String out = "";
        int start = -1;
        int end = -1;
        for(int i = 0; i < codePattern.length(); i++){
            //out += codePattern.charAt(i);
            if(i > 0 && codePattern.charAt(i)=='{' && codePattern.charAt(i-1)=='#'){
                start = i+1;
            } else if(start!=-1){
                if(codePattern.charAt(i)==','){
                    end = i;
                }
                else if(codePattern.charAt(i)=='}'){
                    String columnName = codePattern.substring(start, end);
                    // Merge 语句不更新 ON 语句引用列
                    if(!exclude.contains(columnName) && !exclude.toUpperCase().contains(columnName)){
                        out += "a." + columnName + " = " + "b." + columnName + ", ";
                        start = -1;
                    }
                }
            }
        }
        // 根据情况需要处理逗号
        if(",".equals(out.indexOf(out.length()-1))) out = out.substring(0, out.length()-1);
        System.out.println(out);
        return out;
    }

    // insert 转插入值
    public static String insertVal(){
        String out = "";
        int start = -1;
        int end = -1;
        for(int i = 0; i < codePattern.length(); i++){
            //out += codePattern.charAt(i);
            if(i > 0 && codePattern.charAt(i)=='{' && codePattern.charAt(i-1)=='#'){
                start = i+1;
            } else if(start!=-1){
                if(codePattern.charAt(i)==','){
                    end = i;
                }
                else if(codePattern.charAt(i)=='}'){
                    String tableName = codePattern.substring(start, end);
                    out += "b." + tableName + ", ";
                    start = -1;
                }
            }
        }
        out = out.substring(0, out.length()-2);
        System.out.println(out);
        return out;
    }

    // 字段模板
    private static final String codePattern = "#{id,jdbcType=VARCHAR}, \n" +
            "#{create_user_id,jdbcType=VARCHAR}, \n" +
            "#{create_date,jdbcType=TIMESTAMP}, \n" +
            "#{update_user_id,jdbcType=VARCHAR}, \n" +
            "#{update_date,jdbcType=TIMESTAMP}, \n" +
            "#{isvalid,jdbcType=VARCHAR}, \n" +
            "#{note,jdbcType=VARCHAR}, \n" +
            "#{lb,jdbcType=VARCHAR}, \n" +
            "#{xmmc,jdbcType=VARCHAR}, \n" +
            "#{mj,jdbcType=VARCHAR}, \n" +
            "#{sy,jdbcType=VARCHAR}, \n" +
            "#{rq,jdbcType=TIMESTAMP}, \n" +
            "#{pc,jdbcType=VARCHAR}, \n" +
            "#{zt,jdbcType=VARCHAR}, \n" +
            "#{xzqmc,jdbcType=VARCHAR}, \n" +
            "#{xzqdm,jdbcType=VARCHAR}";

    // insertBatchMerge 语句模板
    public static String fullSql = "    <insert id=\"insertBatchMerge\" >\n" +
            "        MERGE INTO {table} a\n" +
            "        USING (\n" +
            "        <foreach collection=\"entities\" item=\"{itemName}\" index=\"index\" separator=\"union all\">\n" +
            "            (SELECT\n" +
            "             {select}" +
            "            FROM DUAL\n" +
            "            )\n" +
            "        </foreach>\n" +
            "        ) b\n" +
            "        ON (a.xzqdm = b.xzqdm AND a.lb = b.lb)\n" +
            "        WHEN MATCHED THEN\n" +
            "        UPDATE SET\n" +
            "        {update}\n" +
            "        WHERE a.xzqdm = b.xzqdm AND a.lb = b.lb\n" +
            "        WHEN NOT MATCHED THEN\n" +
            "        INSERT({columns})VALUES(\n" +
            "        {insert}" +
            "        )\n" +
            "    </insert>";

    public static String fullSql(){
        String out = "";
        String tableName = "JHZB_GJZB";
        String columns = "ID, CREATE_USER_ID, CREATE_DATE, UPDATE_USER_ID, UPDATE_DATE, ISVALID, NOTE, LB, XMMC, MJ, SY, RQ, PC, ZT, XZQMC, XZQDM";
        tableName = "JHZB_JCZB";
        columns = "ID, CREATE_USER_ID, CREATE_DATE, UPDATE_USER_ID, UPDATE_DATE, ISVALID, NOTE, LB, XMMC, MJ, SY, RQ, PC, ZT, XZQMC, XZQDM";
        String itemName = "item";
        out = fullSql;
        out = out.replace("{table}", tableName);
        out = out.replace("{itemName}", itemName);
        out = out.replace("{select}", selectAs(itemName));
        out = out.replace("{update}", updateSQL("xzqdm,lb"));
        out = out.replace("{columns}", columns);
        out = out.replace("{insert}", insertVal());
        System.out.println();
        System.out.println(out);
        return out;
    }

}
