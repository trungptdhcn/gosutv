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
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.*;
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
import com.icom.gosutv.sao.dto.ListFeedDTO;
import com.icom.gosutv.ui.adapter.GoogleCardsTravelAdapter;
import com.icom.gosutv.ui.adapter.HomFeedAdapter;
import com.icom.gosutv.ui.adapter.ViewpagerAdapter;
import com.icom.gosutv.ui.asyntask.LoadFeedAsyncTask;
import com.icom.gosutv.ui.asyntask.ProgressAsync;
import com.icom.gosutv.ui.customview.CustomListView;
import com.icom.gosutv.ui.customview.OnDetectScrollListener;
import com.icom.gosutv.ui.listener.EndlessScrollListener;
import com.icom.gosutv.ui.model.*;
import com.icom.gosutv.utils.Constants;
import com.icom.gosutv.utils.Utils;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.nineoldandroids.view.ViewHelper;
import com.pnikosis.materialishprogress.ProgressWheel;
import me.relex.circleindicator.CircleIndicator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Trung on 8/27/2015.
 */
public class HomeFragment extends BaseFragment implements ProgressAsync
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
    @InjectView(R.id.home_fragment_rlNoConection)
    RelativeLayout rlNoConection;
    @InjectView(R.id.home_fragment_list_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.fab)
    FloatingActionButton floatingActionButton;
    private static final int INITIAL_DELAY_MILLIS = 300;
    private GoogleCardsTravelAdapter mGoogleCardsAdapter;
    private Integer startItem = 0;
    private Integer totalItem = 4;
    private String typeFilter = "";
    private boolean isFilter = false;
    HomFeedAdapter adapter;

    @Override
    public int getLayout()
    {
        return R.layout.home_fragment;
    }

    @Override
    public void setupView()
    {
        progress.setVisibility(View.VISIBLE);
        SharedPreferences sp = getActivity().getSharedPreferences(getActivity().getString(R.string.preferences_key), Context.MODE_PRIVATE);
        boolean isDota = sp.getBoolean("Dota", false);
        boolean isLol = sp.getBoolean("Lol", false);
        boolean isOverWatch = sp.getBoolean("OverWatch", false);
        boolean isSetupFilter = sp.getBoolean("setupFilter", false);

        if (isDota && !isLol && isSetupFilter)
        {
            typeFilter = "4";
        }
        else if (!isDota && isLol && isSetupFilter)
        {
            typeFilter = "3";
        }
        else
        {
            typeFilter = "3,4";
        }
        swipeRefreshLayout.setProgressViewOffset(false, 0, 5);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                RestfulService.getInstance().getListFeedsWithParams(0, 4, typeFilter, null, null, new Callback<ListFeedDTO>()
                {

                    @Override
                    public void success(ListFeedDTO listFeedDTO, Response response)
                    {
                        progress.setVisibility(View.GONE);
                        rlNoConection.setVisibility(View.GONE);
                        if(adapter == null)
                        {
                            afterAsync(FeedModel.convertFromFeedDTO(listFeedDTO.getFeedDTOs()));
                        }
                        else
                        {
//                            adapter.getListItems().clear();
//                            adapter.setListItems(FeedModel.convertFromFeedDTO(listFeedDTO.getFeedDTOs()));
//                            adapter.notifyDataSetChanged();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        progress.setVisibility(View.GONE);
                        rlNoConection.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), "No network connection", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

        });

        RestfulService.getInstance().getListFeedsWithParams(0, 4, typeFilter, null, null, new Callback<ListFeedDTO>()
        {

            @Override
            public void success(ListFeedDTO listFeedDTO, Response response)
            {
                progress.setVisibility(View.GONE);
                rlNoConection.setVisibility(View.GONE);
                afterAsync(FeedModel.convertFromFeedDTO(listFeedDTO.getFeedDTOs()));
            }

            @Override
            public void failure(RetrofitError error)
            {
                progress.setVisibility(View.GONE);
                rlNoConection.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "No network connection", Toast.LENGTH_SHORT).show();
            }
        });
        floatingActionButton.hide();
        floatingActionButton.setColorNormal(getResources().getColor(R.color.main_color_500));
        floatingActionButton.setColorPressed(getResources().getColor(R.color.material_light_yellow_800));
        floatingActionButton.setColorRipple(getResources().getColor(R.color.material_yellow_50));
//        floatingActionButton.attachToListView(listView, new ScrollDirectionListener()
//        {
//            @Override
//            public void onScrollDown()
//            {
//                floatingActionButton.hide();
//            }
//
//            @Override
//            public void onScrollUp()
//            {
//                floatingActionButton.show();
//            }
//        });
        EndlessScrollListener endlessScrollListener = new EndlessScrollListener()
        {

            @Override
            public void onLoadMore(final int page, int totalItemsCount)
            {
                RestfulService.getInstance().getListFeedsWithParams(page, 4, typeFilter, null, null, new Callback<ListFeedDTO>()
                {
                    @Override
                    public void success(ListFeedDTO listFeedDTO, Response response)
                    {
                        List<FeedModel> feedModels = FeedModel.convertFromFeedDTO(listFeedDTO.getFeedDTOs());
                        for (FeedModel feedModel : feedModels)
                        {
                            adapter.getListItems().add(feedModel);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        Toast.makeText(getActivity(), "No network connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        floatingActionButton.attachToListView(listView,null,endlessScrollListener);
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listView.smoothScrollToPosition(0);
            }
        });
    }

    @OnItemClick(R.id.home_fragment_list_view)
    public void clickOnItem(int position)
    {
        Intent intent = new Intent(getActivity(), FeedDetailActivity.class);
        intent.putExtra(Constants.SLUG, (((FeedModel) adapter.getItem(position - 1))).getSlug());
        startActivity(intent);
    }

    @Override
    public void preAsync()
    {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void afterAsync(List<FeedModel> feedModels)
    {
        View viewHeader = getActivity().getLayoutInflater().inflate(R.layout.viewpager_header, listView, false);
        final ViewPager viewPager = (ViewPager) viewHeader.findViewById(R.id.home_fragment_viewpager_default);
        final CircleIndicator circleIndicator = (CircleIndicator) viewHeader.findViewById(R.id.home_fragment_indicator_default);
        List<FeedModel> feedModels1 = new ArrayList<>();
        feedModels1.add(feedModels.get(0));
        feedModels1.add(feedModels.get(1));
        feedModels1.add(feedModels.get(2));
        feedModels1.add(feedModels.get(3));
        final ViewpagerAdapter pagerAdapter = new ViewpagerAdapter(HomeFragment.this.getActivity(), feedModels1);
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
        viewHeader.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700));
        listView.addHeaderView(viewHeader);
        View footerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.progress_loadmore, null, false);
        listView.addFooterView(footerView);
//            mGoogleCardsAdapter = new GoogleCardsTravelAdapter(getActivity(),
//                    feedModels);
        adapter = new HomFeedAdapter(getActivity(), feedModels);
        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(
                adapter);
        swingBottomInAnimationAdapter.setAbsListView(listView);
        assert swingBottomInAnimationAdapter.getViewAnimator() != null;
        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(
                INITIAL_DELAY_MILLIS);
        listView.setAdapter(swingBottomInAnimationAdapter);
        progress.stopSpinning();
        progress.setVisibility(View.GONE);
        startItem = startItem + totalItem + 1;
    }
}
