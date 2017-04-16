package com.usc.searchonfb.presenter;

import android.util.Log;

import com.usc.searchonfb.model.PageDataModel;
import com.usc.searchonfb.model.UserDataModel;
import com.usc.searchonfb.presenter.contract.MainPresenterContract;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by adarsh on 4/4/2017.
 */

public class PageFragmentPresenter implements MainPresenterContract.Presenter, MainPresenterContract.ModelCallBack{

    MainPresenterContract.View mMainView = null;

    PageDataModel model;


    @Inject
    public PageFragmentPresenter(PageDataModel pDataModel) {
        model = pDataModel;
        model.onAttach(this);
    }

    @Override
    public void attach(MainPresenterContract.View view) {
        mMainView = view;
    }

    @Override
    public void detach() {
        mMainView = null;
    }


    @Override
    public void load(String searchQuery) {
        if(mMainView!=null){
            model.loadUserDetails(searchQuery);
        }else{
            Log.i("Adarsh","mMainView is null");
        }
    }

    @Override
    public void loadMore() {
        if(mMainView!=null){

        }
    }

    @Override
    public void queryChanged(String query) {
        if(mMainView!=null){

        }
    }

    @Override
    public void listItemClicked(SearchData mData) {
        if(mMainView!=null){

        }
    }

    //These are the main model call backs

    @Override
    public void onResultLoadMore(List<SearchData> mData) {
        if(mMainView!=null){
            mMainView.addResults(mData);
        }
    }

    @Override
    public void onResultLoad(List<SearchData> mSeachList) {
        if(mMainView!=null){
            //Log.i("Adarsh","I am here1 dude");
            if(mSeachList.size()==0){
                mMainView.showEmptyResultsView();
            }else{
                mMainView.clearResults();
                mMainView.addResults(mSeachList);
            }
        }else{
            //Log.i("Adarsh","I am here dude");
        }
    }

    @Override
    public void onErrorLoad(String mErrorMessage) {
        if(mMainView!=null){
            mMainView.showContentError();
        }
    }

    @Override
    public void onErrorLoadMore(String mErrorMessage) {
        if(mMainView!=null){
            mMainView.showContentError();
        }
    }
}
