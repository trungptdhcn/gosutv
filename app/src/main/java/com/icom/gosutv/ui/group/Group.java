package com.icom.gosutv.ui.group;

import android.content.Context;
import android.view.View;

/**
 * Created by Trung on 5/30/2015.
 */
public abstract class Group
{
    private Context context;

    public abstract int getLayout();

    public abstract void findById(ViewHolder viewHolder, View view);

    public abstract void setDataToView(Context context, ViewHolder viewHolder, View view, int position);

    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }
}
