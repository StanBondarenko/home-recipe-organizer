package com.stanbond.homerecipeorganizer.DAO.entites;

public class RecipeIng {
    private long recId;
    private long ingId;
    private double amount;
    private long unitId;
    private double amountBase;

    public RecipeIng() {
    }

    public long getRecId() {
        return recId;
    }

    public void setRecId(long recId) {
        this.recId = recId;
    }

    public long getIngId() {
        return ingId;
    }

    public void setIngId(long ingId) {
        this.ingId = ingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }

    public double getAmountBase() {
        return amountBase;
    }

    public void setAmountBase(double amountBase) {
        this.amountBase = amountBase;
    }
}
