package com.icom.gosutv.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.icom.gosutv.R;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.ImageUtil;
import com.icom.gosutv.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 8/28/2015.
 */
public class ViewpagerAdapter extends PagerAdapter
{
    private List<FeedModel> feedModels = new ArrayList<>();
    private ArrayList<View> views = new ArrayList<View>();
    private Context context;
    View rootView;

    public ViewpagerAdapter(Context context, List<FeedModel> feedModels)
    {
        this.context = context;
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
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewLayout = inflater.inflate(R.layout.pager_hot_feed_item, container, false);
        ImageView imageView = (ImageView) viewLayout.findViewById(R.id.pager_hot_feed_item_ivImage);
        TextView tvTitle = (TextView) viewLayout.findViewById(R.id.pager_hot_feed_item__tvTitle);
        if (StringUtils.isNotEmpty(feedModels.get(position).getTitle()))
        {
            tvTitle.setText(feedModels.get(position).getTitle());
        }
        ImageUtil.displayImage(imageView, feedModels.get(position).getThumb(), null);
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
}