package com.icom.gosutv.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
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
public class ListFeedCategoryActivity extends FragmentActivity
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
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager())
        {

            @Override
            public Fragment getItem(int position)
            {
                return new ListFeedCategoryFragment(gid);
            }

            @Override
            public int getCount()
            {
                return 1;
            }

            @Override
            public CharSequence getPageTitle(int position)
            {
                if (gid == 0)
                {
                    return Constants.DOTA_2;

                }
                else
                {
                    return Constants.LOL;
                }
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
                                R.color.main_color_500,
                                "http:\\/\\/img.gosutv.vn\\/pictures\\/2015\\/09\\/01\\/1441102535_IDaTZ7w4.jpg");

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
                                R.color.main_color_500,
                                "http://img.gosutv.vn/pictures/2015/09/03/1441245106_miggFw3C.jpg");

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
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return
                super.onOptionsItemSelected(item);
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
