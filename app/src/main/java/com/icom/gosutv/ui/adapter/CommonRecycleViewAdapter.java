package com.icom.gosutv.ui.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.icom.gosutv.R;
import com.icom.gosutv.ui.customview.RobotoTextView;
import com.icom.gosutv.ui.fragment.FeedDetailActivity;
import com.icom.gosutv.ui.listener.OnItemClickRecycleView;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
import com.icom.gosutv.utils.ImageUtil;
import de.greenrobot.event.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 8/31/2015.
 */
public class CommonRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    List<FeedModel> feedModels = new ArrayList<>();

    public CommonRecycleViewAdapter(List<FeedModel> feedModels)
    {
        this.feedModels = feedModels;
    }

    @Override
    public int getItemViewType(int position)
    {
        switch (position)
        {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType)
        {
            case TYPE_HEADER:
            {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_big, parent, false);
                viewHolder = new FeedHotViewHolder(v)
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(parent.getContext(), FeedDetailActivity.class);
                        String slug = feedModels.get(this.getAdapterPosition()).getSlug();
                        intent.putExtra(Constants.SLUG, slug);
                        parent.getContext().startActivity(intent);
                    }
                };

                break;
            }
            case TYPE_CELL:
            {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_small, parent, false);
                viewHolder = new FeedViewHolder(v)
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(parent.getContext(), FeedDetailActivity.class);
                        String slug = feedModels.get(this.getAdapterPosition()).getSlug();
                        intent.putExtra(Constants.SLUG, slug);
                        parent.getContext().startActivity(intent);
                    }
                };
                break;
            }
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        switch (getItemViewType(position))
        {
            case TYPE_HEADER:
                ((FeedHotViewHolder) holder).tvTitle.setText(feedModels.get(position).getTitle());
                ((FeedHotViewHolder) holder).tvDes.setText(feedModels.get(position).getSapo());
                ImageUtil.displayImage(((FeedHotViewHolder) holder).ivImage, feedModels.get(position).getThumb(), null);
                break;
            case TYPE_CELL:
                ((FeedViewHolder) holder).tvTitle.setText(feedModels.get(position).getTitle());
                ((FeedViewHolder) holder).tvDes.setText(feedModels.get(position).getSapo());
                ImageUtil.displayImage(((FeedViewHolder) holder).ivImage, feedModels.get(position).getThumb(), null);
                break;
        }

    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemCount()
    {
        return feedModels.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView ivImage;
        RobotoTextView tvTitle;
        RobotoTextView tvDes;

        FeedViewHolder(View itemView)
        {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.list_item_card_small_ivImage);
            tvTitle = (RobotoTextView) itemView.findViewById(R.id.list_item_card_small_tvTitle);
            tvDes = (RobotoTextView) itemView.findViewById(R.id.list_item_card_small_tvDes);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {

        }
    }

    public static class FeedHotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView ivImage;
        TextView tvTitle;
        TextView tvDes;

        FeedHotViewHolder(View itemView)
        {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.list_item_card_big_image);
            tvTitle = (TextView) itemView.findViewById(R.id.list_item_card_big_tvTitle);
            tvDes = (TextView) itemView.findViewById(R.id.list_item_card_big_tvSapo);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view)
        {
        }
    }
}
