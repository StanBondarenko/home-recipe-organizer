package com.stanbond.homerecipeorganizer.DAO.entites;

public class Ingredient {
    private long id;
    private String ingName;
public Ingredient(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }
}
