package com.example.dell.rocketlauncher;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("launches")
    private List<Launch> Launch;
    @SerializedName("total")
    private int total;
    @SerializedName("offset")
    private int offset;
    @SerializedName("count")
    private int count;


    public List<Launch> getLaunch() {
        return Launch;
    }

    public int getTotal() {
        return total;
    }

    public int getOffset() {
        return offset;
    }

    public int getCount() {
        return count;
    }


    public void setLaunch(List<Launch> launch)
    {
        Launch = launch;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
