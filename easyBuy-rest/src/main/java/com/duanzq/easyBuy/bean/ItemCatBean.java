package com.duanzq.easyBuy.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by duanzq on 16/6/29.
 * 商品类别Bean
 */
public class ItemCatBean {
    /*JsonProperty注解用于生成转化后json字符串的key*/
    @JsonProperty("u")
    private String url;
    @JsonProperty("n")
    private String name;
    @JsonProperty("i")
    private List items;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
