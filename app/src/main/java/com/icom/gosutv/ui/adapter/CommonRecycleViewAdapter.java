package com.icom.gosutv.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
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
    private boolean sameType = false;
    private Activity activity;

    List<FeedModel> feedModels = new ArrayList<>();

    public CommonRecycleViewAdapter(Activity activity, List<FeedModel> feedModels, boolean sameType)
    {
        this.feedModels = feedModels;
        this.sameType = sameType;
        this.activity = activity;
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
                viewHolder = new FeedHotViewHolder(v);

                break;
            }
            case TYPE_CELL:
            {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_small, parent, false);
                viewHolder = new FeedViewHolder(v);
                break;
            }
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        switch (getItemViewType(position))
        {
            case TYPE_HEADER:
                ((FeedHotViewHolder) holder).tvTitle.setText(feedModels.get(position).getTitle());
                ((FeedHotViewHolder) holder).tvDes.setText(feedModels.get(position).getSapo());
                ImageUtil.displayImage(((FeedHotViewHolder) holder).ivImage, feedModels.get(position).getThumb(), null);
                ((FeedHotViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener()
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
                break;
            case TYPE_CELL:
                ((FeedViewHolder) holder).tvTitle.setText(feedModels.get(position).getTitle());
                ((FeedViewHolder) holder).tvDes.setText(feedModels.get(position).getSapo());
                ((FeedViewHolder) holder).tvAuthor.setText(feedModels.get(position).getAuthor());
                ((FeedViewHolder) holder).tvView.setText(feedModels.get(position).getView());
//                ImageUtil.displayImageWithSize(((FeedViewHolder) holder).ivImage, feedModels.get(position).getThumb()
//                        , null, width / 3, width/4);
                ImageUtil.displayImage(((FeedViewHolder) holder).ivImage, feedModels.get(position).getThumb()
                        , null);
                if (feedModels.get(position).getDisPlayType().equals(Constants.DISPLAY_TYPE_VIDEO))
                {
                    ((FeedViewHolder) holder).ivThumbnailPlay.setVisibility(View.VISIBLE);
                    ((FeedViewHolder) holder).ivThumbnailPlay.setImageResource(R.drawable.play_thumbnail);
                }
                else if (feedModels.get(position).getDisPlayType().equals(Constants.DISPLAY_TYPE_PHOTO))
                {
                    ((FeedViewHolder) holder).ivThumbnailPlay.setVisibility(View.VISIBLE);
                    ((FeedViewHolder) holder).ivThumbnailPlay.setImageResource(R.drawable.gallery);
                }
                else
                {
                    ((FeedViewHolder) holder).ivThumbnailPlay.setVisibility(View.GONE);
                }
                ((FeedViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener()
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
        ImageView ivThumbnailPlay;
        TextView tvTitle;
        TextView tvDes;
        TextView tvAuthor;
        TextView tvView;
        CardView cardView;

        FeedViewHolder(View itemView)
        {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.list_item_card_small_ivImage);
            ivThumbnailPlay = (ImageView) itemView.findViewById(R.id.list_item_card_small_ivPlayThumbnail);
            tvTitle = (TextView) itemView.findViewById(R.id.list_item_card_small_tvTitle);
            tvDes = (TextView) itemView.findViewById(R.id.list_item_card_small_tvDes);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            tvAuthor = (TextView) itemView.findViewById(R.id.list_item_google_cards_travel_tvAuthor);
            tvView = (TextView) itemView.findViewById(R.id.list_item_google_cards_travel_tvView);
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
        TextView tvAuthor;
        TextView tvView;
        CardView cardView;

        FeedHotViewHolder(View itemView)
        {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.list_item_card_big_image);
            tvTitle = (TextView) itemView.findViewById(R.id.list_item_card_big_tvTitle);
            tvDes = (TextView) itemView.findViewById(R.id.list_item_card_big_tvSapo);
            cardView = (CardView) itemView.findViewById(R.id.list_item_card_big_card_view);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view)
        {

        }
    }
}
