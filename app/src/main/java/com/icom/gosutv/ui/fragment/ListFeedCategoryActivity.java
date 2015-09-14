package com.icom.gosutv.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.icom.gosutv.R;
import com.icom.gosutv.base.BaseFragment;
import com.icom.gosutv.base.event.ChangedFragmentEvent;
import com.icom.gosutv.sao.RestfulService;
import com.icom.gosutv.sao.dto.FeedDTO;
import com.icom.gosutv.ui.adapter.CommonRecycleViewAdapter;
import com.icom.gosutv.ui.event.AddCoverActivityEvent;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
import de.greenrobot.event.EventBus;

import java.util.List;

/**
 * Created by Trung on 8/31/2015.
 */
public class ListFeedCategoryActivity extends AppCompatActivity
{
    private MaterialViewPager mViewPager;
    private int gid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_activity);
        gid = getIntent().getIntExtra(Constants.GID, -1);
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        findViewById(R.id.materialviewpager_imageHeader).setAlpha(1);
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager())
        {

            @Override
            public Fragment getItem(int position)
            {

                switch (position)
                {
                    case 0:
                        ListFeedCategoryFragment feedCategoryFragment = new ListFeedCategoryFragment();
                        Bundle args = new Bundle();
                        args.putInt(Constants.GID, gid);
                        feedCategoryFragment.setArguments(args);
                        return feedCategoryFragment;
                    case 1:
                        ListFeedVideoFragment feedVideoFragment = new ListFeedVideoFragment();
                        Bundle args1 = new Bundle();
                        args1.putInt(Constants.GID, gid);
                        feedVideoFragment.setArguments(args1);
                        return feedVideoFragment;
                    case 2:
                        ListFeedPhotoFragment feedPhotoFragment = new ListFeedPhotoFragment();
                        Bundle args2 = new Bundle();
                        args2.putInt(Constants.GID, gid);
                        feedPhotoFragment.setArguments(args2);
                        return feedPhotoFragment;
                }
                return null;
            }

            @Override
            public int getCount()
            {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position)
            {
                if (gid == 0)
                {
                    switch (position)
                    {
                        case 0:
                            return Constants.TIN;
                        case 1:
                            return Constants.VIDEO;
                        case 2:
                            return Constants.PHOTO;
                    }

                }
                else
                {
                    switch (position)
                    {
                        case 0:
                            return Constants.TIN;
                        case 1:
                            return Constants.VIDEO;
                        case 2:
                            return Constants.PHOTO;
                    }
                }
                return "";
            }
        });
        switch (gid)
        {
            case 0:
                mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener()
                {
                    @Override
                    public HeaderDesign getHeaderDesign(int page)
                    {
                        return HeaderDesign.fromColorResAndUrl(
                                android.R.color.transparent,
                                "http://thumb.connect360.vn/unsafe/0x0/img.gosutv.vn/pictures/2015/07/09/1436410972_4WCVkt9Q.jpg");

                    }
                });
                break;
            case 1:
                mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener()
                {
                    @Override
                    public HeaderDesign getHeaderDesign(int page)
                    {
                        return HeaderDesign.fromColorResAndUrl(
                                android.R.color.transparent,
                                "http://img.gosutv.vn//pictures//2015//09//08//1441700754_WHEOOV3o.jpg");

                    }
                });
        }
//        new AsyncTask<String, List<FeedDTO>, List<FeedDTO>>()
//        {
//            @Override
//            protected void onPreExecute()
//            {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected List<FeedDTO> doInBackground(String... strings)
//            {
//                List<FeedDTO> storyDTOs;
//                if (gid == 0)
//                {
//                    storyDTOs = RestfulService.getInstance().getListFeedsWithParams(null, null, 4, null);
//                }
//                else
//                {
//                    storyDTOs = RestfulService.getInstance().getListFeedsWithParams(null, null, 3, null);
//                }
//                return storyDTOs;
//            }
//
//            @Override
//            protected void onPostExecute(final List<FeedDTO> feedDTOs)
//            {
//                super.onPostExecute(feedDTOs);
//                final List<FeedModel> feedModels = FeedModel.convertFromFeedDTO(feedDTOs);
//                EventBus.getDefault().post(new AddCoverActivityEvent(feedModels));
////                CommonRecycleViewAdapter adapter = new CommonRecycleViewAdapter(feedModels);
////                mAdapter = new RecyclerViewMaterialAdapter(adapter);
////                mRecyclerView.setAdapter(mAdapter);
////                EventBus.getDefault().post(new AddCoverActivityEvent(feedDTOs.get(0).getThumb()));
//            }
//        }.execute();


        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        View logo = findViewById(R.id.logo_white);
        if (logo != null)
        {
            logo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
        Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    public MaterialViewPager getmViewPager()
    {
        return mViewPager;
    }

    public void setmViewPager(MaterialViewPager mViewPager)
    {
        this.mViewPager = mViewPager;
    }
}
