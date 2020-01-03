package com.yang.game;

/**
 * 选择
 */
public class Choice {

    // 选择号
    private Integer id;
    // 内容
    private String content;
    // 跳转的题目号
    private Integer skipConditionId;
    // 所属题目号
    private Integer conditionId;

    public Choice(Integer conditionId, Integer id, String content, Integer skipConditionId) {
        this.conditionId = conditionId;
        this.id = id;
        this.content = content;
        this.skipConditionId = skipConditionId;
    }

    public Integer getConditionId() {
        return conditionId;
    }

    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSkipConditionId() {
        return skipConditionId;
    }

    public void setSkipConditionId(Integer skipConditionId) {
        this.skipConditionId = skipConditionId;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", skipConditionId=" + skipConditionId +
                ", conditionId=" + conditionId +
                '}';
    }
}
