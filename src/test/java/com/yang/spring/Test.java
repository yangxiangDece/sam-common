package com.yang.spring;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    private final static AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/Users/yangxiang/Downloads/resource.json");
        byte[] bytes = new byte[1024];
        int read = 0;
        StringBuilder builder = new StringBuilder();
        while ((read = fileInputStream.read(bytes)) > -1) {
            builder.append(new String(bytes, 0, read));
        }
        toSql(builder.toString());
    }

    private static void toSql(String json) {
        JSONArray jsonArray = JSONArray.parseArray(json);
        List<Resource> resources = new ArrayList<>(jsonArray.size());
        buildResources(jsonArray, null, "100", "100", resources, 1);
        StringBuilder builder = new StringBuilder();
        buildSql(resources, builder, new Date().getTime());
        System.out.println(builder.toString());
    }

    private static void buildSql(List<Resource> resources, StringBuilder builder, long now) {
        // id name parent_id code code_level permission type url show icon level sort desc u_server status creater create_time updater update_time
        //"INSERT INTO `resource` VALUES (4, 'OA工作流', 2 ,'10101', '101/10101', NULL , 0, '/oa/workflow' , 1, NULL, 2, 1, NULL, 1, 1, NULL, 1597458789461, NULL, NULL);";
        if (CollectionUtils.isEmpty(resources)) {
            return;
        }
        for (Resource re : resources) {
            String icon = StringUtils.isNotBlank(re.getIcon()) ? "'" + re.getIcon() + "'" : null;
            String name = StringUtils.isNotBlank(re.getName()) ? "'" + re.getName() + "'" : null;
            builder.append("INSERT INTO `resource` VALUES (")
                    .append(re.getId()).append(", ")
                    .append(name).append(", ")
                    .append(re.getParentId()).append(", ")
                    .append("'").append(re.getCode()).append("'").append(", ")
                    .append("'").append(re.getCodeLevel()).append("'").append(", ")
                    .append(re.getPermission()).append(", ")
                    .append(re.getType()).append(", ")
                    .append("'").append(re.getUrl()).append("'").append(", ")
                    .append(re.getShow() ? 1 : 0).append(", ")
                    .append(icon).append(", ")
                    .append(re.getLevel()).append(", ")
                    .append(re.getSort()).append(", ")
                    .append(re.getDesc()).append(", ")
                    .append(re.getUserver()).append(", ")
                    .append(1).append(", ")
                    .append((String) null).append(", ")
                    .append(now).append(", ")
                    .append((String) null).append(", ")
                    .append(now).append(");\n")
            ;
            buildSql(re.getChildren(), builder, now);
        }
    }

    private static void buildResources(JSONArray jsonArray, String parentId, String parentCode, String parentCodeLevel, List<Resource> resources, int level) {
        if (CollectionUtils.isEmpty(jsonArray)) {
            return;
        }
        long codeIncrement = Long.parseLong(parentCode + "01");
        int sort = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Resource resource = new Resource();
            resource.setId(String.valueOf(ATOMIC_INTEGER.getAndIncrement()));
            resource.setParentId(parentId);
            resource.setSort(++sort);
            resource.setShow(true);
            resource.setUserver(1);
            if (parentId == null) {
                resource.setCode(parentCode);
                resource.setCodeLevel(parentCode);
                parentCode = String.valueOf(Long.parseLong(parentCode) + 1);
            } else {
                resource.setCode(Long.toString(codeIncrement));
                resource.setCodeLevel(parentCodeLevel + "/" + resource.getCode());
                codeIncrement++;
            }
            resource.setLevel(level);
            resource.setUrl(jsonObject.getString("path"));
            resource.setType(jsonObject.getInteger("type"));
            JSONObject meta = jsonObject.getJSONObject("meta");
            if (meta != null) {
                resource.setName(meta.getString("title"));
                resource.setIcon(meta.getString("icon"));
            }
            resources.add(resource);
            JSONArray children = jsonObject.getJSONArray("children");
            buildResources(children, resource.getId(), resource.getCode(), resource.getCodeLevel(), resources, level + 1);
        }
    }
}