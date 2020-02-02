package com.binod.model;

public class Income {

    private String name;
    private Integer amount;
    private String category;
    private String account;
    private String date;
    private String description;

    public Income(String name, Integer amount, String category, String account, String date, String description) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.account = account;
        this.date = date;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
