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
    private boolean sameType = false;

    List<FeedModel> feedModels = new ArrayList<>();

    public CommonRecycleViewAdapter(List<FeedModel> feedModels, boolean sameType)
    {
        this.feedModels = feedModels;
        this.sameType = sameType;
    }

    @Override
    public int getItemViewType(int position)
    {
//        if(sameType)
//        {
//           return TYPE_CELL;
//        }
//        else
//        {
            switch (position)
            {
                case 0:
                    return TYPE_HEADER;
                default:
                    return TYPE_CELL;
            }
//        }
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
//                {
//                    @Override
//                    public void onClick(View v)
//                    {
//                        Intent intent = new Intent(parent.getContext(), FeedDetailActivity.class);
//                        String slug = feedModels.get(this.getLayoutPosition() - 1).getSlug();
//                        intent.putExtra(Constants.SLUG, slug);
//                        parent.getContext().startActivity(intent);
//                    }
//                };

                break;
            }
            case TYPE_CELL:
            {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_small, parent, false);
                viewHolder = new FeedViewHolder(v);
//                {
//                    @Override
//                    public void onClick(View v)
//                    {
//                        Intent intent = new Intent(parent.getContext(), FeedDetailActivity.class);
//                        String slug = feedModels.get(this.getLayoutPosition() + 1).getSlug();
//                        intent.putExtra(Constants.SLUG, slug);
//                        parent.getContext().startActivity(intent);
//                    }
//                };
                break;
            }
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
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
                ImageUtil.displayImage(((FeedViewHolder) holder).ivImage, feedModels.get(position).getThumb(), null);
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
        TextView tvTitle;
        TextView tvDes;
        CardView cardView;

        FeedViewHolder(View itemView)
        {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.list_item_card_small_ivImage);
            tvTitle = (TextView) itemView.findViewById(R.id.list_item_card_small_tvTitle);
            tvDes = (TextView) itemView.findViewById(R.id.list_item_card_small_tvDes);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
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
        CardView cardView;

        FeedHotViewHolder(View itemView)
        {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.list_item_card_big_image);
            tvTitle = (TextView) itemView.findViewById(R.id.list_item_card_big_tvTitle);
            tvDes = (TextView) itemView.findViewById(R.id.list_item_card_big_tvSapo);
            cardView = (CardView)itemView.findViewById(R.id.list_item_card_big_card_view);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view)
        {

        }
    }
}
