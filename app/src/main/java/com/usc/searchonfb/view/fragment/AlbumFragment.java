package com.usc.searchonfb.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.usc.searchonfb.R;
import com.usc.searchonfb.rest.model.DetailModel.DetailsData;
import com.usc.searchonfb.utils.ExpandableListItem;
import com.usc.searchonfb.utils.GetExpandableList;
import com.usc.searchonfb.view.adapter.ExpandableListAdapter;

/**
 * Created by adarsh on 4/15/2017.
 */
public class AlbumFragment extends Fragment {

    private boolean dataInserted = false;

    private DetailsData mDetailData = null;

    private boolean isActivityCreated = false;

    private TextView errorTv = null;

    static int lastExpandedPosition = -1;

    private ExpandableListView mExpandableListView = null;

    public AlbumFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);
        findIds(view);
        return view;
    }

    private void findIds(View v) {
        errorTv = (TextView)v.findViewById(R.id.error_message);
        mExpandableListView = (ExpandableListView) v.findViewById(R.id.expandable_list);
    }

    public void insertData(DetailsData pDetailData) {
        dataInserted = true;
        mDetailData = pDetailData;
        if (isActivityCreated) {
            updateUI();
        }
    }

    private void updateUI() {
        if (mDetailData != null) {
            if (mDetailData.getAlbums() != null) {
                //CONVERT THE DATA INTO THE REQUIRED FORMAT
                GetExpandableList mExpandableList = GetExpandableList.getExpandableInstance();
                ExpandableListItem mItem = mExpandableList.getExpandableListData(mDetailData);
                if(mItem.getmHeaderList()!=null && mItem.getmHeaderList().size()!=0){
                    //Initialise adapter and set it to list
                    ExpandableListAdapter mExpandableAdapter = new ExpandableListAdapter(getActivity(), mItem.getmHeaderList(), mItem.getmExpandableListData());
                    // setting list adapter
                    mExpandableListView.setAdapter(mExpandableAdapter);
                    initializeGroupListener();
                    mExpandableListView.setVisibility(View.VISIBLE);
                    errorTv.setVisibility(View.GONE);
                }else{
                    mExpandableListView.setVisibility(View.GONE);
                    errorTv.setVisibility(View.VISIBLE);
                }
            }else{
                mExpandableListView.setVisibility(View.GONE);
                errorTv.setVisibility(View.VISIBLE);
            }
        }else{
            mExpandableListView.setVisibility(View.GONE);
            errorTv.setVisibility(View.VISIBLE);
        }

    }


    private void initializeGroupListener() {
        lastExpandedPosition = -1;
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    mExpandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
    }

    private void showDataFoundPage() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isActivityCreated = true;
        if (dataInserted) {
            updateUI();
        }
    }



}