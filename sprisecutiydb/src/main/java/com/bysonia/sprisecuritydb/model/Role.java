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
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String roleName;
    private String roleDesc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", id=" + id +
        ", roleName=" + roleName +
        ", roleDesc=" + roleDesc +
        "}";
    }
}
