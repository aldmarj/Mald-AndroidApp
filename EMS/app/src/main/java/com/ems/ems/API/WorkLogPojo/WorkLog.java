package com.ems.ems.API.WorkLogPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by aldmar on 11/12/2017.
 */

public class WorkLog {

    @SerializedName("businessTag")
    @Expose
    private String businessTag;
    @SerializedName("clientId")
    @Expose
    private String clientId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("endTime")
    @Expose
    private Long endTime;
    @SerializedName("startTime")
    @Expose
    private Long startTime;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("workLogId")
    @Expose
    private Integer workLogId;

    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getWorkLogId() {
        return workLogId;
    }

    public void setWorkLogId(Integer workLogId) {
        this.workLogId = workLogId;
    }

}
