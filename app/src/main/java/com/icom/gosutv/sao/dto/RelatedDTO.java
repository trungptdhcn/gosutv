package com.icom.gosutv.sao.dto;

import java.util.List;

/**
 * Created by Trung on 9/1/2015.
 */
public class RelatedDTO
{
    private String slug;
    private String title;
    private String thumb;
    private String sapo;
    private String author;
    private Integer like;
    private String view;
    private Long comment;

    public String getSlug()
    {
        return slug;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
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

    public String getSapo()
    {
        return sapo;
    }

    public void setSapo(String sapo)
    {
        this.sapo = sapo;
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

    public String getView()
    {
        return view;
    }

    public void setView(String view)
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
