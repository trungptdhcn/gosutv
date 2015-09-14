package com.icom.gosutv.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.view.PagerAdapter;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.icom.gosutv.R;
import com.icom.gosutv.ui.fragment.FeedDetailActivity;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
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
    private Activity activity;
    View rootView;

    public ViewpagerAdapter(Activity activity, List<FeedModel> feedModels)
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
//        final View viewLayout = inflater.inflate(R.layout.pager_hot_feed_item, container, false);
        final View viewLayout = inflater.inflate(R.layout.pager_hot_feed_item,null);
        ImageView imageView = (ImageView) viewLayout.findViewById(R.id.pager_hot_feed_item_ivImage);
        RelativeLayout rlContainer = (RelativeLayout) viewLayout.findViewById(R.id.pager_hot_feed_item_rlContainer);
        TextView tvTitle = (TextView) viewLayout.findViewById(R.id.pager_hot_feed_item__tvTitle);
        if (StringUtils.isNotEmpty(feedModels.get(position).getTitle()))
        {
            tvTitle.setText(feedModels.get(position).getTitle());
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
//        ImageUtil.displayImageWithSize(imageView, feedModels.get(position).getThumb()
//                , null, width, height/3);
        ImageUtil.displayImage(imageView, feedModels.get(position).getThumb(),null);
        rlContainer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), FeedDetailActivity.class);
                String slug = feedModels.get(position).getSlug();
                intent.putExtra(Constants.SLUG, slug);
                view.getContext().startActivity(intent);
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
}
