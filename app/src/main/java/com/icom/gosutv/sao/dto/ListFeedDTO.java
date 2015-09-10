package com.icom.gosutv.sao.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 9/7/2015.
 */
public class ListFeedDTO
{
    private Integer page;
    private String gid;
    private Integer count;
    @SerializedName("items")
    private List<FeedDTO> feedDTOs = new ArrayList<>();

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public String getGid()
    {
        return gid;
    }

    public void setGid(String gid)
    {
        this.gid = gid;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public List<FeedDTO> getFeedDTOs()
    {
        return feedDTOs;
    }

    public void setFeedDTOs(List<FeedDTO> feedDTOs)
    {
        this.feedDTOs = feedDTOs;
    }
}
