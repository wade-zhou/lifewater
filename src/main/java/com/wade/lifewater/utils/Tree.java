package com.wade.lifewater.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhouwei
 * @Date: 2018/6/1
 *
 * 无极菜单树
 */
public class Tree<T> {

    //节点ID
    private String id;

    //显示节点文本
    private String text;

    //节点的子节点
    private List<Tree<T>> children = new ArrayList<Tree<T>>();

    //父节点ID
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
