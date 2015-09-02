package com.icom.gosutv.ui.group;

import android.view.View;

import java.util.HashMap;

/**
 * Created by trungpt on 4/6/15.
 */
public class ViewHolder
{
    private HashMap<Integer, View> storedViews = new HashMap<Integer, View>();

    public ViewHolder()
    {
    }
    /**
     * @param view The view to add; to reference this view later, simply refer to its id.
     * @return This instance to allow for chaining.
     */
    public ViewHolder addView(View view)
    {
        int id = view.getId();
        storedViews.put(id, view);
        return this;
    }

    public View getView(int id)
    {
        return storedViews.get(id);
    }
}
