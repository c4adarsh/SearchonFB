package com.usc.searchonfb.Rest.Model.DetailModel;

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
