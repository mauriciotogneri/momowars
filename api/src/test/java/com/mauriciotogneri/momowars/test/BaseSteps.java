package com.mauriciotogneri.momowars.test;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.javautils.Json;
import com.mauriciotogneri.momowars.model.accounts.Account;
import com.mauriciotogneri.momowars.validation.endpoints.accounts.CreateAccountEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.accounts.GetAccountEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.games.GetGameEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.session.LoginEndPoint;

import org.junit.Assert;

public class BaseSteps
{
    public static String SESSION_TOKEN;
    public static Account ACCOUNT;

    protected final LoginEndPoint loginEndPoint = new LoginEndPoint();
    protected final GetAccountEndPoint getAccountEndPoint = new GetAccountEndPoint();
    protected final CreateAccountEndPoint createAccountEndPoint = new CreateAccountEndPoint();
    protected final GetGameEndPoint getGameEndPoint = new GetGameEndPoint();

    @SuppressWarnings("unchecked")
    protected <T> T json(ApiResult apiResult, Class<T> clazz) throws Exception
    {
        if (apiResult.isValid())
        {
            T result = Json.object(apiResult.string(), clazz);

            if (result == null)
            {
                throw new RuntimeException("Received empty response");
            }

            return result;
        }
        else
        {
            throw new RuntimeException(apiResult.error());
        }
    }

    protected void checkHttpStatus(int expected, ApiResult apiResult)
    {
        Assert.assertEquals(expected, apiResult.code());
    }
}