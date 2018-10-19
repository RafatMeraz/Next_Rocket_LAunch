package com.example.dell.rocketlauncher;

import java.io.Serializable;

public class Launch2 implements Serializable{
    String id, name, net, tbdTime, tbdDate, windowStart, windowEnd, changed, links;

    public Launch2(String id, String name, String net, String tbdTime, String tbdDate, String windowStart, String windowEnd, String changed, String links) {
        this.id = id;
        this.name = name;
        this.net = net;
        this.tbdTime = tbdTime;
        this.tbdDate = tbdDate;
        this.windowStart = windowStart;
        this.windowEnd = windowEnd;
        this.changed = changed;
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getTbdTime() {
        return tbdTime;
    }

    public void setTbdTime(String tbdTime) {
        this.tbdTime = tbdTime;
    }

    public String getTbdDate() {
        return tbdDate;
    }

    public void setTbdDate(String tbdDate) {
        this.tbdDate = tbdDate;
    }

    public String getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(String windowStart) {
        this.windowStart = windowStart;
    }

    public String getWindowEnd() {
        return windowEnd;
    }

    public void setWindowEnd(String windowEnd) {
        this.windowEnd = windowEnd;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
