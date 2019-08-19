package com.yang.region;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yang.common.httputil.HttpUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:Region</p>
 * <p>Description:</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 *
 * @author Yang Xiang
 * @date 2019/8/19 11:40
 */
public class Region {

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        generateSql();
        System.out.println("总耗时：" + ((System.currentTimeMillis() - startTime) / 1000) + "秒");
    }

    // 访问域名
    private final static String HOST = "https://ali-city.showapi.com";
    // 根据名称查区域
    private final static String AREA_DETAIL = "/areaDetail";
    // 根据ID查询子区域
    private final static String AREA_NAME = "/areaName";
    // 请求方式
    private final static String REQUEST_METHOD = "GET";
    // appCode
    private final static String APP_CODE = "2134c8ca1f2c4d3180f1c7bab0b80b0d";
    // 头部数据
    private final static Map<String, String> HEADERS = new HashMap<>();
    // 参数
    private final static Map<String, String> QUERYS = new HashMap<>();
    // 省份
    private final static List<String> PROVINCES = new ArrayList<>();
    // 生成sql的存储容器
    private final static StringBuilder SQL_BUILDER = new StringBuilder();
    private final static String SQL_FILE_PATH = "D:/region.sql";

    static {
        // 初始化请求头部信息
        initHeaders();
        // 初始化省份数据
        initProvinces();
        // 初始化SQL脚本
        initRegionSql();
    }

    private static void generateSql() throws Exception {
        JSONArray provinceJsonArray;
        JSONArray cityJsonArray;
        String cities;
        String districts;
        for (String name : PROVINCES) {
            // 获取到省份数据
            provinceJsonArray = JSONArray.parseArray(getProvince(name));
            doGenerateSql(provinceJsonArray);
            // 通过省份id查询城市数据
            cities = getByParentId(provinceJsonArray.getJSONObject(0).getString("id"));
            if (StringUtils.isBlank(cities)) {
                throw new Exception("获取城市失败：" + name + "," + provinceJsonArray.getJSONObject(0).getString("id"));
            }
            // 获取到城市数据
            cityJsonArray = JSONArray.parseArray(cities);
            doGenerateSql(cityJsonArray);
            for (int i = 0; i < cityJsonArray.size(); i++) {
                // 通过城市id查询区县数据
                districts = getByParentId(cityJsonArray.getJSONObject(i).getString("id"));
                if (StringUtils.isNotBlank(districts)) {
                    doGenerateSql(JSONArray.parseArray(districts));
                }
                System.out.println(provinceJsonArray.getJSONObject(0).getString("areaName") + ","
                        + cityJsonArray.getJSONObject(i).getString("areaName") + " - 添加完毕...");
            }
            System.out.println(name + " - 添加完毕!");
        }
        // 生成SQL脚本文件
        generateSqlFile(SQL_BUILDER.toString(), SQL_FILE_PATH);
        System.out.println("SQL脚本文件生成完毕...");
    }

    private static void doGenerateSql(JSONArray jsonArray) {
        JSONObject data;
        String level;
        String simpleName;
        for (int i = 0; i < jsonArray.size(); i++) {
            data = jsonArray.getJSONObject(i);
            level = data.getString("level");
            simpleName = data.getString("simpleName");
            // 名称为空的数据 为老数据
            if (!"4".equals(level)
                    && StringUtils.isNotBlank(simpleName)) {
                SQL_BUILDER.append("INSERT INTO `region` VALUES (")
                        .append("'").append(data.getString("id")).append("', ")
                        .append("'").append(data.getString("parentId")).append("', ")
                        .append("'").append(simpleName).append("', ")
                        .append("'").append(data.getString("areaName")).append("', ")
                        .append("'").append(data.getString("wholeName")).append("', ")
                        .append("'").append(level).append("', ")
                        .append("'").append(data.getString("simplePy")).append("', ")
                        .append("'").append(data.getString("prePinYin")).append("', ")
                        .append("'").append(delSingleQuotes(data.getString("pinYin"))).append("', ")
                        .append("'").append(data.getString("areaCode")).append("', ")
                        .append("'").append(data.getString("zipCode")).append("', ")
                        .append("'").append(numberOfString(data.getString("lon"))).append("', ")
                        .append("'").append(numberOfString(data.getString("lat"))).append("'")
                        .append(");\n");
            }
        }
    }

    private static String numberOfString(String value) {
        return (StringUtils.isBlank(value)) ? "0.000000" : value;
    }

    // 删除单引号
    private static String delSingleQuotes(String value) {
        return (StringUtils.isNotBlank(value) && value.contains("'")) ? value.replace("'", "") : value;
    }

    private static void generateSqlFile(String content, String path) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(content.getBytes(Charset.forName("UTF-8")));
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

    private static String getProvince(String name) {
        return getByNameAndLevel(name, "1");
    }

    private static String getByNameAndLevel(String name, String level) {
        QUERYS.clear();
        QUERYS.put("areaName", name);
        QUERYS.put("level", level);
        QUERYS.put("maxSize", "50");
        QUERYS.put("page", "1");
        return request(AREA_NAME);
    }

    private static String getByParentId(String parentId) {
        QUERYS.clear();
        QUERYS.put("parentId", parentId);
        return request(AREA_DETAIL);
    }

    private static String request(String path) {
        try {
            HttpResponse response = HttpUtils.doGet(HOST, path, REQUEST_METHOD, HEADERS, QUERYS);
            JSONObject entity = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            if (entity == null) {
                System.out.println("查询数据失败----");
                return null;
            }
            JSONObject jsonObject = JSONObject.parseObject(entity.getString("showapi_res_body"));
            Boolean flag = jsonObject.getBoolean("flag");
            if (!BooleanUtils.isTrue(flag)) {
                System.out.println("查询数据失败：" + jsonObject.getString("msg"));
                return null;
            }
            return jsonObject.getString("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
                .append("  `lat` DECIMAL(10,6) DEFAULT NULL COMMENT '纬度'\n")
                .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区信息表';\n");
    }

    private static void initHeaders() {
        HEADERS.put("Authorization", "APPCODE " + APP_CODE);
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
