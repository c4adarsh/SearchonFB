package com.usc.searchonfb.rest.model.DetailModel;

import com.usc.searchonfb.rest.model.SearchModel.Picture;

/**
 * Created by adarsh on 4/15/2017.
 */

public class DetailsData {

    String id;

    String name;

    Picture picture;

    Albums albums;

    Posts posts;

    public DetailsData(String id, String name, Picture picture, Albums albums, Posts posts) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.albums = albums;
        this.posts = posts;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Picture getPicture() {
        return picture;
    }

    public Albums getAlbums() {
        return albums;
    }

    public Posts getPosts() {
        return posts;
    }
}
