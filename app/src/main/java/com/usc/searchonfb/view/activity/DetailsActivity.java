package com.usc.searchonfb.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.usc.searchonfb.FacebookApplication;
import com.usc.searchonfb.R;
import com.usc.searchonfb.dagger.component.DaggerDetailsActivityComponent;
import com.usc.searchonfb.dagger.component.DetailsActivityComponent;
import com.usc.searchonfb.dagger.module.DetailsFragmentModule;
import com.usc.searchonfb.databinding.ActivityResultsBinding;
import com.usc.searchonfb.presenter.DetailActivityPresenter;
import com.usc.searchonfb.presenter.contract.DetailsPresenterContract;
import com.usc.searchonfb.rest.model.DetailModel.DetailsData;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;
import com.usc.searchonfb.view.fragment.AlbumFragment;
import com.usc.searchonfb.view.fragment.PostsFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.usc.searchonfb.utils.Constants.CONST_TYPE;
import static com.usc.searchonfb.utils.Constants.SEARCH_STRING;

public class DetailsActivity extends AppCompatActivity implements DetailsPresenterContract.View {

    ActivityResultsBinding binding;

    private ViewPager mViewPager;

    private DetailsActivityComponent mDaggerDetailComponent;

    private SearchData mSearchData = null;

    private String mType = null;

    private AlbumFragment mAlbumFragment = null;

    private  PostsFragment mPostFragment = null;

    @Inject
    DetailActivityPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSearchData = getIntent().getParcelableExtra(SEARCH_STRING);
        mType = getIntent().getStringExtra(CONST_TYPE);

        initializeInjector();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_results);
        setUpToolBar();
        setUpTabsViewPager();
        mainPresenter.attach(this);
        mainPresenter.load(mSearchData.getId());
    }

    private void initializeInjector() {
        mDaggerDetailComponent = DaggerDetailsActivityComponent.builder()
                .detailsFragmentModule(new DetailsFragmentModule())
                .netComponent(((FacebookApplication)getApplicationContext()).getNetComponent())
                .build();
        mDaggerDetailComponent.inject(this);
    }

    private void setUpTabsViewPager() {
        mViewPager = binding.viewPager;
        setupViewPager(mViewPager);
        binding.tabs.setupWithViewPager(binding.viewPager);
        setupTabIcons();
    }

    private void setUpToolBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("  " + getString(R.string.title_details));
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupTabIcons() {
        View tabOne =  LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        ImageView mImageView = (ImageView)tabOne.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.albums);
        TextView mTextView = (TextView)tabOne.findViewById(R.id.text_view_tab);
        mTextView.setText("Albums");
        binding.tabs.getTabAt(0).setCustomView(tabOne);

        View tabTwo =  LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        mImageView = (ImageView)tabTwo.findViewById(R.id.image_view_tab);
        mImageView.setImageResource(R.drawable.posts);
        mTextView = (TextView)tabTwo.findViewById(R.id.text_view_tab);
        mTextView.setText("Posts");
        binding.tabs.getTabAt(1).setCustomView(tabTwo);
    }

    public void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAlbumFragment = new AlbumFragment();
        adapter.addFrag(mAlbumFragment, "Albums");
        mPostFragment = new PostsFragment();
        adapter.addFrag(mPostFragment, "Posts");
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void addResults(DetailsData mDetailsData) {
        //the result comes here, we have to give this data to fragment, broadcast telling that the data is ready or we can also get an instance of the fragment and give it to the fragment.
        Log.i("ResultData",mDetailsData+"");

        if(mAlbumFragment!=null){
            mAlbumFragment.insertData(mDetailsData);
        }

        if(mPostFragment!=null){
            mPostFragment.insertData(mDetailsData);
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

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
