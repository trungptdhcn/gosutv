package com.icom.gosutv.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.icom.gosutv.R;
import com.icom.gosutv.ui.model.FeedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    List<FeedModel> feedModels = new ArrayList<>();

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public TestRecyclerViewAdapter(List<FeedModel> feedModels)
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
    public int getItemCount()
    {
        return feedModels.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = null;

        switch (viewType)
        {
            case TYPE_HEADER:
            {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new RecyclerView.ViewHolder(view)
                {
                };
            }
            case TYPE_CELL:
            {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                final View finalView = view;
                return new RecyclerView.ViewHolder(finalView)
                {
                    ImageView ivImage = (ImageView) finalView.findViewById(R.id.list_item_card_small_ivImage);
                    TextView tvTitle = (TextView) finalView.findViewById(R.id.list_item_card_small_tvTitle);
                    TextView tvDes = (TextView) finalView.findViewById(R.id.list_item_card_small_tvDes);
                };
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        switch (getItemViewType(position))
        {
            case TYPE_HEADER:

                break;
            case TYPE_CELL:
//                TextView.
                break;
        }
    }
}