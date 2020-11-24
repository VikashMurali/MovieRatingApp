package com.example.movieratingapp.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Self {

    @SerializedName("href")
    @Expose
    public String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
