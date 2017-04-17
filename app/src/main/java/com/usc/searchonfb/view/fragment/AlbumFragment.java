package com.usc.searchonfb.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.usc.searchonfb.R;
import com.usc.searchonfb.rest.model.DetailModel.DetailsData;
import com.usc.searchonfb.rest.model.DetailModel.Posts;

/**
 * Created by adarsh on 4/15/2017.
 */
public class AlbumFragment extends Fragment {


    public boolean dataInserted = false;

    public DetailsData mDetailData = null;

    public AlbumFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_result_pager, container, false);
        findIds(view);
        return view;
    }

    private void findIds(View v) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void insertData(DetailsData pDetailData) {
        dataInserted = true;
        mDetailData = pDetailData;
        updateUI();

    }

    private void updateUI() {
        
    }

    @Override
    public void onDestroy() {
        dataInserted = false;
        mDetailData = null;
        super.onDestroy();
    }
}