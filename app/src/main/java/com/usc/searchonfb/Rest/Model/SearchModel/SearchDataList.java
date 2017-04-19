package com.usc.searchonfb.rest.model.SearchModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by adarsh on 4/15/2017.
 */

public class SearchDataList {

    @SerializedName("data")
    List<SearchData> searchDataList;

    Paging paging;

    public void setSearchDataList(List<SearchData> searchDataList) {
        this.searchDataList = searchDataList;
    }

    public SearchDataList(List<SearchData> searchDataList, Paging paging) {
        this.searchDataList = searchDataList;
        this.paging = paging;
    }

    public SearchDataList(List<SearchData> searchDataList) {
        this.searchDataList = searchDataList;
    }

    public List<SearchData> getSearchDataList() {
        return searchDataList;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
