package com.icom.gosutv.sao.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Trung on 9/1/2015.
 */
public class ItemDTO
{
    private String id;
    private String title;
    private String thumb;
    private String slug;
    private String sapo;
    private String content;
    private String embed;
    private String displayType;
    @SerializedName("data")
    private List<PhotoDTO> photoDTOs;
    private String author;
    private Integer like;
    private Long view;
    private Long comment;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
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

    public String getSapo()
    {
        return sapo;
    }

    public void setSapo(String sapo)
    {
        this.sapo = sapo;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getEmbed()
    {
        return embed;
    }

    public void setEmbed(String embed)
    {
        this.embed = embed;
    }

    public String getDisplayType()
    {
        return displayType;
    }

    public void setDisplayType(String displayType)
    {
        this.displayType = displayType;
    }

    public List<PhotoDTO> getPhotoDTOs()
    {
        return photoDTOs;
    }

    public void setPhotoDTOs(List<PhotoDTO> photoDTOs)
    {
        this.photoDTOs = photoDTOs;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public Integer getLike()
    {
        return like;
    }

    public void setLike(Integer like)
    {
        this.like = like;
    }

    public Long getView()
    {
        return view;
    }

    public void setView(Long view)
    {
        this.view = view;
    }

    public Long getComment()
    {
        return comment;
    }

    public void setComment(Long comment)
    {
        this.comment = comment;
    }
}
