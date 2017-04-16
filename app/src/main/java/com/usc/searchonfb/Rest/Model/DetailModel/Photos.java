package com.usc.searchonfb.rest.model.DetailModel;

import java.util.List;

/**
 * Created by adarsh on 4/15/2017.
 */

public class Photos {
    List<ImageData> data;

    public Photos(List<ImageData> data) {
        this.data = data;
    }

    public List<ImageData> getData() {
        return data;
    }
}
