package com.yang.game;

import java.util.List;
import java.util.Map;

/**
 * 题目
 */
public class Condition {

    // 题目id
    private Integer id;
    // 题目名称
    private String name;
    // 题目内容
    private String content;
    // 题目选择
    private Map<Integer, Choice> choices;
    // 是否是终点
    private Boolean ended = Boolean.FALSE;
    // 结局 当ended为true时
    private Ending ending;

    public Ending getEnding() {
        return ending;
    }

    public void setEnding(Ending ending) {
        this.ending = ending;
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

    public Map<Integer, Choice> getChoices() {
        return choices;
    }

    public void setChoices(Map<Integer, Choice> choices) {
        this.choices = choices;
    }

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", choices=" + choices +
                ", ended=" + ended +
                ", ending=" + ending +
                '}';
    }
}
