package com.ems.ems.API.EmployeePojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aldmar on 05/01/2018.
 */

public class Employee {
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("surName")
    @Expose
    private String surName;
    @SerializedName("parentUserName")
    @Expose
    private Object parentUserName;
    @SerializedName("jobRole")
    @Expose
    private Object jobRole;
    @SerializedName("account")
    @Expose
    private Account account;
    @SerializedName("hoursWorked")
    @Expose
    private Integer hoursWorked;
    @SerializedName("businessTag")
    @Expose
    private String businessTag;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Object getParentUserName() {
        return parentUserName;
    }

    public void setParentUserName(Object parentUserName) {
        this.parentUserName = parentUserName;
    }

    public Object getJobRole() {
        return jobRole;
    }

    public void setJobRole(Object jobRole) {
        this.jobRole = jobRole;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag;
    }
}
