package com.ems.ems.API.ClientPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aldmar on 08/12/2017.
 */

public class Client {

    @SerializedName("businessTag")
    @Expose
    private String businessTag;
    @SerializedName("clientId")
    @Expose
    private Integer clientId;
    @SerializedName("clientName")
    @Expose
    private String clientName;
    @SerializedName("locations")
    @Expose
    private List<Locations> locations = null;

    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<Locations> getLocations() {
        return locations;
    }

    public void setLocations(List<Locations> locations) {
        this.locations = locations;
    }


}

