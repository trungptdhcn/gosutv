package com.icom.gosutv.sao.exception;

import retrofit.RetrofitError;

/**
 * Created by Trung on 9/17/2015.
 */
public class UnauthorizedException extends Throwable
{
   private RetrofitError error;

    public UnauthorizedException(RetrofitError error)
    {
        this.error = error;
    }

    public RetrofitError getError()
    {
        return error;
    }

    public void setError(RetrofitError error)
    {
        this.error = error;
    }
}
