package com.icom.gosutv.sao;

import com.icom.gosutv.sao.dto.FeedDTO;
import com.icom.gosutv.sao.dto.FeedDetailDTO;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.List;

/**
 * Created by Trung on 5/30/2015.
 */
public interface RestfulServiceIn
{
    @GET("/latest")
    public List<FeedDTO> getListFeedsWithParams(@Query("page") Integer page, @Query("limit") Integer limit,
                                      @Query("gid") Integer gid, @Query("f") Boolean f);
    @GET("/latest")
    public  List<FeedDTO> getListFeeds();

    @GET("/post")
    public FeedDetailDTO getFeedDetail(@Query("slug") String slug);

//    @GET("/api/v1/story/{chapter}")
//    public StoryDTO getChapter(@Path("chapter") int chapter);
//
//
//    @GET("/api/v1/chapter/{chapter}")
//    public ChapterDTO getReadStory(@Path("chapter") int chapter);
}
