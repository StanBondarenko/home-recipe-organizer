package com.stanbond.homerecipeorganizer.DAO.entites;

public class UserFavorite {
    private long userId;
    private long recId;

    public UserFavorite() {}

    public UserFavorite(long userId, long recId) {
        this.userId = userId;
        this.recId = recId;
    }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }

    public long getRecId() { return recId; }
    public void setRecId(long recId) { this.recId = recId; }
}
