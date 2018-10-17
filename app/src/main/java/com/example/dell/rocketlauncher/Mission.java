package com.example.dell.rocketlauncher;

import java.io.Serializable;

public class Mission implements Serializable {
    String id, name, description, type, infoURL, wikiURL;

    public Mission(String id, String name, String description, String type, String infoURL, String wikiURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.infoURL = infoURL;
        this.wikiURL = wikiURL;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfoURL() {
        return infoURL;
    }

    public void setInfoURL(String infoURL) {
        this.infoURL = infoURL;
    }

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }
}
