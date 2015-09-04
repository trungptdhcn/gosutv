package com.icom.gosutv.ui.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.webkit.WebView;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.icom.gosutv.R;
import com.icom.gosutv.sao.RestfulService;
import com.icom.gosutv.sao.dto.FeedDetailDTO;
import com.icom.gosutv.sao.dto.PhotoDTO;
import com.icom.gosutv.ui.adapter.CommonAdapter;
import com.icom.gosutv.ui.customview.VideoControllerView;
import com.icom.gosutv.ui.group.RelatedGroup;
import com.icom.gosutv.utils.Constants;
import com.icom.gosutv.utils.StringUtils;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.io.IOException;
import java.util.List;

/**
 * Created by Trung on 9/1/2015.
 */
public class FeedDetailActivity extends YouTubeBaseActivity implements SurfaceHolder.Callback, android.media.MediaPlayer.OnPreparedListener
        , VideoControllerView.MediaPlayerControl,
        YouTubePlayer.OnInitializedListener
{
    @InjectView(R.id.feed_detail_fragment_tvTitle)
    TextView tvTitle;
    @InjectView(R.id.feed_detail_fragment_tvDes)
    WebView tvDes;
    @InjectView(R.id.feed_detail_fragment_bar)
    ProgressWheel progressWheel;
    @InjectView(R.id.video_container)
    RelativeLayout rlVideoContainer;
    @InjectView(R.id.feed_detail_content_fragment_llRelated)
    LinearLayout llRelated;
    @InjectView(R.id.feed_detail_content_fragment_lvRelated)
    ListView lvRelated;
    @InjectView(R.id.feed_detail_content_fragment_view)
    YouTubePlayerView youTubePlayerView;
    @InjectView(R.id.videoSurfaceContainer)
    FrameLayout videoOriginalView;
    SurfaceView videoSurface;
    MediaPlayer player;
    VideoControllerView controller;
    private RecyclerView.Adapter mAdapter;
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private String slug;
    private static boolean isFullScreen = false;
    public static String youtubeUrl ="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_detail_content_fragment);
        videoSurface = (SurfaceView) findViewById(R.id.videoSurface);
        ButterKnife.inject(this);
        slug = getIntent().getStringExtra(Constants.SLUG);
        tvTitle.setTypeface(Typeface.SERIF);
        tvDes.getSettings().setJavaScriptEnabled(true);
        SurfaceHolder videoHolder = videoSurface.getHolder();
        videoHolder.addCallback(FeedDetailActivity.this);

        player = new MediaPlayer();
        controller = new VideoControllerView(FeedDetailActivity.this);
        new AsyncTask<String, FeedDetailDTO, FeedDetailDTO>()
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                progressWheel.setVisibility(View.VISIBLE);
            }

            @Override
            protected FeedDetailDTO doInBackground(String... strings)
            {
                FeedDetailDTO feedDTO = RestfulService.getInstance().getFeedDetail(slug);
                return feedDTO;
            }

            @Override
            protected void onPostExecute(FeedDetailDTO feedDTO)
            {
                super.onPostExecute(feedDTO);
                progressWheel.setVisibility(View.GONE);
                tvTitle.setText(feedDTO.getItemDTO().getTitle());
                if (feedDTO.getItemDTO().getDisplayType().equals(Constants.DISPLAY_TYPE_NEWS))
                {
                    tvDes.setVisibility(View.VISIBLE);
                    rlVideoContainer.setVisibility(View.GONE);
                    llRelated.setVisibility(View.GONE);
                    tvDes.loadDataWithBaseURL(null
                            , getHtmlData(feedDTO.getItemDTO().getContent()), "text/html", "UTF-8", null);
                }
                else if (feedDTO.getItemDTO().getDisplayType().equals(Constants.DISPLAY_TYPE_VIDEO))
                {
                    tvDes.setVisibility(View.GONE);
                    llRelated.setVisibility(View.VISIBLE);
                    rlVideoContainer.setVisibility(View.VISIBLE);
                    try
                    {
                        List<PhotoDTO> photoDTOs = feedDTO.getItemDTO().getPhotoDTOs();
                        if (photoDTOs != null && photoDTOs.size() > 0)
                        {
                            PhotoDTO photoDTO = photoDTOs.get(0);
                            if (photoDTO.getSrcType().equals(Constants.SCR_TYPE_VIDEO_CONNECT360))
                            {
                                videoOriginalView.setVisibility(View.VISIBLE);
                                youTubePlayerView.setVisibility(View.GONE);
                                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                if (photoDTO.getVideoDTO() != null && StringUtils.isNotEmpty(photoDTO.getVideoDTO().getUrlMp4()))
                                {
                                    player.setDataSource(FeedDetailActivity.this, Uri.parse(photoDTO.getVideoDTO().getUrlMp4()));
                                    player.setOnPreparedListener(FeedDetailActivity.this);
                                    player.prepareAsync();
                                }

                            }
                            else if (photoDTO.getSrcType().equals(Constants.SCR_TYPE_VIDEO_YOUTUBE))
                            {
                                videoOriginalView.setVisibility(View.GONE);
                                youTubePlayerView.setVisibility(View.VISIBLE);
                                youTubePlayerView.initialize(Constants.DEVELOPER_KEY, FeedDetailActivity.this);
                                setYoutubeUrl(photoDTO.getVideoId());
                            }
                        }
                        List<RelatedGroup> relatedGroups = RelatedGroup.convertFromRelatedDTO(feedDTO.getRelatedDTOs());
                        CommonAdapter adapter = new CommonAdapter(FeedDetailActivity.this, relatedGroups);
                        lvRelated.setAdapter(adapter);
                    }
                    catch (IllegalArgumentException e)
                    {
                        e.printStackTrace();
                    }
                    catch (SecurityException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IllegalStateException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {

                }
            }
        }.execute();
    }

    private void setYoutubeUrl(String url)
    {
        youtubeUrl = url;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        controller.show();
        return false;
    }

    // Implement SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        player.setDisplay(holder);
//        player.prepareAsync();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {

    }
    // End SurfaceHolder.Callback

    // Implement MediaPlayer.OnPreparedListener
    @Override
    public void onPrepared(MediaPlayer mp)
    {
        controller.setMediaPlayer(this);
        controller.setAnchorView((FrameLayout) findViewById(R.id.videoSurfaceContainer));
        player.start();
    }
    // End MediaPlayer.OnPreparedListener

    // Implement VideoMediaController.MediaPlayerControl
    @Override
    public boolean canPause()
    {
        return true;
    }

    @Override
    public boolean canSeekBackward()
    {
        return true;
    }

    @Override
    public boolean canSeekForward()
    {
        return true;
    }

    @Override
    public int getBufferPercentage()
    {
        return 0;
    }

    @Override
    public int getCurrentPosition()
    {
        return player.getCurrentPosition();
    }

    @Override
    public int getDuration()
    {
        return player.getDuration();
    }

    @Override
    public boolean isPlaying()
    {
        return player.isPlaying();
    }

    @Override
    public void pause()
    {
        player.pause();
    }

    @Override
    public void seekTo(int i)
    {
        player.seekTo(i);
    }

    @Override
    public void start()
    {
        player.start();
    }

    @Override
    public boolean isFullScreen()
    {
        return isFullScreen;
    }

    @Override
    public void toggleFullScreen()
    {
        if (!isFullScreen())
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            isFullScreen = true;
        }
        else
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            isFullScreen = false;
        }
        controller.updateFullScreen();
    }
    // End VideoMediaController.MediaPlayerControl


    private String getHtmlData(String bodyHTML)
    {
        String head = "<head><style type=\"text/css\">img{max-width: 100%; width:auto; height: auto;}" +
                "iframe{max-width: 100%; width:100%; height: auto;}" +
                "@font-face {\n" +
                "    font-family: MyFont;\n" +
                "    src: url(\"file:///android_asset/fonts/newscycle_regular.ttf\")\n" +
                "}\n" +
                "body {\n" +
                "    font-family: MyFont;\n" +
                "    font-size: medium;\n" +
                "    text-align: justify;\n" +
                "}</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored)
    {
        if (!wasRestored)
        {
            player.cueVideo(youtubeUrl);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason)
    {
        if (errorReason.isUserRecoverableError())
        {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }
        else
        {
//            String errorMessage = String.format(getString(R.string.error_player), errorReason.toString());
//            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

//    @Override
//    protected YouTubePlayer.Provider getYouTubePlayerProvider()
//    {
//        return (YouTubePlayerView) findViewById(R.id.feed_detail_content_fragment_view);
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == RECOVERY_DIALOG_REQUEST)
        {
            // Retry initialization if user performed a recovery action
//            getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY, this);
        }
    }
}
