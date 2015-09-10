package com.icom.gosutv.ui.group;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import com.icom.gosutv.R;
import com.icom.gosutv.ui.fragment.ListFeedCategoryActivity;
import com.icom.gosutv.utils.Constants;
import com.icom.gosutv.utils.ImageUtil;

/**
 * Created by Trung on 8/31/2015.
 */
public class CategoryGroup extends Group
{
    private String title;
    private String description;
    private String imageUrl;

    public CategoryGroup()
    {

    }

    @Override
    public int getLayout()
    {
        return R.layout.category_list_item;
    }

    @Override
    public void findById(ViewHolder viewHolder, View view)
    {
        ImageView imageView = (ImageView) view.findViewById(R.id.list_item_image_1);
        viewHolder.addView(imageView);

    }

    @Override
    public void setDataToView(final Context context, ViewHolder viewHolder, View view, final int position)
    {
        ImageView imageView = (ImageView) viewHolder.getView(R.id.list_item_image_1);
        ImageUtil.displayImage(imageView, "drawable://" + imageUrl, null);
        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, ListFeedCategoryActivity.class);
                intent.putExtra(Constants.GID, position);
                context.startActivity(intent);
            }
        });
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }
}
