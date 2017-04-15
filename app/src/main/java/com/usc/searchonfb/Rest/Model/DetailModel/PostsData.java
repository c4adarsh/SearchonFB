package com.usc.searchonfb.Rest.Model.DetailModel;

/**
 * Created by adarsh on 4/15/2017.
 */

public class PostsData {

    String message;

    String created_time;

    String story;

    public PostsData(String message, String created_time, String story) {
        this.message = message;
        this.created_time = created_time;
        this.story = story;
    }

    public String getMessage() {
        return message;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getStory() {
        return story;
    }
}
