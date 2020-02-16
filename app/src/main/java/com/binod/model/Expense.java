package com.binod.model;

import android.content.SharedPreferences;

import java.util.Date;

public class Expense {
    private String name;
    private Integer amount;
    private String category;
    private String account;
    private String days;
    private String months;
    private String years;
    private String description;

    public Expense(String name, Integer amount, String category, String account, String days, String months, String years, String description) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.account = account;
        this.days = days;
        this.months = months;
        this.years = years;
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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
