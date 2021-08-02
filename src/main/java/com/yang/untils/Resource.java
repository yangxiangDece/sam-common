package com.yang.untils;

import java.io.Serializable;
import java.util.List;

/**
 * 资源类（菜单、按钮）
 */
public class Resource implements Serializable {

    private static final long serialVersionUID = -6031138024785199196L;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 资源名称
     */
    private String name;
    /**
     * 父级id
     */
    private String parentId;
    /**
     * 资源编码
     */
    private String code;
    /**
     * 拥有权限字符串
     */
    private String permission;
    /**
     * 资源类型，0-菜单，1-按钮
     */
    private Integer type;
    /**
     * 资源连接地址
     */
    private String url;
    /**
     * 是否展示到前端
     * 0-不展示到前端。1-展示到前端
     */
    private Boolean show;
    /**
     * 资源类型，资源图标
     */
    private String icon;
    /**
     * 资源级别
     */
    private Integer level;
    /**
     * code层级 100/10001/1000101
     */
    private String codeLevel;
    /**
     * 资源顺序
     */
    private Integer sort;
    /**
     * 资源描述
     */
    private String desc;
    /**
     * 所属产品线
     */
    private Integer userver;
    /**
     * 子菜单列表
     */
    private List<Resource> children;

    /**
     * 父级菜单
     */
    private Resource parent;

    private Integer flowCode;

    private String tagName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(String codeLevel) {
        this.codeLevel = codeLevel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getUserver() {
        return userver;
    }

    public void setUserver(Integer userver) {
        this.userver = userver;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }

    public Resource getParent() {
        return parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
    }

    public Integer getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(Integer flowCode) {
        this.flowCode = flowCode;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", code='" + code + '\'' +
                ", permission='" + permission + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", show=" + show +
                ", icon='" + icon + '\'' +
                ", level=" + level +
                ", codeLevel='" + codeLevel + '\'' +
                ", sort=" + sort +
                ", desc='" + desc + '\'' +
                ", userver=" + userver +
                ", children=" + children +
                ", parent=" + parent +
                ", flowCode=" + flowCode +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
