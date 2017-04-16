package com.usc.searchonfb.presenter.contract;

import com.usc.searchonfb.presenter.basepresenter.BasePresenterInterface;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;

import java.util.List;

/**
 * Created by adarsh on 4/4/2017.
 */

public class MainPresenterContract {

    public interface View {
        void addResults(List<SearchData> searchDataList);
        void clearResults();
        void showContentLoading();
        void hideContentLoading();
        void showListLoading();
        void hideListLoading();
        void showContentError();
        void hideContentError();
        void showListError();
        void showEmptyResultsView();
        void hideEmptyResultsView();
    }
    public interface Presenter extends BasePresenterInterface<View> {
        void load(String query);
        void loadMore();
        void queryChanged(String query);
        void listItemClicked(SearchData searchData);
    }
    public interface Model{
       void onAttach(MainPresenterContract.ModelCallBack mResponseCallBack);
       void onDetach();
    }
    public interface ModelCallBack{
        void onResultLoad(List<SearchData> searchDataList);
        void onResultLoadMore(List<SearchData> searchDataList);
        void onErrorLoad(String mErrorMessage);
        void onErrorLoadMore(String mErrorMessage);
    }

}
