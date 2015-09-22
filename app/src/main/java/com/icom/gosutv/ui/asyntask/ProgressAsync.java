package com.icom.gosutv.ui.asyntask;

import com.icom.gosutv.ui.model.FeedModel;

import java.util.List;

/**
 * Created by Trung on 9/15/2015.
 */
public interface ProgressAsync
{
    public void preAsync();
    public void afterAsync(List<FeedModel> feedModels);
}
