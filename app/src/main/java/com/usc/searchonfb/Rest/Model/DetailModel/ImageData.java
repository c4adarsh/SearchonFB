package com.usc.searchonfb.rest.model.DetailModel;

import java.util.List;

/**
 * Created by adarsh on 4/15/2017.
 */

public class ImageData {

    List<Images> images;

    public ImageData(List<Images> images) {
        this.images = images;
    }

    public List<Images> getImages() {
        return images;
    }
}
