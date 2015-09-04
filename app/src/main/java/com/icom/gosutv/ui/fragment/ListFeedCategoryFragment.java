package com.icom.gosutv.ui.fragment;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.InjectView;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.icom.gosutv.R;
import com.icom.gosutv.base.BaseFragment;
import com.icom.gosutv.sao.RestfulService;
import com.icom.gosutv.sao.dto.FeedDTO;
import com.icom.gosutv.ui.adapter.CommonRecycleViewAdapter;
import com.icom.gosutv.ui.adapter.GoogleCardsTravelAdapter;
import com.icom.gosutv.ui.adapter.TestRecyclerViewAdapter;
import com.icom.gosutv.ui.adapter.ViewpagerAdapter;
import com.icom.gosutv.ui.event.AddCoverActivityEvent;
import com.icom.gosutv.ui.model.FeedModel;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import de.greenrobot.event.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Trung on 8/31/2015.
 */
@SuppressLint("ValidFragment")
public class ListFeedCategoryFragment extends BaseFragment
{
    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private int gid;

    @SuppressLint("ValidFragment")
    public ListFeedCategoryFragment(int gid)
    {
        this.gid = gid;
    }

    @Override
    public int getLayout()
    {
        return R.layout.fragment_recyclerview;
    }

    @Override
    public void setupView()
    {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
//        mAdapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewAdapter(mContentItems));
        new AsyncTask<String, List<FeedDTO>, List<FeedDTO>>()
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
            }

            @Override
            protected List<FeedDTO> doInBackground(String... strings)
            {
                List<FeedDTO> storyDTOs;
                if (gid == 0)
                {
                    storyDTOs = RestfulService.getInstance().getListFeedsWithParams(null, null, 4, null);
                }
                else
                {
                    storyDTOs = RestfulService.getInstance().getListFeedsWithParams(null, null, 3, null);
                }
                return storyDTOs;
            }

            @Override
            protected void onPostExecute(final List<FeedDTO> feedDTOs)
            {
                super.onPostExecute(feedDTOs);
                List<FeedModel> feedModels = FeedModel.convertFromFeedDTO(feedDTOs);
                CommonRecycleViewAdapter adapter = new CommonRecycleViewAdapter(feedModels,false);
                mAdapter = new RecyclerViewMaterialAdapter(adapter);
                mRecyclerView.setAdapter(mAdapter);
//                EventBus.getDefault().post(new AddCoverActivityEvent(feedDTOs.get(0).getThumb()));
            }
        }.execute();
//        mRecyclerView.setAdapter(mAdapter);
//        {
//            for (int i = 0; i < ITEM_COUNT; ++i)
//            {
//                mContentItems.add(new Object());
//            }
//            mAdapter.notifyDataSetChanged();
//        }
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }
}
