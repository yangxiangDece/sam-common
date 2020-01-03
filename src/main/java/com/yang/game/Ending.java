package com.yang.game;

/**
 * 结局
 */
public class Ending {

    // 结局id
    private Integer id;
    // 结局名称
    private String name;
    // 结局内容
    private String content;

    public Ending(Integer id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
