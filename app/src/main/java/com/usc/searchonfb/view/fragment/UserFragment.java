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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.usc.searchonfb.R;
import com.usc.searchonfb.presenter.UserFragmentPresenter;
import com.usc.searchonfb.presenter.contract.MainPresenterContract;
import com.usc.searchonfb.rest.model.SearchModel.Paging;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;
import com.usc.searchonfb.utils.FavoriteSharedPreference;
import com.usc.searchonfb.utils.NextPrevUtil;
import com.usc.searchonfb.view.activity.ResultsActivity;
import com.usc.searchonfb.view.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.usc.searchonfb.utils.Constants.CALL_FROM_FAVORITES;
import static com.usc.searchonfb.utils.Constants.CONST_USER;
import static com.usc.searchonfb.utils.Constants.SEARCH_STRING;

/**
 * Created by adarsh on 4/15/2017.
 */
public class UserFragment extends Fragment implements MainPresenterContract.View {

    @Inject
    UserFragmentPresenter mPresenter;

    String mSearchString = null;

    List<SearchData> mSearchData = new ArrayList<>();

    RecyclerView mRecyclerView;

    private RecyclerViewAdapter adapter;

    boolean callFromFavorites = false;

    LinearLayout mButtonLayout;

    Paging mPaging;

    //newly added
    Button mNextButton;

    Button mPreviousButton;

    String nextUrl = null;

    String previousUrl = null;

    public UserFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearchString = getArguments().getString(SEARCH_STRING);
            callFromFavorites = getArguments().getBoolean(CALL_FROM_FAVORITES, callFromFavorites);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            if (callFromFavorites) {
                addResults(FavoriteSharedPreference.getFavouriteList(getActivity(), CONST_USER), null);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_result_pager, container, false);
        findIds(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(getActivity(), mSearchData, CONST_USER, callFromFavorites);
        mRecyclerView.setAdapter(adapter);
        if (callFromFavorites) {
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
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mSearchString != null) {
            ((ResultsActivity) getActivity()).getResultFragmentComponent().inject(this);
        }

        if (mSearchString != null && mPresenter != null) {
            mPresenter.attach(this);
            mPresenter.load(mSearchString, 0, null);
        }

        if (callFromFavorites) {
            addResults(FavoriteSharedPreference.getFavouriteList(getActivity(), CONST_USER), null);
        }

    }

    @Override
    public void addResults(List<SearchData> searchData, Paging mPaging) {
        if (searchData != null) {
            Log.i(UserFragment.class.getSimpleName(), searchData.size() + "");
            adapter.setData(searchData);
            adapter.notifyDataSetChanged();
        }
        if (mPaging != null) {
            this.mPaging = mPaging;
        }

        //newly added code
        handleNextPrevVisibility(mPaging);

    }

    //newly added code
    private void handleNextPrevVisibility(Paging mPaging) {
        nextUrl = NextPrevUtil.getNextOffset(mPaging);
        previousUrl = NextPrevUtil.getPreviosOffset(mPaging);

        if (nextUrl != null) {
            mNextButton.setEnabled(true);
        }

        if (previousUrl != null) {
            mPreviousButton.setEnabled(true);
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

    }

    @Override
    public void hideContentError() {

    }

    @Override
    public void showListError() {

    }

    @Override
    public void showEmptyResultsView(Paging mPaging) {
        //newly added

        //tell there is no data to show here
        //this is facebooks error
        //Page empty should pop up here
        handleNextPrevVisibility(mPaging);
    }

    @Override
    public void hideEmptyResultsView() {

    }
}