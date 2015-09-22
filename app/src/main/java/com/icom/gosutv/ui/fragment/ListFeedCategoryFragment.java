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
import android.widget.Toast;
import butterknife.InjectView;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.icom.gosutv.R;
import com.icom.gosutv.base.BaseFragment;
import com.icom.gosutv.sao.RestfulService;
import com.icom.gosutv.sao.dto.FeedDTO;
import com.icom.gosutv.sao.dto.ListFeedDTO;
import com.icom.gosutv.ui.adapter.*;
import com.icom.gosutv.ui.asyntask.LoadFeedAsyncTask;
import com.icom.gosutv.ui.asyntask.ProgressAsync;
import com.icom.gosutv.ui.event.AddCoverActivityEvent;
import com.icom.gosutv.ui.listener.EndlessRecyclerOnScrollListener;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
import com.icom.gosutv.utils.Utils;
import com.melnykov.fab.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.pnikosis.materialishprogress.ProgressWheel;
import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Trung on 8/31/2015.
 */
@SuppressLint("ValidFragment")
public class ListFeedCategoryFragment extends BaseFragment implements ProgressAsync
{
    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.fragment_recyclerview_bar)
    ProgressWheel progressWheel;

    @InjectView(R.id.fragment_recyclerview_fab)
    FloatingActionButton floatingActionButton;

    private RecyclerView.Adapter mAdapter;
    CommonRecycleViewAdapter adapter;
//    CommonRecycleViewAdapter2 adapter;
    LoadFeedAsyncTask loadFeedAsyncTask;
    private int gid;

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
        gid = getArguments().getInt(Constants.GID,-1);
        floatingActionButton.attachToRecyclerView(mRecyclerView);
        floatingActionButton.setColorNormal(getResources().getColor(R.color.main_color_500));
        floatingActionButton.setColorPressed(getResources().getColor(R.color.material_light_yellow_800));
        floatingActionButton.setColorRipple(getResources().getColor(R.color.material_yellow_50));
        progressWheel.setVisibility(View.VISIBLE);
        RestfulService.getInstance().getListFeedsWithParams(0, 4, gid+"", null, "news", new Callback<ListFeedDTO>()
        {
            @Override
            public void success(ListFeedDTO listFeedDTO, Response response)
            {
                progressWheel.setVisibility(View.GONE);
                afterAsync(FeedModel.convertFromFeedDTO(listFeedDTO.getFeedDTOs()));
            }

            @Override
            public void failure(RetrofitError error)
            {
                progressWheel.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "No network connection", Toast.LENGTH_SHORT).show();
            }
        });
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager)
        {
            @Override
            public void onLoadMore(final int current_page)
            {
                RestfulService.getInstance().getListFeedsWithParams(current_page, 4, gid+"", null, "news", new Callback<ListFeedDTO>()
                {
                    @Override
                    public void success(ListFeedDTO listFeedDTO, Response response)
                    {
                        progressWheel.setVisibility(View.GONE);
                        List<FeedModel> feedModels = FeedModel.convertFromFeedDTO(listFeedDTO.getFeedDTOs());
                        for (FeedModel feedModel : feedModels)
                        {
                            adapter.getFeedModels().add(feedModel);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        Toast.makeText(getActivity(), "No network connection", Toast.LENGTH_SHORT);
                    }
                });
            }
        });
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }

    @Override
    public void preAsync()
    {
        progressWheel.setVisibility(View.VISIBLE);
    }

    @Override
    public void afterAsync(List<FeedModel> feedModels)
    {
        progressWheel.setVisibility(View.GONE);
        adapter = new CommonRecycleViewAdapter(getActivity(), feedModels, false);
        mAdapter = new RecyclerViewMaterialAdapter(adapter);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver()
        {
            @Override
            public void onChanged()
            {
                super.onChanged();
                adapter.notifyDataSetChanged();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
