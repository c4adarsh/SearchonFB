package com.usc.searchonfb.Rest.Model.DetailModel;

import java.util.List;

/**
 * Created by adarsh on 4/15/2017.
 */

public class Albums {

    List<DataAlbums> data;

    public Albums(List<DataAlbums> data) {
        this.data = data;
    }

    public List<DataAlbums> getData() {
        return data;
    }
}
