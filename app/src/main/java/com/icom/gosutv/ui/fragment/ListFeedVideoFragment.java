package com.icom.gosutv.ui.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.InjectView;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.icom.gosutv.R;
import com.icom.gosutv.base.BaseFragment;
import com.icom.gosutv.sao.RestfulService;
import com.icom.gosutv.sao.dto.FeedDTO;
import com.icom.gosutv.ui.adapter.CommonRecycleViewAdapter;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
import com.pnikosis.materialishprogress.ProgressWheel;

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


    @Override
    public int getLayout()
    {
        return R.layout.fragment_recyclerview;
    }

    @Override
    public void setupView()
    {
        gid = getArguments().getInt(Constants.GID,-1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        new AsyncTask<String, List<FeedDTO>, List<FeedDTO>>()
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                progressWheel.setVisibility(View.VISIBLE);
            }

            @Override
            protected List<FeedDTO> doInBackground(String... strings)
            {
                List<FeedDTO> storyDTOs;
                if (gid == 0)
                {
                    storyDTOs = RestfulService.getInstance().getListFeedsWithParams(null, 30, 4, null).getFeedDTOs();
                }
                else
                {
                    storyDTOs = RestfulService.getInstance().getListFeedsWithParams(null, 30, 3, null).getFeedDTOs();
                }
                return storyDTOs;
            }

            @Override
            protected void onPostExecute(final List<FeedDTO> feedDTOs)
            {
                super.onPostExecute(feedDTOs);
                progressWheel.setVisibility(View.GONE);
                List<FeedModel> feedModels = FeedModel.convertFromFeedDTO(feedDTOs);
                List<FeedModel> feedModelVideo = new ArrayList<FeedModel>();
                for(FeedModel feedModel : feedModels)
                {
                    if (feedModel.getDisPlayType().equals(Constants.DISPLAY_TYPE_VIDEO))
                    {
                        feedModelVideo.add(feedModel);
                    }
                }
                CommonRecycleViewAdapter adapter = new CommonRecycleViewAdapter(getActivity(), feedModelVideo, false);
                mAdapter = new RecyclerViewMaterialAdapter(adapter);
                mRecyclerView.setAdapter(mAdapter);
            }
        }.execute();
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }
}
