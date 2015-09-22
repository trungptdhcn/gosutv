package com.icom.gosutv.ui.adapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.icom.gosutv.R;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
import com.icom.gosutv.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 9/15/2015.
 */
public class HomFeedAdapter extends BaseAdapter
{
    Activity activity;
    List<FeedModel> listItems = new ArrayList<>();

    public HomFeedAdapter(Activity activity, List<FeedModel> listItems)
    {
        this.activity = activity;
        this.listItems = listItems;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (listItems.get(position).getStatus().equals("published"))
        {
            return ListItemType.CONTEXT_PLUGIN_VIEW;
        }
        else
        {
            return ListItemType.HEADER_VIEW;
        }
    }

    //Since we have two types of items here, we'll return 2.
    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

//We'll use a switch case on the type and then typecast it to the relevant
// HeaderObject or the ListItemObject.

//We'll also use the ViewHolder pattern so that android can recycle the views
//and we do not inflate it every time getView() is called. We'll need to create two ViewHolder //Objects for both the item types.

    @Override
    public int getCount()
    {
        return listItems.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    //Let's assume the two layouts to inflated are called list_item_layout and header_layout.
    @Override
    public View getView(final int position, View convertView,
                        final ViewGroup arg2)
    {
        LayoutInflater inflater = activity.getLayoutInflater();
        FeedModel feedModel = listItems.get(position);
        switch (getItemViewType(position))
        {
            case ListItemType.HEADER_VIEW:
//                ListItemObject listItemObject = (ListItemObject) listObject;
                ViewHolderListItem holder;
                if (convertView == null)
                {
                    holder = new ViewHolderListItem();
                    convertView = inflater.inflate(
                            R.layout.list_item_google_cards_travel, null);
                    holder.tvTitle = (TextView) convertView.findViewById(R.id.list_item_google_cards_travel_tvTitle);
                    holder.tvAuthor = (TextView) convertView.findViewById(R.id.list_item_google_cards_travel_tvAuthor);
                    holder.tvView = (TextView) convertView.findViewById(R.id.list_item_google_cards_travel_tvView);
                    holder.ivPlayThumbnail = (ImageView) convertView.findViewById(R.id.list_item_google_cards_travel_ivThumbnail);
                    holder.image = (ImageView) convertView.findViewById(R.id.list_item_google_cards_travel_image);
                    convertView.setTag(holder);
                }
                else
                {
                    holder = (ViewHolderListItem) convertView.getTag();
                }
                if (feedModel.getDisPlayType().equals(Constants.DISPLAY_TYPE_VIDEO))
                {
                    holder.ivPlayThumbnail.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.ivPlayThumbnail.setVisibility(View.GONE);
                }
                ImageUtil.displayImage(holder.image, feedModel.getThumb(), null);
                holder.tvTitle.setText(Html.fromHtml(feedModel.getTitle()));
                holder.tvAuthor.setText(feedModel.getAuthor());
                holder.tvView.setText(feedModel.getView());
                return convertView;
            case ListItemType.CONTEXT_PLUGIN_VIEW:
//                HeaderObject headerObject = (HeaderObject) listObject;
                ViewHolderHeader holder2;
                if (convertView == null)
                {
                    holder2 = new ViewHolderHeader();
                    convertView = inflater.inflate(
                            R.layout.list_item_card_small_home, null);
//                    holder2.headerNameView = (TextView) convertView.findViewById(R.id.headerNameViewId);
                    holder2.tvTitle = (TextView) convertView.findViewById(R.id.list_item_card_small_tvTitle);
                    holder2.tvAuthor = (TextView) convertView.findViewById(R.id.list_item_google_cards_travel_tvAuthor);
                    holder2.tvView = (TextView) convertView.findViewById(R.id.list_item_google_cards_travel_tvView);
                    holder2.ivPlayThumbnail = (ImageView) convertView.findViewById(R.id.list_item_card_small_ivPlayThumbnail);
                    holder2.image = (ImageView) convertView.findViewById(R.id.list_item_card_small_ivImage);
                    convertView.setTag(holder2);
                }
                else
                {
                    holder2 = (ViewHolderHeader) convertView.getTag();
                }
                if (feedModel.getDisPlayType().equals(Constants.DISPLAY_TYPE_VIDEO))
                {
                    holder2.ivPlayThumbnail.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder2.ivPlayThumbnail.setVisibility(View.GONE);
                }
                ImageUtil.displayImage(holder2.image, feedModel.getThumb(), null);
                holder2.tvTitle.setText(Html.fromHtml(feedModel.getTitle()));
                holder2.tvAuthor.setText(feedModel.getAuthor());
                holder2.tvView.setText(feedModel.getView());
                return convertView;
        }
        return null;
    }

    private static class ViewHolderListItem
    {
        public ImageView image;
        public ImageView ivPlayThumbnail;
        public TextView tvTitle;
        public TextView tvSapo;
        public TextView share;
        public TextView tvAuthor;
        public TextView tvView;
    }

    private static class ViewHolderHeader
    {
        public ImageView image;
        public ImageView ivPlayThumbnail;
        public TextView tvTitle;
        public TextView tvSapo;
        public TextView share;
        public TextView tvAuthor;
        public TextView tvView;
    }

    public List<FeedModel> getListItems()
    {
        return listItems;
    }

    public void setListItems(List<FeedModel> listItems)
    {
        this.listItems = listItems;
    }
}
