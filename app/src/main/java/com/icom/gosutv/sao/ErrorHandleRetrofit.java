package com.icom.gosutv.sao;

import com.icom.gosutv.sao.exception.NetWorkException;
import com.icom.gosutv.sao.exception.UnauthorizedException;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Trung on 9/17/2015.
 */
public class ErrorHandleRetrofit implements ErrorHandler
{
    @Override
    public Throwable handleError(RetrofitError cause)
    {
        Response r = cause.getResponse();
        if (r != null && r.getStatus() == 401)
        {
            return new UnauthorizedException(cause);
        }
        if (cause.getKind() == RetrofitError.Kind.NETWORK)
        {
            return new NetWorkException("No connection");
        }
        return cause;
    }
}
