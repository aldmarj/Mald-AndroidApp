package com.ems.ems.API;

import com.google.gson.annotations.SerializedName;

public class Locations {
    @SerializedName("description")
    private String description;

    @SerializedName("postCode")
    private String postcode;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}
