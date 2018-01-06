package com.ems.ems.API.EmployeePojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aldmar on 05/01/2018.
 */

public class Account {
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("businessTag")
    @Expose
    private String businessTag;
    @SerializedName("email")
    @Expose
    private Object email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }
}
