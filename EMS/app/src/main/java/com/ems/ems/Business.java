package com.ems.ems;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aldmar on 22/11/2017.
 */

public class Business {
    @SerializedName("businessName")
    private String name;

    @SerializedName("businessTag")
    private String tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


}
