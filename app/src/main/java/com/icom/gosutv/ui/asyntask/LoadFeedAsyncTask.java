package com.icom.gosutv.ui.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.icom.gosutv.sao.RestfulService;
import com.icom.gosutv.sao.dto.FeedDTO;
import com.icom.gosutv.sao.exception.NetWorkException;
import com.icom.gosutv.ui.model.FeedModel;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 9/15/2015.
 */
public class LoadFeedAsyncTask extends AsyncTask<String, List<FeedDTO>, List<FeedDTO>>
{
    private Integer lastItem;
    private Integer totalItem;
    private Integer typeFeed;
    private Boolean isFeatured;
    private String type;
    private Context context;
    private ProgressAsync progressAsync;

    public LoadFeedAsyncTask(Context context)
    {
        this.context = context;
    }

    public void buildAsync(Integer lastItem, Integer totalItem, Integer typeFeed, Boolean isFeatured,String type)
    {
        this.lastItem = lastItem;
        this.totalItem = totalItem;
        this.typeFeed = typeFeed;
        this.isFeatured = isFeatured;
        this.type = type;
    }

    public void buildAsync(Integer lastItem, Integer totalItem, Integer typeFeed, Boolean isFeatured)
    {
        buildAsync(lastItem,totalItem,typeFeed,isFeatured,null);
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        progressAsync.preAsync();
    }

    @Override
    protected List<FeedDTO> doInBackground(String... strings)
    {
        List<FeedDTO> feedDTOs = new ArrayList<>();
//                RestfulService.getInstance().getListFeedsWithParams(lastItem, totalItem, typeFeed, isFeatured, type).getFeedDTOs();
        return feedDTOs;
    }

    @Override
    protected void onPostExecute(List<FeedDTO> feedDTOs)
    {
        super.onPostExecute(feedDTOs);
        List<FeedModel> feedModels = FeedModel.convertFromFeedDTO(feedDTOs);
        progressAsync.afterAsync(feedModels);
    }

    public Integer getLastItem()
    {
        return lastItem;
    }

    public void setLastItem(Integer lastItem)
    {
        this.lastItem = lastItem;
    }

    public Integer getTotalItem()
    {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem)
    {
        this.totalItem = totalItem;
    }

    public Integer getTypeFeed()
    {
        return typeFeed;
    }

    public void setTypeFeed(Integer typeFeed)
    {
        this.typeFeed = typeFeed;
    }

    public Boolean getIsFeatured()
    {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured)
    {
        this.isFeatured = isFeatured;
    }

    public ProgressAsync getProgressAsync()
    {
        return progressAsync;
    }

    public void setProgressAsync(ProgressAsync progressAsync)
    {
        this.progressAsync = progressAsync;
    }
}
