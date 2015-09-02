package com.icom.gosutv.sao.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Trung on 9/1/2015.
 */
public class PhotoDTO
{
    @SerializedName("url")
    private String urlImage;
    @SerializedName("desc")
    private String description;

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
}
