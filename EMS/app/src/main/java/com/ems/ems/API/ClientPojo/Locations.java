package com.ems.ems.API.ClientPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Locations {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("postCode")
    @Expose
    private String postCode;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

}
