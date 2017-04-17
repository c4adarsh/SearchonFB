package com.usc.searchonfb.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usc.searchonfb.R;
import com.usc.searchonfb.presenter.PlaceFragmentPresenter;
import com.usc.searchonfb.presenter.UserFragmentPresenter;
import com.usc.searchonfb.presenter.contract.MainPresenterContract;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;
import com.usc.searchonfb.view.activity.ResultsActivity;
import com.usc.searchonfb.view.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.usc.searchonfb.utils.Constants.CONST_PLACE;
import static com.usc.searchonfb.utils.Constants.CONST_USER;
import static com.usc.searchonfb.utils.Constants.SEARCH_STRING;

/**
 * Created by adarsh on 4/15/2017.
 */

public class PlaceFragment extends Fragment implements MainPresenterContract.View{

    @Inject
    PlaceFragmentPresenter mPresenter;

    String mSearchString = "";

    List<SearchData> mSearchData = new ArrayList<>();

    RecyclerView mRecyclerView;

    private RecyclerViewAdapter adapter;

    public PlaceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.content_result_pager, container, false);
        findIds(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(getActivity(), mSearchData, CONST_PLACE);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    private void findIds(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
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
            adapter.setData(searchData);
            adapter.notifyDataSetChanged();
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
