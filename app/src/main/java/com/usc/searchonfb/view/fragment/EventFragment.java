package com.usc.searchonfb.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.usc.searchonfb.R;
import com.usc.searchonfb.presenter.EventFragmentPresenter;
import com.usc.searchonfb.presenter.UserFragmentPresenter;
import com.usc.searchonfb.presenter.contract.MainPresenterContract;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;
import com.usc.searchonfb.utils.FavoriteSharedPreference;
import com.usc.searchonfb.view.activity.ResultsActivity;
import com.usc.searchonfb.view.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.usc.searchonfb.utils.Constants.CALL_FROM_FAVORITES;
import static com.usc.searchonfb.utils.Constants.CONST_EVENT;
import static com.usc.searchonfb.utils.Constants.CONST_USER;
import static com.usc.searchonfb.utils.Constants.SEARCH_STRING;

/**
 * Created by adarsh on 4/15/2017.
 */
public class EventFragment extends Fragment implements MainPresenterContract.View {

    @Inject
    EventFragmentPresenter mPresenter;

    String mSearchString = null;

    List<SearchData> mSearchData = new ArrayList<>();

    RecyclerView mRecyclerView;

    private RecyclerViewAdapter adapter;

    boolean callFromFavorites = false;

    LinearLayout mButtonLayout;


    public EventFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearchString = getArguments().getString(SEARCH_STRING);
            callFromFavorites = getArguments().getBoolean(CALL_FROM_FAVORITES,callFromFavorites);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null){
            if(callFromFavorites){
                addResults(FavoriteSharedPreference.getFavouriteList(getActivity(),CONST_EVENT));
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.content_result_pager, container, false);
        findIds(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(getActivity(), mSearchData, CONST_EVENT,callFromFavorites);
        mRecyclerView.setAdapter(adapter);
        if(callFromFavorites){
            mButtonLayout.setVisibility(View.GONE);
        }
        return view;
    }

    private void findIds(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mButtonLayout = (LinearLayout) v.findViewById(R.id.btns);
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(mSearchString!=null){
            ((ResultsActivity)getActivity()).getResultFragmentComponent().inject(this);
        }

        if(mSearchString!=null && mPresenter!=null ){
            mPresenter.attach(this);
            mPresenter.load(mSearchString);
        }

        if(callFromFavorites){
            addResults(FavoriteSharedPreference.getFavouriteList(getActivity(),CONST_EVENT));
        }

    }



    @Override
    public void addResults(List<SearchData> searchData) {
        if(searchData!=null){
            //Log.i(UserFragment.class.getSimpleName(),searchData.size() + "");
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