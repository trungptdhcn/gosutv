package com.icom.gosutv.ui.group;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.icom.gosutv.R;
import com.icom.gosutv.sao.dto.RelatedDTO;
import com.icom.gosutv.ui.fragment.FeedDetailActivity;
import com.icom.gosutv.utils.Constants;
import com.icom.gosutv.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 9/4/2015.
 */
public class RelatedGroup extends Group
{
    private String title;
    private String description;
    private String imageUrl;
    private String slug;
    private Context context;

    public RelatedGroup()
    {
    }

    public RelatedGroup(Context context)
    {
        this.context = context;
    }

    @Override
    public int getLayout()
    {
        return R.layout.list_item_card_small;
    }

    @Override
    public void findById(ViewHolder viewHolder, View view)
    {
        ImageView imageView = (ImageView) view.findViewById(R.id.list_item_card_small_ivImage);
        TextView tvTitle = (TextView) view.findViewById(R.id.list_item_card_small_tvTitle);
        TextView tvDes = (TextView) view.findViewById(R.id.list_item_card_small_tvDes);
        CardView cardView = (CardView) view.findViewById(R.id.card_view);
        viewHolder.addView(imageView);
        viewHolder.addView(tvTitle);
        viewHolder.addView(tvDes);
        viewHolder.addView(cardView);
    }

    @Override
    public void setDataToView(Context context, ViewHolder viewHolder, View view, int position)
    {
        ImageView imageView = (ImageView) viewHolder.getView(R.id.list_item_card_small_ivImage);
        CardView cardView = (CardView) viewHolder.getView(R.id.card_view);
        TextView tvTitle = (TextView) viewHolder.getView(R.id.list_item_card_small_tvTitle);
        TextView tvDes = (TextView) viewHolder.getView(R.id.list_item_card_small_tvDes);
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
//        ImageUtil.displayImageWithSize(imageView, imageUrl, null, width / 4, width / 4);
        ImageUtil.displayImage(imageView, imageUrl, null);
        tvTitle.setText(title);
        tvDes.setText(description);
        cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), FeedDetailActivity.class);
//                        String slug = relatedGroups.get(i).getSlug();
                intent.putExtra(Constants.SLUG, slug);
                view.getContext().startActivity(intent);
                ((Activity)view.getContext()).finish();
            }
        });
//        imageView.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(context, ListFeedCategoryActivity.class);
//                intent.putExtra(Constants.GID, position);
//                context.startActivity(intent);
//            }
//        });
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

    @Override
    public Context getContext()
    {
        return context;
    }

    @Override
    public void setContext(Context context)
    {
        this.context = context;
    }

    public String getSlug()
    {
        return slug;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    public static RelatedGroup convertFromRelatedDTO(RelatedDTO relatedDTO)
    {
        RelatedGroup relatedGroup = new RelatedGroup();
        relatedGroup.setImageUrl(relatedDTO.getThumb());
        relatedGroup.setTitle(relatedDTO.getTitle());
        relatedGroup.setDescription(relatedDTO.getSapo());
        relatedGroup.setSlug(relatedDTO.getSlug());
        return relatedGroup;
    }

    public static List<RelatedGroup> convertFromRelatedDTO(List<RelatedDTO> relatedDTOs)
    {
        List<RelatedGroup> relatedGroups = new ArrayList<>();
        for (RelatedDTO relatedDTO : relatedDTOs)
        {
            relatedGroups.add(convertFromRelatedDTO(relatedDTO));
        }
        return relatedGroups;
    }
}
