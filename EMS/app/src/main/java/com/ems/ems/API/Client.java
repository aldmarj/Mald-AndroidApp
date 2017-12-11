package com.ems.ems.API;

import android.preference.PreferenceManager;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aldmar on 08/12/2017.
 */

public class Client {

    @SerializedName("businessTag")
    private String businessTag;

    @SerializedName("clientName")
    private String clientName;

    @SerializedName("clientId")
    private String clientID;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag;
    }

}
