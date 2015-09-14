package com.icom.gosutv.ui.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.icom.gosutv.ui.customview.CustomListView;
import com.icom.gosutv.ui.customview.OnDetectScrollListener;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
import com.melnykov.fab.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.nineoldandroids.view.ViewHelper;
import com.pnikosis.materialishprogress.ProgressWheel;
import me.relex.circleindicator.CircleIndicator;

import java.util.ArrayList;
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
    //    @InjectView(R.id.home_fragment_viewpager_default)
//    ViewPager viewPager;
//    @InjectView(R.id.home_fragment_indicator_default)
//    CircleIndicator circleIndicator;
    @InjectView(R.id.home_fragment_list_view)
    ListView listView;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
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
        fab.setColorNormal(getResources().getColor(R.color.main_color_500));
        fab.setColorPressed(getResources().getColor(R.color.material_light_yellow_800));
        fab.setColorRipple(getResources().getColor(R.color.material_yellow_50));
        fab.attachToListView(listView);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listView.smoothScrollToPosition(0);
            }
        });
//        final Animation animationFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.enter_x);
//        final Animation animationFadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.exit_x);
//        listView.setOnDetectScrollListener(new OnDetectScrollListener()
//        {
//            @Override
//            public void onUpScrolling()
//            {
//                fab.setVisibility(View.VISIBLE);
//                fab.startAnimation(animationFadeIn);
//            }
//
//            @Override
//            public void onDownScrolling()
//            {
//                fab.setVisibility(View.GONE);
//                fab.startAnimation(animationFadeOut);
//            }
//        });
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
                List<FeedDTO> storyDTOs = new ArrayList<FeedDTO>();
                SharedPreferences sp = getActivity().getSharedPreferences(getActivity().getString(R.string.preferences_key), Context.MODE_PRIVATE);
                boolean isDota = sp.getBoolean("Dota", false);
                boolean isLol = sp.getBoolean("Lol", false);
                boolean isSetupFilter = sp.getBoolean("setupFilter", false);
                if(isDota && !isLol && isSetupFilter)
                {
                    storyDTOs = RestfulService.getInstance().getListFeedsWithParams(null, 30, 4, null).getFeedDTOs();
                }
                else if(!isDota && isLol && isSetupFilter)
                {
                    storyDTOs = RestfulService.getInstance().getListFeedsWithParams(null, 30, 3, null).getFeedDTOs();
                }
                else
                {
                    storyDTOs = RestfulService.getInstance().getListFeedsWithParams(null, 30, null, null).getFeedDTOs();
                }
                return storyDTOs;
            }

            @Override
            protected void onPostExecute(List<FeedDTO> feedDTOs)
            {
                super.onPostExecute(feedDTOs);
                List<FeedModel> feedModels = FeedModel.convertFromFeedDTO(feedDTOs);
                View view = getActivity().getLayoutInflater().inflate(R.layout.viewpager_header, listView, false);
                final ViewPager viewPager = (ViewPager) view.findViewById(R.id.home_fragment_viewpager_default);
                final CircleIndicator circleIndicator = (CircleIndicator) view.findViewById(R.id.home_fragment_indicator_default);
                final ViewpagerAdapter pagerAdapter = new ViewpagerAdapter(HomeFragment.this.getActivity(), feedModels.subList(0, 5));
                viewPager.setAdapter(pagerAdapter);
                circleIndicator.setViewPager(viewPager);
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
                viewPager.setOnTouchListener(new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch(View v, MotionEvent event)
                    {
                        PointF downP = new PointF();
                        PointF curP = new PointF();
                        int act = event.getAction();
                        if (act == MotionEvent.ACTION_DOWN || act == MotionEvent.ACTION_MOVE || act == MotionEvent.ACTION_UP)
                        {
                            ((ViewGroup) v).requestDisallowInterceptTouchEvent(true);
                            if (downP.x == curP.x && downP.y == curP.y)
                            {
                                return false;
                            }
                        }
                        return false;
                    }
                });
                view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700));

                listView.addHeaderView(view);
//                Display display = getActivity().getWindowManager().getDefaultDisplay();
//                Point size = new Point();
//                display.getSize(size);
//                int height = size.y;
//                AbsListView.LayoutParams headerViewParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height / 3);
//                view.setLayoutParams(headerViewParams);
                mGoogleCardsAdapter = new GoogleCardsTravelAdapter(getActivity(),
                        feedModels);
                SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(
                        mGoogleCardsAdapter);
                swingBottomInAnimationAdapter.setAbsListView(listView);
                assert swingBottomInAnimationAdapter.getViewAnimator() != null;
                swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(
                        INITIAL_DELAY_MILLIS);
//                listView.setClipToPadding(false);
//                listView.setDivider(null);
//                Resources r = getResources();
//                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                        8, r.getDisplayMetrics());
//                listView.setDividerHeight(px);
//                listView.setFadingEdgeLength(0);
//                listView.setFitsSystemWindows(true);
//                px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12,
//                        r.getDisplayMetrics());
//                listView.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
                listView.setAdapter(swingBottomInAnimationAdapter);
                progress.stopSpinning();
                progress.setVisibility(View.GONE);
            }
        }.execute();
    }

    @OnItemClick(R.id.home_fragment_list_view)
    public void clickOnItem(int position)
    {
        Intent intent = new Intent(getActivity(), FeedDetailActivity.class);
        intent.putExtra(Constants.SLUG, (mGoogleCardsAdapter.getItem(position -1)).getSlug());
        startActivity(intent);
    }

//    @Override
//    public void onDismiss(@NonNull ViewGroup viewGroup, @NonNull int[] ints)
//    {
//
//    }
}
