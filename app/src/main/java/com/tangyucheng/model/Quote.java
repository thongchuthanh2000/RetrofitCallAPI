package com.tangyucheng.model;

import com.google.gson.annotations.SerializedName;

public class Quote {
    public float getUSDVND() {
        return USDVND;
    }

    public void setUSDVND(float USDVND) {
        this.USDVND = USDVND;
    }

    @SerializedName("USDVND")
    private  float USDVND;
}
