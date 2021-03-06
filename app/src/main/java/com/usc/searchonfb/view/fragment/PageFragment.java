package com.usc.searchonfb.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.usc.searchonfb.R;
import com.usc.searchonfb.presenter.PageFragmentPresenter;
import com.usc.searchonfb.presenter.contract.MainPresenterContract;
import com.usc.searchonfb.rest.model.SearchModel.Paging;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;
import com.usc.searchonfb.utils.FavoriteSharedPreference;
import com.usc.searchonfb.utils.NextPrevUtil;
import com.usc.searchonfb.view.activity.ResultsActivity;
import com.usc.searchonfb.view.adapter.OnItemClickListener;
import com.usc.searchonfb.view.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.usc.searchonfb.utils.Constants.CALL_FROM_FAVORITES;
import static com.usc.searchonfb.utils.Constants.CONST_PAGE;
import static com.usc.searchonfb.utils.Constants.CURRENT_URL;
import static com.usc.searchonfb.utils.Constants.NEXT_URL;
import static com.usc.searchonfb.utils.Constants.PREV_URL;
import static com.usc.searchonfb.utils.Constants.SEARCH_STRING;

/**
 * Created by adarsh on 4/15/2017.
 */
public class PageFragment extends Fragment implements MainPresenterContract.View {

    @Inject
    PageFragmentPresenter mPresenter;

    String mSearchString = null;

    List<SearchData> mSearchData = new ArrayList<>();

    RecyclerView mRecyclerView;

    private RecyclerViewAdapter adapter;

    boolean callFromFavorites = false;

    LinearLayout mButtonLayout;

    Paging mPaging;

    //newly added
    TextView mNoDataView;

    Button mNextButton;

    Button mPreviousButton;

    String nextUrl = null;

    String previousUrl = null;

    String currentUrl = null;

    public PageFragment() {
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
                addResults(FavoriteSharedPreference.getFavouriteList(getActivity(),CONST_PAGE),null);
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
        adapter = new RecyclerViewAdapter(getActivity(), mSearchData, CONST_PAGE,callFromFavorites);
        mRecyclerView.setAdapter(adapter);
        if(callFromFavorites){
            mButtonLayout.setVisibility(View.GONE);
        }
        //newly added code
        setOnclickListenersNextprev();
        return view;
    }

    private void findIds(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mButtonLayout = (LinearLayout) v.findViewById(R.id.btns);
        //newly added
        mNextButton = (Button) v.findViewById(R.id.next);
        mPreviousButton = (Button) v.findViewById(R.id.previous);

        //no data view
        mNoDataView = (TextView)v.findViewById(R.id.no_data_view);
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(mSearchString!=null){
            ((ResultsActivity)getActivity()).getResultFragmentComponent().inject(this);
        }

        if (mSearchString != null && mPresenter != null) {
            mPresenter.attach(this);

            if (savedInstanceState != null && savedInstanceState.getString(CURRENT_URL) != null) {
                currentUrl = savedInstanceState.getString(CURRENT_URL);
                mPresenter.load(mSearchString, 0, currentUrl);
            } else {
                if(currentUrl!=null){
                    mPresenter.load(mSearchString, 0, currentUrl);
                }else{
                    mPresenter.load(mSearchString, 0, null);
                }
            }
        }

        if(callFromFavorites){
            onClickListener();
            addResults(FavoriteSharedPreference.getFavouriteList(getActivity(),CONST_PAGE),null);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mSearchString != null) {
            outState.putString(SEARCH_STRING, mSearchString);
        }

        if (nextUrl != null) {
            outState.putString(NEXT_URL, nextUrl);
        }

        if (previousUrl != null) {
            outState.putString(PREV_URL, previousUrl);
        }

        if (currentUrl != null) {
            outState.putString(CURRENT_URL, currentUrl);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public void addResults(List<SearchData> searchData,Paging mPaging) {
        mNoDataView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        if(searchData!=null){
            //Log.i(UserFragment.class.getSimpleName(),searchData.size() + "");
            adapter.setData(searchData);
            adapter.notifyDataSetChanged();
        }

        if(mPaging!=null){
            this.mPaging = mPaging;
        }

        //TEMP FIX
        if(callFromFavorites){
            if(searchData!=null && searchData.size()==0){
                showEmptyResultsView(mPaging);
            }
        }else{
            //newly added code
            handleNextPrevVisibility(mPaging);
        }
    }

    //newly added code
    private void handleNextPrevVisibility(Paging mPaging) {
        nextUrl = NextPrevUtil.getNextOffset(mPaging);
        String tempPreviousUrl = NextPrevUtil.getPreviosOffset(mPaging);

        if (nextUrl != null) {
            mNextButton.setEnabled(true);
        }

        if(tempPreviousUrl==null && nextUrl==null){
            if(previousUrl!=null){
                mPreviousButton.setEnabled(true);
            }
        }else{
            previousUrl = tempPreviousUrl;
            if(previousUrl==null){
                mPreviousButton.setEnabled(false);
            }else{
                mPreviousButton.setEnabled(true);
            }
        }
    }

    //newly added code
    private void setOnclickListenersNextprev() {
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNextButton.setEnabled(false);
                mPreviousButton.setEnabled(false);
                if (mPresenter != null && nextUrl != null && mSearchString != null) {
                    currentUrl = nextUrl;
                    mPresenter.load(mSearchString, 0, nextUrl);
                }

            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNextButton.setEnabled(false);
                mPreviousButton.setEnabled(false);
                if (mPresenter != null && previousUrl != null && mSearchString != null) {
                    currentUrl = previousUrl;
                    mPresenter.load(mSearchString, 0, previousUrl);
                }
            }
        });
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
        Toast.makeText(getActivity(),"Error in getting data",Toast.LENGTH_SHORT).show();
        if(previousUrl!=null){
            mPreviousButton.setEnabled(true);
        }
    }

    @Override
    public void hideContentError() {

    }

    @Override
    public void showListError() {

    }

    @Override
    public void showEmptyResultsView(Paging mPaging) {
        mNoDataView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        handleNextPrevVisibility(mPaging);
    }

    @Override
    public void hideEmptyResultsView() {

    }

    private void onClickListener() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int noItems) {
                if(noItems<=0){
                    mNoDataView.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }
            }
        });
    }
}