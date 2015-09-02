package com.icom.gosutv.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.icom.gosutv.ui.group.Group;
import com.icom.gosutv.ui.group.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungpt on 5/27/15.
 */
public class CommonAdapter<T extends Group> extends BaseAdapter
{
    private List<T> groupList = new ArrayList<>();
    private Context context;

    public CommonAdapter(Context context, List<T> groupList)
    {
        this.context = context;
        this.groupList = groupList;
        for (T t : groupList)
        {
            t.setContext(context);
        }
    }

    @Override
    public int getCount()
    {
        return groupList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return groupList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        T commonGroup = groupList.get(position);
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(commonGroup.getLayout(), parent, false);
            holder = new ViewHolder();
            commonGroup.findById(holder, convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        commonGroup.setDataToView(holder, convertView, position);
        return convertView;
    }

    public List<T> getGroupList()
    {
        return groupList;
    }

    public void setGroupList(List<T> groupList)
    {
        this.groupList = groupList;
    }
}
