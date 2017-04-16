package com.usc.searchonfb.rest.model.SearchModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by adarsh on 4/15/2017.
 */

public class SearchData {

    String id;

    String link;

    String name;

    @SerializedName("picture")
    Picture picture;

    public SearchData(String id, String link, String name, Picture picture) {
        this.id = id;
        this.link = link;
        this.name = name;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public Picture getPicture() {
        return picture;
    }
}
