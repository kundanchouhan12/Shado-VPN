package com.vpn.shadovpn.models;

public class ServerModel {

    private String country;
    private int ping;

    public ServerModel(String country, int ping) {
        this.country = country;
        this.ping = ping;
    }

    public String getCountryName() {
        return country;
    }

    public int getPing() {
        return ping;
    }

    public void setCountryName(String country) {
        this.country = country;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }
}
