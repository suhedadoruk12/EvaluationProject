package com.abas.evaluationProject.model;


public class DetailModel {
    private String number;
    private Integer amount;
    private float price;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public DetailModel(String number, Integer amount, float price) {
        this.number = number;
        this.amount = amount;
        this.price = price;
    }

    public DetailModel() {
    }
}
