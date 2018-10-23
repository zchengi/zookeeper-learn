package com.cheng.item.pojo;

import java.io.Serializable;

/**
 * @author cheng
 *         2018/10/3 12:41
 */
public class Items implements Serializable {

    private static final long serialVersionUID = -4066810524302953948L;

    private String id;

    private String name;

    private Integer counts;

    private Integer buyCounts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getBuyCounts() {
        return buyCounts;
    }

    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
    }

}