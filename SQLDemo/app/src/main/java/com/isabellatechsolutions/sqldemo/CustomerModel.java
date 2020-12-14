package com.isabellatechsolutions.sqldemo;

public class CustomerModel {

    private int id;
    private String customerName;
    private int customerAge;
    private boolean isActive;

    // Constructor
    public CustomerModel() {
    }

    public CustomerModel(int id, String customerName, int customerAge, boolean isActive) {
        this.id = id;
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.isActive = isActive;
    }

//    Getter and Setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // To String Method


    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerAge=" + customerAge +
                ", isActive=" + isActive +
                '}';
    }
}
