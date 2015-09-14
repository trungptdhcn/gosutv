package com.icom.gosutv.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.icom.gosutv.R;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
import com.icom.gosutv.utils.ImageUtil;

import java.util.List;

public class GoogleCardsTravelAdapter extends ArrayAdapter<FeedModel>
        implements OnClickListener
{

    private LayoutInflater mInflater;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public GoogleCardsTravelAdapter(Context context, List<FeedModel> items)
    {
        super(context, 0, items);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position)
    {
        if (getItem(position).getStatus().equals("featured"))
        {
            return TYPE_HEADER;
        }
        else
        {
            return TYPE_CELL;
        }
    }

    @Override
    public long getItemId(int position)
    {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;
        FeedModel item;
        switch (getItemViewType(position))
        {
            case TYPE_HEADER:
                if (convertView == null)
                {
                    convertView = mInflater.inflate(
                            R.layout.list_item_google_cards_travel, parent, false);
                    holder = new ViewHolder();
                    holder.image = (ImageView) convertView
                            .findViewById(R.id.list_item_google_cards_travel_image);
                    holder.ivPlayThumbnail = (ImageView) convertView
                            .findViewById(R.id.list_item_google_cards_travel_ivThumbnail);
                    holder.tvTitle = (TextView) convertView
                            .findViewById(R.id.list_item_google_cards_travel_tvTitle);
                    holder.tvSapo = (TextView) convertView
                            .findViewById(R.id.list_item_google_cards_travel_tvSapo);
                    holder.tvAuthor = (TextView) convertView
                            .findViewById(R.id.list_item_google_cards_travel_tvAuthor);
                    holder.tvView = (TextView) convertView
                            .findViewById(R.id.list_item_google_cards_travel_tvView);
                    convertView.setTag(holder);
                }
                else
                {
                    holder = (ViewHolder) convertView.getTag();
                }

                item = getItem(position);
                if (item.getDisPlayType().equals(Constants.DISPLAY_TYPE_VIDEO))
                {
                    holder.ivPlayThumbnail.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.ivPlayThumbnail.setVisibility(View.GONE);
                }
                ImageUtil.displayImage(holder.image, item.getThumb(), null);
                holder.tvTitle.setText(item.getTitle());
//                holder.tvSapo.setText(item.getSapo());
                break;
            case TYPE_CELL:
                if (convertView == null)
                {
                    convertView = mInflater.inflate(
                            R.layout.list_item_card_small_home, parent, false);
                    holder = new ViewHolder();
                    holder.image = (ImageView) convertView
                            .findViewById(R.id.list_item_card_small_ivImage);
                    holder.ivPlayThumbnail = (ImageView) convertView
                            .findViewById(R.id.list_item_card_small_ivPlayThumbnail);
                    holder.tvTitle = (TextView) convertView
                            .findViewById(R.id.list_item_card_small_tvTitle);
                    holder.tvAuthor = (TextView) convertView
                            .findViewById(R.id.list_item_google_cards_travel_tvAuthor);
                    holder.tvView = (TextView) convertView
                            .findViewById(R.id.list_item_google_cards_travel_tvView);
//                    holder.tvSapo = (TextView) convertView
//                            .findViewById(R.id.list_item_google_cards_travel_tvSapo);
                    convertView.setTag(holder);
                }
                else
                {
                    holder = (ViewHolder) convertView.getTag();
                }

                item = getItem(position);
                if (item.getDisPlayType().equals(Constants.DISPLAY_TYPE_VIDEO))
                {
                    holder.ivPlayThumbnail.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.ivPlayThumbnail.setVisibility(View.GONE);
                }
                ImageUtil.displayImage(holder.image, item.getThumb(), null);
                holder.tvTitle.setText(item.getTitle());
                holder.tvAuthor.setText(item.getAuthor());
                holder.tvView.setText(item.getView());
//                holder.tvSapo.setText(item.getSapo());
                break;
        }
        return convertView;

    }

    private static class ViewHolder
    {
        public ImageView image;
        public ImageView ivPlayThumbnail;
        public TextView tvTitle;
        public TextView tvSapo;
        public TextView share;
        public TextView tvAuthor;
        public TextView tvView;
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        int possition = (Integer) v.getTag();
        switch (v.getId())
        {
//            case R.id.list_item_google_cards_travel_explore:
//                // click on explore button
//                break;
//            case R.id.list_item_google_cards_travel_share:
//                // click on share button
//                break;
        }
    }
}
