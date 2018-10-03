package com.cheng.pojo;

public class Orders {
    private String id;

    private String oredrNum;

    private String itemId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOredrNum() {
        return oredrNum;
    }

    public void setOredrNum(String oredrNum) {
        this.oredrNum = oredrNum == null ? null : oredrNum.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }
}