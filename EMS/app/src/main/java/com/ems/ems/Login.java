package com.ems.ems;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aldmar on 27/11/2017.
 */

public class Login {
    @SerializedName("u")
    private String username;

    @SerializedName("p")
    private String password;

    @SerializedName("account")
    private String account;

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


}
