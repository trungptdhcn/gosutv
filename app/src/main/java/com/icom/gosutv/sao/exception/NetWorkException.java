package com.icom.gosutv.sao.exception;

/**
 * Created by Trung on 9/17/2015.
 */
public class NetWorkException extends Throwable
{
    private String error;

    public NetWorkException(String error)
    {
        this.error = error;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }
}
