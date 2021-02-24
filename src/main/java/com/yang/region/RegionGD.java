package com.yang.region;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yang.common.httputil.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.yang.common.PinYinTools.*;

/**
 * <p>Description:高德API 省市区镇</p>
 *
 * @author Yang Xiang
 * @date 2021/2/23 14:14
 */
public class RegionGD {

    // 地址
    private final static String ROOT_URL = "https://developer.amap.com/service/api/restapi";
    // 参数信息
    private final static Map<String, Object> paramMap = new HashMap<>();
    // 省份
    private final static List<String> PROVINCES = new ArrayList<>();
    // 生成sql的存储容器
    private final static StringBuilder SQL_BUILDER = new StringBuilder();
    private final static String SQL_FILE_PATH = "D:/region_full.sql";
    private final static Integer ID_LEN = "130637000000".length();
    private final static String ZERO = "0000000000000000";
    private final static String DISTRICTS = "districts";

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        generateSql();
        System.out.println("总耗时：" + ((System.currentTimeMillis() - startTime) / 1000) + "秒");
    }

    private static void generateSql() throws Exception {
        for (String name : PROVINCES) {
            doGenerateSql(JSONArray.parseArray(request(name)), "0", "");
            System.out.println(name + " --------------- 添加完毕!");
        }
        // 生成SQL脚本文件
        generateSqlFile(SQL_BUILDER.toString(), SQL_FILE_PATH);
        System.out.println("SQL脚本文件生成完毕...");
    }

    private static void doGenerateSql(JSONArray jsonArray, String parentId, String mName) {
        if (jsonArray == null) {
            return;
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject data = jsonArray.getJSONObject(i);
            String id = fullZeroId(data.getString("adcode"));
            String name = data.getString("name");
            int level = getLevel(data.getString("level"));
            String mergerName = StringUtils.isBlank(mName) ? name : mName + "," + name;
            String simpleName = getSimpleName(name);
            String[] centers = data.getString("center").split(",");
            String citycode = data.getString("citycode");
            if ("[]".equalsIgnoreCase(citycode)) {
                citycode = "";
            }
            SQL_BUILDER.append("INSERT INTO `region` VALUES (")
                    .append("'").append(id).append("', ")
                    .append("'").append(parentId).append("', ")
                    .append("'").append(simpleName).append("', ")
                    .append("'").append(name).append("', ")
                    .append("'").append(mergerName).append("', ")
                    .append("'").append(level).append("', ")
                    .append("'").append(getFirstLettersUp(simpleName)).append("', ")
                    .append("'").append(getFirstLetter(simpleName)).append("', ")
                    .append("'").append(chineseToSpell(simpleName)).append("', ")
                    .append("'").append(citycode).append("', ")
                    .append("'").append("', ")
                    .append("'").append(numberOfString(centers[0])).append("', ")
                    .append("'").append(numberOfString(centers[1])).append("'")
                    .append(");\n");
            System.out.println(mergerName + " - 已添加!");
            doGenerateSql(data.getJSONArray(DISTRICTS), id, mergerName);
        }
    }

    private static int getLevel(String level) {
        if (Objects.equals(level, "province")) {
            return 1;
        } else if (Objects.equals(level, "city")) {
            return 2;
        } else if (Objects.equals(level, "district")) {
            return 3;
        } else if (Objects.equals(level, "street")) {
            return 4;
        }
        return 0;
    }

    private static String fullZeroId(String value) {
        return (Integer.parseInt(value) + ZERO).substring(0, ID_LEN);
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

    static {
        // 初始化参数
        initParams();
        // 初始化省份数据
        initProvinces();
        // 初始化SQL脚本
        initRegionSql();
    }

    private static String request(String keywords) {
        try {
            String url = ROOT_URL + "?keywords=" + keywords + "&subdistrict=3&extensions=base";
            String json = HttpClientUtils.sendPost(url, paramMap);
            return JSONObject.parseObject(json).getJSONArray(DISTRICTS).toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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

    private static void initRegionSql() {
        SQL_BUILDER.append("CREATE TABLE `region` (\n")
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

    private static void initParams() {
        paramMap.put("type", "config/district");
        paramMap.put("version", "v3");
    }

    private static void initProvinces() {
        PROVINCES.add("山东");
        PROVINCES.add("江苏");
        PROVINCES.add("上海");
        PROVINCES.add("浙江");
        PROVINCES.add("安徽");
        PROVINCES.add("福建");
        PROVINCES.add("江西");
        PROVINCES.add("广东");
        PROVINCES.add("广西");
        PROVINCES.add("海南");
        PROVINCES.add("河南");
        PROVINCES.add("湖南");
        PROVINCES.add("天津");
        PROVINCES.add("湖北");
        PROVINCES.add("北京");
        PROVINCES.add("河北");
        PROVINCES.add("山西");
        PROVINCES.add("内蒙");
        PROVINCES.add("宁夏");
        PROVINCES.add("青海");
        PROVINCES.add("陕西");
        PROVINCES.add("甘肃");
        PROVINCES.add("新疆");
        PROVINCES.add("四川");
        PROVINCES.add("贵州");
        PROVINCES.add("云南");
        PROVINCES.add("重庆");
        PROVINCES.add("西藏");
        PROVINCES.add("辽宁");
        PROVINCES.add("吉林");
        PROVINCES.add("黑龙江");
        PROVINCES.add("香港");
        PROVINCES.add("澳门");
        PROVINCES.add("台湾");
    }

    private static String numberOfString(String value) {
        return (StringUtils.isBlank(value)) ? "0.000000" : value;
    }
}
