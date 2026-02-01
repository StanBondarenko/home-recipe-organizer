package com.stanbond.homerecipeorganizer.DAO.entites;

public class UserIngredientViewDto {

    private String ingName;
    private java.math.BigDecimal amount;
    private String unitCode;

    public UserIngredientViewDto() {}

    public UserIngredientViewDto(String ingName, java.math.BigDecimal amount, String unitCode) {
        this.ingName = ingName;
        this.amount = amount;
        this.unitCode = unitCode;
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
