package com.icom.gosutv.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.icom.gosutv.R;
import com.icom.gosutv.ui.model.CategoryModel;
import com.icom.gosutv.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseAdapter
{

    private LayoutInflater mInflater;
    private List<CategoryModel> categoryModels = new ArrayList<>();
//    private final boolean mIsLayoutOnTop;

    public CategoryAdapter(Context context,
                           List<CategoryModel> categoryModels)
    {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.categoryModels = categoryModels;
    }
//
//    @Override
//    public int getItemViewType(int position)
//    {
//        return TYPE_TWO_COLUMNS;
//    }

//    @Override
//    public int getViewTypeCount()
//    {
//        return TYPE_MAX_COUNT;
//    }

    @Override
    public int getCount()
    {
        return (categoryModels.size());
    }

    @Override
    public Object getItem(int position)
    {
        return categoryModels.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder.ColumnsViewHolder columnsViewHolder;
//        int type = getItemViewType(position);
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.category_list_item, parent, false);
            columnsViewHolder = new ViewHolder.ColumnsViewHolder();
            columnsViewHolder.image1 = (ImageView) convertView.findViewById(R.id.list_item_image_1);
            convertView.setTag(columnsViewHolder);
        }
        else
        {
            columnsViewHolder = (ViewHolder.ColumnsViewHolder) convertView.getTag();
        }

        CategoryModel categoryModel = categoryModels.get(position);
//        if (TextUtils.isEmpty(categoryModel.getTitle()))
//        {
//            twoColumnsViewHolder.layoutTopBottom1.setVisibility(View.GONE);
//        }
//        else
//        {
//            twoColumnsViewHolder.title1.setText(model1.getTitle());
//        }
        ImageUtil.displayImage(columnsViewHolder.image1,  "drawable://" + R.drawable.test, null);
        columnsViewHolder.image1.setTag(position);

//        //twoColumnsViewHolder.favoriteImage1.setVisibility(View.GONE);
//        twoColumnsViewHolder.numberOfImages1.setVisibility(View.GONE);
//        //twoColumnsViewHolder.favoriteImage2.setVisibility(View.GONE);
//        twoColumnsViewHolder.numberOfImages2.setVisibility(View.GONE);
//        LayoutParams lp1 = (LayoutParams) twoColumnsViewHolder.layoutTopBottom1.getLayoutParams();
//        LayoutParams lp2 = (LayoutParams) twoColumnsViewHolder.layoutTopBottom2.getLayoutParams();
//        if (!mIsLayoutOnTop)
//        {
//            lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//            lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//        }
//        else
//        {
//            lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
//            lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
//        }
        return convertView;
    }

    /* We are not using favourite image here. If you really want to use it,
     * remove commented lines related to favourite image.
     */
    private static class ViewHolder
    {
        private static class ColumnsViewHolder
        {
            public ImageView image1;
        }
    }
}
