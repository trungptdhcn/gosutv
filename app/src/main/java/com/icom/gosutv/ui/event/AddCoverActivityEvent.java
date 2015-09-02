package com.icom.gosutv.ui.event;

import com.icom.gosutv.ui.model.FeedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 9/1/2015.
 */
public class AddCoverActivityEvent
{
    List<FeedModel> feedModelList = new ArrayList<>();

    public AddCoverActivityEvent(List<FeedModel> feedModelList)
    {
        this.feedModelList = feedModelList;
    }

    public List<FeedModel> getFeedModelList()
    {
        return feedModelList;
    }

    public void setFeedModelList(List<FeedModel> feedModelList)
    {
        this.feedModelList = feedModelList;
    }
}
