package com.usc.searchonfb.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.usc.searchonfb.R;
import com.usc.searchonfb.rest.model.DetailModel.PostsData;
import com.usc.searchonfb.rest.model.SearchModel.Picture;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;
import com.usc.searchonfb.utils.Constants;
import com.usc.searchonfb.utils.FavoriteSharedPreference;
import com.usc.searchonfb.view.activity.DetailsActivity;

import java.util.List;

/**
 * Created by adarsh on 4/4/2017.
 */

public class RecyclerViewPostAdapter extends RecyclerView.Adapter<RecyclerViewPostAdapter.CustomViewHolder> {

    private List<PostsData> postDataList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private String type;
    private String name;
    private Picture picture;


    public RecyclerViewPostAdapter(Context context, List<PostsData> postDataList, String type, String name, Picture picture) {
        this.postDataList = postDataList;
        this.mContext = context;
        this.type = type;
        this.name = name;
        this.picture = picture;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<PostsData> data) {
        this.postDataList = data;
    }

    public void loadMoreData(List<PostsData> data) {
        this.postDataList.addAll(data);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_list_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        final PostsData mPostData = postDataList.get(position);
        if (mPostData.getCreated_time() != null) {
            holder.mDate.setText(mPostData.getCreated_time());
        }

        if (picture != null) {
            if (picture.getData() != null) {
                if (picture.getData().getUrl() != null) {
                    String mUrl = picture.getData().getUrl();
                    Picasso.with(mContext).load(mUrl)
                            .resize(60, 60).into(holder.mProfileImage);
                }
            }
        }

        if(name!=null){
            holder.mName.setText(name);
        }

        if(mPostData.getMessage()!=null){
            holder.mDetailsText.setText(mPostData.getMessage());
        }else{
            if(mPostData.getStory()!=null){
                holder.mDetailsText.setText(mPostData.getMessage());
            }
        }

    }

    @Override
    public int getItemCount() {
        return (null != postDataList ? postDataList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView mProfileImage;

        protected TextView mName;

        protected TextView mDate;

        protected TextView mDetailsText;

        protected View itemView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.mProfileImage = (ImageView) itemView.findViewById(R.id.image_search);
            this.mName = (TextView) itemView.findViewById(R.id.name);
            this.mDate = (TextView) itemView.findViewById(R.id.time);
            this.mDetailsText = (TextView) itemView.findViewById(R.id.postDetails);
            this.itemView = itemView;
        }

    }
}
