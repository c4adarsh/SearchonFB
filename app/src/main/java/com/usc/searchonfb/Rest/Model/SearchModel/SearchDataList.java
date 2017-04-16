package com.usc.searchonfb.rest.model.SearchModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by adarsh on 4/15/2017.
 */

public class SearchDataList {

    @SerializedName("data")
    private List<SearchData> searchDataList;

    public SearchDataList(List<SearchData> searchDataList) {
        this.searchDataList = searchDataList;
    }

    public List<SearchData> getSearchDataList() {
        return searchDataList;
    }
}
