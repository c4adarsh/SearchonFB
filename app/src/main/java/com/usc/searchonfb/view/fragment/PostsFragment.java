package com.usc.searchonfb.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.usc.searchonfb.R;
import com.usc.searchonfb.rest.model.DetailModel.DetailsData;

/**
 * Created by adarsh on 4/15/2017.
 */
public class PostsFragment extends Fragment{


    private boolean dataInserted = false;

    private DetailsData mDetailData = null;

    private boolean isActivityCreated = false;



    public PostsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.content_result_pager, container, false);
        return view;
    }

    private void findIds(View v) {

    }

    public void insertData(DetailsData pDetailData) {
        dataInserted = true;
        mDetailData = pDetailData;
        if(isActivityCreated){
            updateUI();
        }
    }

    private void updateUI() {
        Toast.makeText(getActivity(),"In updateUI of post fragment",Toast.LENGTH_LONG).show();
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isActivityCreated = true;
        if(dataInserted){
            updateUI();
        }
    }

}