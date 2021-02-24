package com.yang.region;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yang.common.httputil.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p>Description:幽灵api 省市区乡镇</p>
 *
 * @author Yang Xiang
 * @date 2021/2/24 9:52
 */
public class RegionYLing {

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        generateSql();
        System.out.println("总耗时：" + ((System.currentTimeMillis() - startTime) / 1000) + "秒");
    }

    // 访问域名
    private final static String URL = "http://area.ylapi.cn/ad_area/division_de.u";
    // key
//    private final static String KEY = "7fee610e8bbcafe5d840cb7d668d770d";
//    private final static String U_ID = "11516";
    private final static String KEY = "ecf00925693fc2090d9e3ecbc24bfda8";
    private final static String U_ID = "11518";
    // 生成sql的存储容器
    private final static StringBuffer STRING_BUFFER = new StringBuffer();
    private final static String ZERO = "0000000000000000";
    private final static Integer ID_LEN = "130637000000".length();
    private final static String SQL_FILE_PATH = "D:/region_full_yling22.sql";

    static {
        // 初始化SQL脚本
        initRegionSql();
    }

    private static void generateSql() throws Exception {
        // 查询省份
        doGenerateSql(request("0"), 1,"");
        // 生成SQL脚本文件
        generateSqlFile(STRING_BUFFER.toString(), SQL_FILE_PATH);
        System.out.println("SQL脚本文件生成完毕...");
    }

    private static void doGenerateSql(JSONArray jsonArray, Integer level, String mergerName) {
        if (jsonArray == null) {
            return;
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject data = jsonArray.getJSONObject(i);
            String name = data.getString("areaName");
            String id = data.getString("id");
            String zipCode = data.getString("zipCode");
            zipCode = StringUtils.isBlank(zipCode) ? "" : zipCode;
            String mn = StringUtils.isNotBlank(mergerName) ? mergerName + "," + name : name;
            STRING_BUFFER.append("INSERT INTO `region` VALUES (")
                    .append("'").append(id).append("', ")
                    .append("'").append(data.getString("parentId")).append("', ")
                    .append("'").append(getSimpleName(name)).append("', ")
                    .append("'").append(name).append("', ")
                    .append("'").append(mn).append("', ")
                    .append("'").append(level).append("', ")
                    .append("'").append(data.getString("prePinYin")).append("', ")
                    .append("'").append(data.getString("simplePy")).append("', ")
                    .append("'").append(data.getString("pinYin")).append("', ")
                    .append("'").append("").append("', ")
                    .append("'").append(zipCode).append("', ")
                    .append("'").append(numberOfString(data.getString("lon"))).append("', ")
                    .append("'").append(numberOfString(data.getString("lat"))).append("'")
                    .append(");\n");
            if (level < 4) {
                System.out.println(mn + "   -- 已添加...");
                doGenerateSql(request(id), level + 1, mn);
            }
        }
    }

    private static String numberOfString(String value) {
        return (StringUtils.isBlank(value)) ? "0.000000" : value;
    }

    private static void generateSqlFile(String content, String path) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static JSONArray request(String parentId) {
        try {
            Thread.sleep(200);
            String url = URL + "?uid=" + U_ID + "&appkey=" + KEY;
            if (StringUtils.isNotBlank(parentId)) {
                url = url + "&parentId=" + parentId;
            }
            String json = HttpClientUtils.sendGet(url, null);
            JSONObject jsonObject = JSONObject.parseObject(json);
            Integer code = jsonObject.getInteger("code");
            if (code == 202) {
                System.out.println("请求过于频繁，休息10秒....==================，parentId=" + parentId);
                Thread.sleep(10000);
                request(parentId);
            }
            return jsonObject.getJSONArray("datas");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getSimpleName(String name) {
        if (name.endsWith("省") || name.endsWith("市") || name.endsWith("区") || name.endsWith("县") || name.endsWith("乡") || name.endsWith("镇")) {
            return name.substring(0, name.length() - 1);
        }
        if (name.endsWith("街道")) {
            return name.substring(0, name.length() - 2);
        }
        return name;
    }

    private static void initRegionSql() {
        STRING_BUFFER.append("CREATE TABLE `region` (\n")
                .append("  `id` BIGINT(20) NOT NULL COMMENT 'ID',\n")
                .append("  `parent_id` BIGINT(20) DEFAULT NULL COMMENT '父id',\n")
                .append("  `short_name` VARCHAR(100) DEFAULT NULL COMMENT '简称',\n")
                .append("  `name` VARCHAR(100) DEFAULT NULL COMMENT '名称',\n")
                .append("  `merger_name` VARCHAR(100) DEFAULT NULL COMMENT '完整名称',\n")
                .append("  `level` TINYINT(1) DEFAULT NULL COMMENT '级别省市区 1为省级，2为市级，3为区县级',\n")
                .append("  `pre_pinyin` VARCHAR(50) DEFAULT NULL COMMENT '拼音的第一个字母',\n")
                .append("  `simple_py` VARCHAR(50) DEFAULT NULL COMMENT '首字母',\n")
                .append("  `pinyin` VARCHAR(50) DEFAULT NULL COMMENT '拼音',\n")
                .append("  `code` VARCHAR(50) DEFAULT NULL COMMENT '长途区号',\n")
                .append("  `zip_code` VARCHAR(50) DEFAULT NULL COMMENT '邮编',\n")
                .append("  `lng` DECIMAL(10,6) DEFAULT NULL COMMENT '经度',\n")
                .append("  `lat` DECIMAL(10,6) DEFAULT NULL COMMENT '纬度',\n")
                .append("  PRIMARY KEY (`id`)\n")
                .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区信息表';\n");
    }
}
