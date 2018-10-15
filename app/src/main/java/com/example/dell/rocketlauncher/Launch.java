package com.example.dell.rocketlauncher;

import com.google.gson.annotations.SerializedName;

public class Launch {

    @SerializedName("id")
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("name")
    private String name;
    @SerializedName("net")
    private String net;
    @SerializedName("tbdtime")
    private String tbdtime;
    @SerializedName("tbddate")
    private String tbddate;

    public Launch(String id, String name, String net, String tbdtime, String tbddate) {
        this.id = id;
        this.name = name;
        this.net = net;
        this.tbdtime = tbdtime;
        this.tbddate = tbddate;




    }

    public String getId() {
        return id;
    }


    public String getNet() {
        return net;
    }

    public String getTbdtime() {
        return tbdtime;
    }

    public String getTbddate() {
        return tbddate;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setNet(String net) {
        this.net = net;
    }

    public void setTbdtime(String tbdtime) {
        this.tbdtime = tbdtime;
    }

    public void setTbddate(String tbddate) {
        this.tbddate = tbddate;
    }

}
