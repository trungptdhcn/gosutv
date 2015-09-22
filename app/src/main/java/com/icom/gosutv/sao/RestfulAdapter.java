package com.icom.gosutv.sao;

import com.icom.gosutv.utils.Constants;
import com.squareup.okhttp.OkHttpClient;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import java.util.concurrent.TimeUnit;

/**
 * Created by Trung on 7/22/2015.
 */
public class RestfulAdapter
{
    private static RestAdapter restAdapter;
    public static RestAdapter getRestAdapter()
    {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(600, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(600, TimeUnit.SECONDS);
        if (restAdapter == null)
        {
            restAdapter =  new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setClient(new OkClient(okHttpClient))
                    .setErrorHandler(new ErrorHandleRetrofit())
                    .build();
        }
        return restAdapter;
    }
}
