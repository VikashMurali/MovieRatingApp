package com.example.movieratingapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelClass {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("season")
    @Expose
    public Integer season;
    @SerializedName("number")
    @Expose
    public Integer number;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("airdate")
    @Expose
    public String airdate;
    @SerializedName("airtime")
    @Expose
    public String airtime;
    @SerializedName("airstamp")
    @Expose
    public String airstamp;
    @SerializedName("runtime")
    @Expose
    public Integer runtime;
    @SerializedName("image")
    @Expose
    public Image image;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("_links")
    @Expose
    public Links links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirdate() {
        return airdate;
    }

    public void setAirdate(String airdate) {
        this.airdate = airdate;
    }

    public String getAirtime() {
        return airtime;
    }

    public void setAirtime(String airtime) {
        this.airtime = airtime;
    }

    public String getAirstamp() {
        return airstamp;
    }

    public void setAirstamp(String airstamp) {
        this.airstamp = airstamp;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
