package com.icom.gosutv.sao.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Trung on 9/4/2015.
 */
public class VideoDTO
{
    @SerializedName("mp4")
    private String urlMp4;
    @SerializedName("hls")
    private String urlHls;
    @SerializedName("url")
    private String urlYoutube;

    public String getUrlMp4()
    {
        return urlMp4;
    }

    public void setUrlMp4(String urlMp4)
    {
        this.urlMp4 = urlMp4;
    }

    public String getUrlHls()
    {
        return urlHls;
    }

    public void setUrlHls(String urlHls)
    {
        this.urlHls = urlHls;
    }

    public String getUrlYoutube()
    {
        return urlYoutube;
    }

    public void setUrlYoutube(String urlYoutube)
    {
        this.urlYoutube = urlYoutube;
    }
}
