package com.stanbond.homerecipeorganizer.DAO.entites;

public class UserIngredient {
    private long userId;
    private long ingId;
    private double amount;
    private long unitId;
    private Double amountBase;

    public UserIngredient() {}

    public UserIngredient(long userId, long ingId, double amount, long unitId, Double amountBase) {
        this.userId = userId;
        this.ingId = ingId;
        this.amount = amount;
        this.unitId = unitId;
        this.amountBase = amountBase;
    }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }

    public long getIngId() { return ingId; }
    public void setIngId(long ingId) { this.ingId = ingId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public long getUnitId() { return unitId; }
    public void setUnitId(long unitId) { this.unitId = unitId; }

    public Double getAmountBase() { return amountBase; }
    public void setAmountBase(Double amountBase) { this.amountBase = amountBase; }
}
