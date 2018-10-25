package com.house.house.common.bean;

import java.io.Serializable;

public class Record implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String account;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String des;

    /**
     * 
     */
    private String company;

    /**
     * record
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return account 
     */
    public String getAccount() {
        return account;
    }

    /**
     * 
     * @param account 
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 
     * @return password 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 
     * @return des 
     */
    public String getDes() {
        return des;
    }

    /**
     * 
     * @param des 
     */
    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    /**
     * 
     * @return company 
     */
    public String getCompany() {
        return company;
    }

    /**
     * 
     * @param company 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     *
     * @mbggenerated 2018-10-24
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", password=").append(password);
        sb.append(", des=").append(des);
        sb.append(", company=").append(company);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}