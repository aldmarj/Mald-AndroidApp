package com.ems.ems.API;

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
    private String clientID;

    @SerializedName("description")
    private String description;

    @SerializedName("endTime")
    private String endTime;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("userName")
    private String username;

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

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   // public String getWorkLogId() {
       // return workLogId;
    //}

    //public void setWorkLogId(String workLogId) {
       // this.workLogId = workLogId;
    //}

}
