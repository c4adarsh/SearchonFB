package com.usc.searchonfb.View.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.usc.searchonfb.R;
import com.usc.searchonfb.databinding.ContentFavoriteBinding;
import com.usc.searchonfb.databinding.ContentMainBinding;

public class MainActivityFragment extends Fragment {

    ContentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentMainBinding.inflate(inflater, container, false);
        initializeOnClickListeners();
        return binding.getRoot();
    }

    private void initializeOnClickListeners() {
        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.autoCompleteTextView.setText("");
            }
        });

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.autoCompleteTextView.getText().toString().length()!=0){
                    //call the API
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Plese enter a keyword!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
