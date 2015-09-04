package com.icom.gosutv.sao.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 9/1/2015.
 */
public class PhotoDTO
{
    @SerializedName("url")
    private String urlImage;
    @SerializedName("desc")
    private String description;
    @SerializedName("srcType")
    private String srcType;
    @SerializedName("videoId")
    private String videoId;
    @SerializedName("videoSrc")
    private VideoDTO videoDTO;
    @SerializedName("dataSetup")
    private DataSetupDTO dataSetupDTO;
    @SerializedName("poster")
    private String poster;

    public String getUrlImage()
    {
        return urlImage;
    }

    public void setUrlImage(String urlImage)
    {
        this.urlImage = urlImage;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getSrcType()
    {
        return srcType;
    }

    public void setSrcType(String srcType)
    {
        this.srcType = srcType;
    }

    public String getVideoId()
    {
        return videoId;
    }

    public void setVideoId(String videoId)
    {
        this.videoId = videoId;
    }

    public VideoDTO getVideoDTO()
    {
        return videoDTO;
    }

    public void setVideoDTO(VideoDTO videoDTO)
    {
        this.videoDTO = videoDTO;
    }

    public String getPoster()
    {
        return poster;
    }

    public void setPoster(String poster)
    {
        this.poster = poster;
    }

    public DataSetupDTO getDataSetupDTO()
    {
        return dataSetupDTO;
    }

    public void setDataSetupDTO(DataSetupDTO dataSetupDTO)
    {
        this.dataSetupDTO = dataSetupDTO;
    }
}
