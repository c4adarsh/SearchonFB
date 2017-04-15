package com.usc.searchonfb.Rest.Model.DetailModel;

/**
 * Created by adarsh on 4/15/2017.
 */

public class DataAlbums {

    String name;

    Photos photos;

    String id;

    public DataAlbums(String name, Photos photos, String id) {
        this.name = name;
        this.photos = photos;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Photos getPhotos() {
        return photos;
    }

    public String getId() {
        return id;
    }
}
