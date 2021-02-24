package com.yang.region;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yang.common.httputil.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.yang.common.PinYinTools.*;

/**
 * <p>Description:腾讯 省市区乡镇</p>
 *
 * @author Yang Xiang
 * @date 2021/2/24 9:52
 */
public class RegionTX {

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        generateSql();
        System.out.println("总耗时：" + ((System.currentTimeMillis() - startTime) / 1000) + "秒");
    }

    // 访问域名
    private final static String URL = "https://apis.map.qq.com/ws/district/v1/getchildren";
    // key
    private final static String KEY = "EDYBZ-ZCBCS-KVGOZ-6GKZ7-CCCU6-XQFJT";
    // 省份
    private final static List<String> PROVINCES = new ArrayList<>();
    // 生成sql的存储容器
    private final static StringBuffer STRING_BUFFER = new StringBuffer();
    private final static String ZERO = "0000000000000000";
    private final static Integer ID_LEN = "130637000000".length();
    private final static String SQL_FILE_PATH = "D:/region_full.sql";

    static {
        // 初始化省份数据
        initProvinces();
        // 初始化SQL脚本
        initRegionSql();
    }

    private static void generateSql() throws Exception {
        // 查询省份
        doGenerateSql(request(""), "0", "", 1);
        // 生成SQL脚本文件
        generateSqlFile(STRING_BUFFER.toString(), SQL_FILE_PATH);
        System.out.println("SQL脚本文件生成完毕...");
    }

    private static void doGenerateSql(JSONArray jsonArray, String parentId, String mergerName, Integer level) {
        if (jsonArray == null) {
            return;
        }
        jsonArray = jsonArray.getJSONArray(0);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject data = jsonArray.getJSONObject(i);
            String name = data.getString("name");
            String fullname = data.getString("fullname");
            name = StringUtils.isBlank(name) ? getSimpleName(fullname) : name;
            String dataId = data.getString("id");
            String id = fullZeroId(dataId);
            String mn = StringUtils.isNotBlank(mergerName) ? mergerName + "," + fullname : fullname;
            JSONObject location = data.getJSONObject("location");
            STRING_BUFFER.append("INSERT INTO `region` VALUES (")
                    .append("'").append(id).append("', ")
                    .append("'").append(parentId).append("', ")
                    .append("'").append(name).append("', ")
                    .append("'").append(fullname).append("', ")
                    .append("'").append(mn).append("', ")
                    .append("'").append(level).append("', ")
                    .append("'").append(getFirstLettersUp(name)).append("', ")
                    .append("'").append(getFirstLetter(name)).append("', ")
                    .append("'").append(chineseToSpell(name)).append("', ")
                    .append("'").append("").append("', ")
                    .append("'").append("").append("', ")
                    .append("'").append(numberOfString(location.getString("lng"))).append("', ")
                    .append("'").append(numberOfString(location.getString("lat"))).append("'")
                    .append(");\n");
            if (level < 3) {
                System.out.println(mn + "   -- 已添加...");
            }
            if (level < 4) {
                doGenerateSql(request(dataId), id, mn, level + 1);
            }
        }
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

    private static String fullZeroId(String value) {
        return (Integer.parseInt(value) + ZERO).substring(0, ID_LEN);
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

    private static JSONArray request(String id) {
        try {
            String url = URL + "?key=" + KEY;
            if (StringUtils.isNotBlank(id)) {
                url = url + "&id=" + id;
            }
            String json = HttpClientUtils.sendGet(url, null);
            JSONObject jsonObject = JSONObject.parseObject(json);
            if (jsonObject.getInteger("status") == 120) {
                Thread.sleep(201);
                return request(id);
            }
            return jsonObject.getJSONArray("result");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
}
