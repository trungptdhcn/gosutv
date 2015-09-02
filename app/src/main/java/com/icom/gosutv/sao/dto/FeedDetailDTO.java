package com.icom.gosutv.sao.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Trung on 9/1/2015.
 */
public class FeedDetailDTO
{
    @SerializedName("item")
    ItemDTO itemDTO;
    @SerializedName("game")
    GameDTO gameDTO;
    @SerializedName("related")
    List<RelatedDTO> relatedDTOs;

    public ItemDTO getItemDTO()
    {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO)
    {
        this.itemDTO = itemDTO;
    }

    public GameDTO getGameDTO()
    {
        return gameDTO;
    }

    public void setGameDTO(GameDTO gameDTO)
    {
        this.gameDTO = gameDTO;
    }

    public List<RelatedDTO> getRelatedDTOs()
    {
        return relatedDTOs;
    }

    public void setRelatedDTOs(List<RelatedDTO> relatedDTOs)
    {
        this.relatedDTOs = relatedDTOs;
    }
}
