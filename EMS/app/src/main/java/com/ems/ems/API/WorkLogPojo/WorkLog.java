package com.ems.ems.API.WorkLogPojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aldmar on 11/12/2017.
 */

public class WorkLog {

    @SerializedName("businessTag")
    private String businessTag;

    // @SerializedName("clientName")
    //private String clientName;

    @SerializedName("clientId")
    private String clientId;

    @SerializedName("description")
    private String description;

    @SerializedName("endTime")
    private String endTime;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("userName")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // @SerializedName("workLogId")
    //private String workLogId;

    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag;
    }

    //public String getClientName() {
    //   return clientName;
    //}

    //public void setClientName(String clientName) {
    //    this.clientName = clientName;
    //}

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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    // public String getWorkLogId() {
    // return workLogId;
    //}

    //public void setWorkLogId(String workLogId) {
    // this.workLogId = workLogId;
    //}

}
