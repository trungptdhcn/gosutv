package com.icom.gosutv.ui.model;

/**
 * Created by Trung on 8/31/2015.
 */
public class CategoryModel
{
    private String title;
    private String urlImage;
    private String description;

    public CategoryModel(String title)
    {
        this.title = title;
    }

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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
