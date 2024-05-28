package com.wits.grofast_user.Api.responseModels;

public class WalletModel {
    private Integer id;
    private String uuid;
    private Integer user_id;
    private Integer order_id;
    private Integer points;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public WalletModel(Integer id, String uuid, Integer user_id, Integer order_id, Integer points, Integer status) {
        this.id = id;
        this.uuid = uuid;
        this.user_id = user_id;
        this.order_id = order_id;
        this.points = points;
        this.status = status;
    }
}
