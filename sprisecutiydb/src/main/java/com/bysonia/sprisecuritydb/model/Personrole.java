package com.bysonia.sprisecuritydb.model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sonia
 * @since 2020-03-06
 */
public class Personrole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer peronId;
    private Integer roleId;


    public Integer getPeronId() {
        return peronId;
    }

    public void setPeronId(Integer peronId) {
        this.peronId = peronId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Personrole{" +
        ", peronId=" + peronId +
        ", roleId=" + roleId +
        "}";
    }
}
