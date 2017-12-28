package com.ems.ems.API.WeatherPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aldma on 26/12/2017.
 */

public class Rain {
    @SerializedName("3h")
    @Expose
    private Integer _3h;

    public Integer get3h() {
        return _3h;
    }

    public void set3h(Integer _3h) {
        this._3h = _3h;
    }
}
