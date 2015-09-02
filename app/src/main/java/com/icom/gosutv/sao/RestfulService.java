package com.icom.gosutv.sao;

/**
 * Created by Trung on 7/22/2015.
 */
public class RestfulService
{
    private static RestfulServiceIn restfulServiceIn;

    public static RestfulServiceIn getInstance()
    {
        if (restfulServiceIn != null)
        {
            return restfulServiceIn;
        }
        else
        {
            restfulServiceIn = RestfulAdapter.getRestAdapter().create(RestfulServiceIn.class);
            return restfulServiceIn;
        }
    }
}
