package com.stanbond.homerecipeorganizer.DAO.entites;

public class UserRole {
    private long userId;
    private long roleId;

    public UserRole() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
