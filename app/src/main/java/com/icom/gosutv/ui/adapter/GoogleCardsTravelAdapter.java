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

    public GoogleCardsTravelAdapter(Context context, List<FeedModel> items)
    {
        super(context, 0, items);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        if (convertView == null)
        {
            convertView = mInflater.inflate(
                    R.layout.list_item_google_cards_travel, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView
                    .findViewById(R.id.list_item_google_cards_travel_image);
            holder.ivPlayThumbnail = (ImageView) convertView
                    .findViewById(R.id.list_item_google_cards_travel_ivThumbnail);
            holder.categoryName = (TextView) convertView
                    .findViewById(R.id.list_item_google_cards_travel_category_name);
            holder.tvTitle = (TextView) convertView
                    .findViewById(R.id.list_item_google_cards_travel_tvTitle);
            holder.tvSapo = (TextView) convertView
                    .findViewById(R.id.list_item_google_cards_travel_tvSapo);
//            holder.explore = (TextView) convertView
//                    .findViewById(R.id.list_item_google_cards_travel_explore);
//            holder.share = (TextView) convertView
//                    .findViewById(R.id.list_item_google_cards_travel_share);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        FeedModel item = getItem(position);
        if(item.getDisPlayType().equals(Constants.DISPLAY_TYPE_VIDEO))
        {
            holder.ivPlayThumbnail.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.ivPlayThumbnail.setVisibility(View.GONE);
        }
        ImageUtil.displayImage(holder.image, item.getThumb(), null);
        holder.tvTitle.setText(item.getTitle());
        holder.tvSapo.setText(item.getSapo());
        return convertView;
    }

    private static class ViewHolder
    {
        public ImageView image;
        public ImageView ivPlayThumbnail;
        public TextView categoryName;
        public TextView tvTitle;
        public TextView tvSapo;
        public TextView explore;
        public TextView share;
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
