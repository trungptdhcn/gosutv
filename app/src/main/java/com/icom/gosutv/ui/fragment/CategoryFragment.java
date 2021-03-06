package com.icom.gosutv.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import butterknife.InjectView;
import com.icom.gosutv.R;
import com.icom.gosutv.base.BaseFragment;
import com.icom.gosutv.ui.adapter.CommonAdapter;
import com.icom.gosutv.ui.group.CategoryGroup;
import com.icom.gosutv.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 8/28/2015.
 */
public class CategoryFragment extends BaseFragment
{
    @InjectView(R.id.category_fragment_grCategory)
    GridView grCategory;
    CommonAdapter adapter;

    @Override
    public int getLayout()
    {
        return R.layout.category_fragment;
    }

    @Override
    public void setupView()
    {
        List<CategoryGroup> categoryGroups = new ArrayList<>();
        CategoryGroup categoryDOTA = new CategoryGroup();
        categoryDOTA.setImageUrl(R.drawable.dota_2_icon + "");
        CategoryGroup categoryLOL = new CategoryGroup();
        categoryLOL.setImageUrl(R.drawable.lol_icon + "");
        CategoryGroup overWatch = new CategoryGroup();
        overWatch.setImageUrl(R.drawable.overwatch + "");
        CategoryGroup other = new CategoryGroup();
        other.setImageUrl(R.drawable.other_game + "");
        categoryGroups.add(categoryLOL);
        categoryGroups.add(categoryDOTA);
        categoryGroups.add(overWatch);
        categoryGroups.add(other);
        adapter = new CommonAdapter(getActivity(), categoryGroups);
        grCategory.setAdapter(adapter);
//        grCategory.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
//            {
//                Intent intent = new Intent(getActivity(), ListFeedCategoryActivity.class);
//                if (i == 0)
//                {
//                    intent.putExtra(Constants.GID, 4);
//                }
//                else
//                {
//                    intent.putExtra(Constants.GID, 3);
//                }
//                getActivity().startActivity(intent);
//            }
//        });
    }
}
