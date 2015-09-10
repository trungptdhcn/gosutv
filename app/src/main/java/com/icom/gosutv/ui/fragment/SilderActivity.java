package com.icom.gosutv.ui.fragment;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.icom.gosutv.R;
import com.icom.gosutv.ui.adapter.SlideAdapter;
import com.icom.gosutv.ui.listener.PagerOnClickItem;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
import com.icom.gosutv.utils.ImageUtil;

import java.util.List;

public class SilderActivity extends Activity implements PagerOnClickItem
{

    @InjectView(R.id.gallery_activity_viewpager)
    ViewPager viewpager;
    @InjectView(R.id.slide_pager_item_llActionBar)
    RelativeLayout llActionBar;
    SlideAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider_activity);
        ButterKnife.inject(this);
        int position = getIntent().getIntExtra(Constants.POSITION, -1);
        String title = getIntent().getStringExtra(Constants.TITLE);
        Bundle bundle = getIntent().getExtras();
        List<FeedModel> listImage = bundle.getParcelableArrayList(Constants.MY_LIST);
        adapter = new SlideAdapter(this, listImage);
        adapter.setPagerOnClickItem(this);
        viewpager.setAdapter(adapter);
        if (position != -1)
        {
            viewpager.setCurrentItem(position);
        }
    }

    @Override
    public void onClickItem(int position, View view)
    {
        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.enter);
        final Animation animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.exit);
        if (llActionBar.getVisibility() == View.GONE)
        {
            llActionBar.setVisibility(View.VISIBLE);
            llActionBar.startAnimation(animationFadeIn);
        }
        else
        {
            llActionBar.setVisibility(View.GONE);
            llActionBar.startAnimation(animationFadeOut);
        }
    }
}
