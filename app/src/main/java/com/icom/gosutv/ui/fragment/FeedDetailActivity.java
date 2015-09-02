package com.icom.gosutv.ui.fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.icom.gosutv.R;
import com.icom.gosutv.base.BaseFragment;
import com.icom.gosutv.sao.RestfulService;
import com.icom.gosutv.sao.dto.FeedDTO;
import com.icom.gosutv.sao.dto.FeedDetailDTO;
import com.icom.gosutv.ui.adapter.CommonRecycleViewAdapter;
import com.icom.gosutv.ui.model.FeedModel;
import com.icom.gosutv.utils.Constants;
import com.icom.gosutv.utils.ImageUtil;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

/**
 * Created by Trung on 9/1/2015.
 */
public class FeedDetailActivity extends Activity
{
    @InjectView(R.id.feed_detail_fragment_tvTitle)
    TextView tvTitle;
    @InjectView(R.id.feed_detail_fragment_tvDes)
    WebView tvDes;
    @InjectView(R.id.feed_detail_fragment_bar)
    ProgressWheel progressWheel;
    private String slug;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_detail_fragment);
        ButterKnife.inject(this);
        slug = getIntent().getStringExtra(Constants.SLUG);
        tvTitle.setTypeface(Typeface.SERIF);
//        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/newscycle_regular.ttf");
        tvDes.getSettings().setJavaScriptEnabled(true);
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
//                FeedModel feedModel = FeedModel.convertFromFeedDTO(feedDTO);
                tvTitle.setText(feedDTO.getItemDTO().getTitle());
//                tvDes.setText(feedDTO.getItemDTO().getSapo());
                tvDes.loadDataWithBaseURL(null
                        ,getHtmlData(feedDTO.getItemDTO().getContent()), "text/html", "UTF-8", null);
            }
        }.execute();
    }

    private String getHtmlData(String bodyHTML)
    {
        String head = "<head><style type=\"text/css\">img{max-width: 100%; width:auto; height: auto;}" +
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
}
