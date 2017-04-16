package com.usc.searchonfb.rest.model.DetailModel;

import java.util.List;

/**
 * Created by adarsh on 4/15/2017.
 */

public class Posts {

    List<PostsData> data;

    public Posts(List<PostsData> data) {
        this.data = data;
    }

    public List<PostsData> getData() {
        return data;
    }
}
