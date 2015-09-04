package com.icom.gosutv.sao.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 9/4/2015.
 */
public class DataSetupDTO
{
    private Boolean autoplay;
    private String preload;
    private List<String> techOrder = new ArrayList<>();
    private String src;

    public Boolean getAutoplay()
    {
        return autoplay;
    }

    public void setAutoplay(Boolean autoplay)
    {
        this.autoplay = autoplay;
    }

    public String getPreload()
    {
        return preload;
    }

    public void setPreload(String preload)
    {
        this.preload = preload;
    }

    public List<String> getTechOrder()
    {
        return techOrder;
    }

    public void setTechOrder(List<String> techOrder)
    {
        this.techOrder = techOrder;
    }

    public String getSrc()
    {
        return src;
    }

    public void setSrc(String src)
    {
        this.src = src;
    }
}
