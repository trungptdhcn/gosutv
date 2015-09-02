package com.icom.gosutv.base.event;

import com.icom.gosutv.base.BaseFragment;

/**
 * Created by Trung on 5/17/2015.
 */
public class ChangedFragmentEvent
{
    private BaseFragment baseFragment;

    public ChangedFragmentEvent(BaseFragment baseFragment)
    {
        this.baseFragment = baseFragment;
    }

    public BaseFragment getBaseFragment()
    {
        return baseFragment;
    }

    public void setBaseFragment(BaseFragment baseFragment)
    {
        this.baseFragment = baseFragment;
    }
}
