package com.usc.searchonfb.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usc.searchonfb.databinding.ContentFavoriteBinding;
import com.usc.searchonfb.view.activity.MainActivity;

/**
 * Created by adarsh on 4/14/2017.
 */

public class FavoriteActivityFragment extends Fragment {

    ContentFavoriteBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = ContentFavoriteBinding.inflate(inflater, container, false);
        binding.toolbar.setTitle("Favorite");
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setToolbar(binding.toolbar);
        super.onActivityCreated(savedInstanceState);
    }
}
