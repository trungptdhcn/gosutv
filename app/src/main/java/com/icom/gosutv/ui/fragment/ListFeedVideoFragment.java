package com.icom.gosutv.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import butterknife.InjectView;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.icom.gosutv.R;
import com.icom.gosutv.base.BaseFragment;
import com.icom.gosutv.sao.RestfulService;
import com.icom.gosutv.sao.dto.FeedDTO;
import com.icom.gosutv.sao.dto.ListFeedDTO;
import com.icom.gosutv.ui.adapter.CommonRecycleViewAdapter;
import com.icom.gosutv.ui.listener.EndlessRecyclerOnScrollListener;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
import com.icom.gosutv.utils.Utils;
import com.melnykov.fab.FloatingActionButton;
import com.pnikosis.materialishprogress.ProgressWheel;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 8/31/2015.
 */
@SuppressLint("ValidFragment")
public class ListFeedVideoFragment extends BaseFragment
{
    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.fragment_recyclerview_bar)
    ProgressWheel progressWheel;
    private RecyclerView.Adapter mAdapter;
    private int gid;
    @InjectView(R.id.fragment_recyclerview_fab)
    FloatingActionButton floatingActionButton;
    CommonRecycleViewAdapter adapter;

    @Override
    public int getLayout()
    {
        return R.layout.fragment_recyclerview;
    }

    @Override
    public void setupView()
    {
        floatingActionButton.attachToRecyclerView(mRecyclerView);
        floatingActionButton.setColorNormal(getResources().getColor(R.color.main_color_500));
        floatingActionButton.setColorPressed(getResources().getColor(R.color.material_light_yellow_800));
        floatingActionButton.setColorRipple(getResources().getColor(R.color.material_yellow_50));
        gid = getArguments().getInt(Constants.GID,-1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        RestfulService.getInstance().getListFeedsWithParams(0, 4, gid+"", null, "video", new Callback<ListFeedDTO>()
        {
            @Override
            public void success(ListFeedDTO listFeedDTO, Response response)
            {
                progressWheel.setVisibility(View.GONE);
                List<FeedModel> feedModels = FeedModel.convertFromFeedDTO(listFeedDTO.getFeedDTOs());
                afterAsync(feedModels);
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
                RestfulService.getInstance().getListFeedsWithParams(current_page, 4, gid+"", null, "video", new Callback<ListFeedDTO>()
                {
                    @Override
                    public void success(ListFeedDTO listFeedDTO, Response response)
                    {
                        progressWheel.setVisibility(View.GONE);
                        adapter.getFeedModels().addAll(FeedModel.convertFromFeedDTO(listFeedDTO.getFeedDTOs()));
                        adapter.notifyDataSetChanged();
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
    public void onDestroy()
    {
        getActivity().getSupportLoaderManager().destroyLoader(1);
        super.onDestroy();
    }

    public void afterAsync(List<FeedModel> feedModels)
    {
        progressWheel.setVisibility(View.GONE);
        adapter = new CommonRecycleViewAdapter(getActivity(), feedModels, false);
        mAdapter = new RecyclerViewMaterialAdapter(adapter);
        mRecyclerView.setAdapter(mAdapter);
    }
}
