package com.stanbond.homerecipeorganizer.DAO.entites;

public class DishIng {
    private String ingName;
    private double amount;
    private String code;

    public DishIng() {
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
