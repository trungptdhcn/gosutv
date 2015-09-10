package com.icom.gosutv.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.PagerAdapter;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.icom.gosutv.R;
import com.icom.gosutv.ui.listener.PagerOnClickItem;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.ImageUtil;
import com.icom.gosutv.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 9/7/2015.
 */
public class SlideAdapter extends PagerAdapter
{
    private List<FeedModel> feedModels = new ArrayList<>();
    private ArrayList<View> views = new ArrayList<View>();
    private Activity activity;
    PagerOnClickItem pagerOnClickItem;
    View rootView;

    public SlideAdapter(Activity activity, List<FeedModel> feedModels)
    {
        this.activity = activity;
        this.feedModels = feedModels;
    }

    @Override
    public int getItemPosition(Object object)
    {
        int index = views.indexOf(object);
        if (index == -1)
        {
            return POSITION_NONE;
        }
        else
        {
            return index;
        }
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position)
    {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewLayout = inflater.inflate(R.layout.silde_pager_item, container, false);
        ImageView imageView = (ImageView) viewLayout.findViewById(R.id.silde_pager_item_ivImage);
        RelativeLayout rlContainer = (RelativeLayout) viewLayout.findViewById(R.id.silde_pager_item_rlContainer);
        ImageUtil.displayImage(imageView, feedModels.get(position).getThumb(), null);
        viewLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pagerOnClickItem.onClickItem(position, view);
            }
        });
        container.addView(viewLayout);
        return viewLayout;
    }

    @Override
    public int getCount()
    {
        return feedModels.size();
    }

    public boolean isViewFromObject(View view, Object o)
    {
        return view == o;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object)
    {
        container.removeView((RelativeLayout) object);
    }

    public View getRootView()
    {
        return rootView;
    }

    public void setRootView(View rootView)
    {
        this.rootView = rootView;
    }

    public List<FeedModel> getFeedModels()
    {
        return feedModels;
    }

    public void setFeedModels(List<FeedModel> feedModels)
    {
        this.feedModels = feedModels;
    }

    public PagerOnClickItem getPagerOnClickItem()
    {
        return pagerOnClickItem;
    }

    public void setPagerOnClickItem(PagerOnClickItem pagerOnClickItem)
    {
        this.pagerOnClickItem = pagerOnClickItem;
    }
}
