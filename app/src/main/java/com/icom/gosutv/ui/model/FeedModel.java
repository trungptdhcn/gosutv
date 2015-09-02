package com.icom.gosutv.ui.model;

import com.icom.gosutv.sao.dto.FeedDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 8/28/2015.
 */
public class FeedModel
{
    private long id;
    private String title;
    private String urlImage;
    private String content;
    private String sapo;
    private String thumb;
    private String slug;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getUrlImage()
    {
        return urlImage;
    }

    public void setUrlImage(String urlImage)
    {
        this.urlImage = urlImage;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getSapo()
    {
        return sapo;
    }

    public void setSapo(String sapo)
    {
        this.sapo = sapo;
    }

    public String getThumb()
    {
        return thumb;
    }

    public void setThumb(String thumb)
    {
        this.thumb = thumb;
    }

    public String getSlug()
    {
        return slug;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    public static List<FeedModel> convertFromFeedDTO(List<FeedDTO> feedDTOs)
    {
        List<FeedModel> feedModels = new ArrayList<>();
        for (FeedDTO feedDTO : feedDTOs)
        {
            FeedModel feedModel = convertFromFeedDTO(feedDTO);
            feedModels.add(feedModel);
        }
        return feedModels;
    }

    public static FeedModel convertFromFeedDTO(FeedDTO feedDTO)
    {
        FeedModel feedModel = new FeedModel();
        feedModel.setTitle(feedDTO.getTitle());
        feedModel.setUrlImage(feedDTO.getUrlImage());
        feedModel.setContent(feedDTO.getContent());
        feedModel.setSapo(feedDTO.getSapo());
        feedModel.setThumb(feedDTO.getThumb());
        feedModel.setSlug(feedDTO.getSlug());
        return feedModel;
    }
}
