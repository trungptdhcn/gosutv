package com.icom.gosutv.ui.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.InjectView;
import butterknife.OnItemClick;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.icom.gosutv.R;
import com.icom.gosutv.base.BaseFragment;
import com.icom.gosutv.sao.RestfulService;
import com.icom.gosutv.sao.dto.FeedDTO;
import com.icom.gosutv.ui.adapter.GoogleCardsTravelAdapter;
import com.icom.gosutv.ui.adapter.ViewpagerAdapter;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.nineoldandroids.view.ViewHelper;
import com.pnikosis.materialishprogress.ProgressWheel;
import me.relex.circleindicator.CircleIndicator;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Trung on 8/27/2015.
 */
public class HomeFragment extends BaseFragment
//        OnDismissCallback
{
    @InjectView(R.id.home_fragment_progress_bar)
    ProgressWheel progress;
    @InjectView(R.id.home_fragment_viewpager_default)
    ViewPager viewPager;
    @InjectView(R.id.home_fragment_indicator_default)
    CircleIndicator circleIndicator;
    @InjectView(R.id.home_fragment_list_view)
    ListView listView;
    private static final int INITIAL_DELAY_MILLIS = 300;
    private GoogleCardsTravelAdapter mGoogleCardsAdapter;

    @Override
    public int getLayout()
    {
        return R.layout.home_fragment;
    }

    @Override
    public void setupView()
    {

        new AsyncTask<String, List<FeedDTO>, List<FeedDTO>>()
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            protected List<FeedDTO> doInBackground(String... strings)
            {
                List<FeedDTO> storyDTOs = RestfulService.getInstance().getListFeeds();
                return storyDTOs;
            }

            @Override
            protected void onPostExecute(List<FeedDTO> feedDTOs)
            {
                super.onPostExecute(feedDTOs);
                List<FeedModel> feedModels = FeedModel.convertFromFeedDTO(feedDTOs);
                final ViewpagerAdapter pagerAdapter = new ViewpagerAdapter(HomeFragment.this.getActivity(), feedModels.subList(0,5));
                viewPager.setAdapter(pagerAdapter);
                final Handler handler = new Handler();
                final int[] currentPage = {0};
                final Runnable Update = new Runnable()
                {
                    public void run()
                    {
                        if (currentPage[0] == pagerAdapter.getCount())
                        {
                            currentPage[0] = 0;
                        }
                        viewPager.setCurrentItem(currentPage[0]++, true);
                    }
                };

                Timer swipeTimer = new Timer();
                swipeTimer.schedule(new TimerTask()
                {

                    @Override
                    public void run()
                    {
                        handler.post(Update);
                    }
                }, 500, 3000);
                circleIndicator.setViewPager(viewPager);
                mGoogleCardsAdapter = new GoogleCardsTravelAdapter(getActivity(),
                        feedModels);
                SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(
                       mGoogleCardsAdapter);
                swingBottomInAnimationAdapter.setAbsListView(listView);
                assert swingBottomInAnimationAdapter.getViewAnimator() != null;
                swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(
                        INITIAL_DELAY_MILLIS);
                listView.setClipToPadding(false);
                listView.setDivider(null);
                Resources r = getResources();
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        8, r.getDisplayMetrics());
                listView.setDividerHeight(px);
                listView.setFadingEdgeLength(0);
                listView.setFitsSystemWindows(true);
                px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12,
                        r.getDisplayMetrics());
                listView.setPadding(px, px, px, px);
                listView.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
                listView.setAdapter(swingBottomInAnimationAdapter);
                progress.stopSpinning();
                progress.setVisibility(View.GONE);
            }
        }.execute();
    }
    @OnItemClick(R.id.home_fragment_list_view)
    public void clickOnItem(int position)
    {
        Intent intent = new Intent(getActivity(),FeedDetailActivity.class);
        intent.putExtra(Constants.SLUG, (mGoogleCardsAdapter.getItem(position)).getSlug());
        startActivity(intent);
    }

//    @Override
//    public void onDismiss(@NonNull ViewGroup viewGroup, @NonNull int[] ints)
//    {
//
//    }
}
