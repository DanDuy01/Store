/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import models.DiningTable;
import models.User;

/**
 *
 * @author ThinkPro
 */
public class Order {
    private int id;
    private int customer_id;
    private int table_id;
    private String status;
    private String order_datetime;
    private int total_cost;
    private User customer;
    private DiningTable table;

    public DiningTable getTable() {
        return table;
    }

    public void setTable(DiningTable table) {
        this.table = table;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_datetime() {
        return order_datetime;
    }

    public void setOrder_datetime(String order_datetime) {
        this.order_datetime = order_datetime;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }
    
    

    public Order() {
    }

    public Order(int id, int customer_id, int table_id, String status, String order_datetime, int total_cost) {
        this.id = id;
        this.customer_id = customer_id;
        this.table_id = table_id;
        this.status = status;
        this.order_datetime = order_datetime;
        this.total_cost = total_cost;
    }
    
}
