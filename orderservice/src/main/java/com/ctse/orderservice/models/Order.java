package com.ctse.orderservice.models;

public class Order {
    private Integer id;
    private String item;
    private Integer quantity;
    private String customerId;
    private String status;

    public Order() {}

    public Order(Integer id, String item, Integer quantity, String customerId, String status) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.customerId = customerId;
        this.status = status;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}