package com.usc.searchonfb.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.usc.searchonfb.R;
import com.usc.searchonfb.presenter.UserFragmentPresenter;
import com.usc.searchonfb.presenter.contract.MainPresenterContract;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;
import com.usc.searchonfb.view.activity.ResultsActivity;

import java.util.List;

import javax.inject.Inject;

import static com.usc.searchonfb.utils.Constants.SEARCH_STRING;

/**
 * Created by adarsh on 4/15/2017.
 */
public class UserFragment extends Fragment implements MainPresenterContract.View {

    @Inject
    UserFragmentPresenter mPresenter;

    String mSearchString = "";

    public UserFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_result_pager, container, false);
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ResultsActivity)getActivity()).getResultFragmentComponent().inject(this);

        if (getArguments() != null) {
            mSearchString = getArguments().getString(SEARCH_STRING);
        }

        if(mPresenter!=null){
            mPresenter.attach(this);
            mPresenter.load(mSearchString);
        }

    }

    @Override
    public void addResults(List<SearchData> searchData) {
        if(searchData!=null){
            Log.i(UserFragment.class.getSimpleName(),searchData.size() + "");
        }
    }

    @Override
    public void clearResults() {

    }

    @Override
    public void showContentLoading() {

    }

    @Override
    public void hideContentLoading() {

    }

    @Override
    public void showListLoading() {

    }

    @Override
    public void hideListLoading() {

    }

    @Override
    public void showContentError() {

    }

    @Override
    public void hideContentError() {

    }

    @Override
    public void showListError() {

    }

    @Override
    public void showEmptyResultsView() {

    }

    @Override
    public void hideEmptyResultsView() {

    }
}