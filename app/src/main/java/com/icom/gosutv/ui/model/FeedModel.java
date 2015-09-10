package com.icom.gosutv.ui.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.icom.gosutv.sao.dto.FeedDTO;
import com.icom.gosutv.sao.dto.PhotoDTO;
import com.icom.gosutv.sao.dto.RelatedDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 8/28/2015.
 */
public class FeedModel implements Parcelable
{
    private long id;
    private String title;
    private String urlImage;
    private String content;
    private String sapo;
    private String thumb;
    private String slug;
    private String disPlayType;
    public FeedModel()
    {

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

    public String getDisPlayType()
    {
        return disPlayType;
    }

    public void setDisPlayType(String disPlayType)
    {
        this.disPlayType = disPlayType;
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

    public static List<FeedModel> convertFromRelatedDTO(List<RelatedDTO> relatedDTOs)
    {
        List<FeedModel> feedModels = new ArrayList<>();
        for (RelatedDTO relatedDTO : relatedDTOs)
        {
            FeedModel feedModel = convertFromRelatedDTO(relatedDTO);
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
        feedModel.setDisPlayType(feedDTO.getDisplayType());
        return feedModel;
    }

    public static FeedModel convertFromDataDTO(PhotoDTO photoDTO)
    {
        FeedModel feedModel = new FeedModel();
        feedModel.setTitle("");
        feedModel.setSapo(photoDTO.getDescription());
        feedModel.setThumb(photoDTO.getUrlImage());
        return feedModel;
    }

    public static List<FeedModel> convertFromDataDTO(List<PhotoDTO> photoDTOs)
    {
        List<FeedModel> feedModels = new ArrayList<>();
        for (PhotoDTO photoDTO : photoDTOs)
        {
            FeedModel feedModel = convertFromDataDTO(photoDTO);
            feedModels.add(feedModel);
        }
        return feedModels;
    }

    public static FeedModel convertFromRelatedDTO(RelatedDTO relatedDTO)
    {
        FeedModel feedModel = new FeedModel();
        feedModel.setTitle(relatedDTO.getTitle());
        feedModel.setSapo(relatedDTO.getSapo());
        feedModel.setThumb(relatedDTO.getThumb());
        feedModel.setSlug(relatedDTO.getSlug());
        return feedModel;
    }

    public FeedModel(Parcel in)
    {
        id = in.readLong();
        title = in.readString();
        urlImage = in.readString();
        content = in.readString();
        sapo = in.readString();
        thumb = in.readString();
        slug = in.readString();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(urlImage);
        parcel.writeString(content);
        parcel.writeString(sapo);
        parcel.writeString(thumb);
        parcel.writeString(slug);
    }

    public static final Parcelable.Creator<FeedModel> CREATOR = new Parcelable.Creator<FeedModel>()
    {
        public FeedModel createFromParcel(Parcel in)
        {
            return new FeedModel(in);
        }

        public FeedModel[] newArray(int size)
        {
            return new FeedModel[size];
        }
    };
}
