package com.mauriciotogneri.momowars.validation.endpoints.session;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.body.JsonBodyParameter;
import com.mauriciotogneri.momowars.api.sessions.Login;
import com.mauriciotogneri.momowars.validation.BaseEndPoint;
import com.mauriciotogneri.momowars.validation.EndPointDefinition;

public class LoginEndPoint extends BaseEndPoint implements Login
{
    public LoginEndPoint()
    {
        super(new EndPointDefinition(Login.class));
    }

    public ApiResult execute(String email, String password) throws Exception
    {
        DataParameter data = new DataParameter();
        data.email = email;
        data.password = password;

        ApiRequest.Builder builder = request();
        builder.header(CONTENT_TYPE_JSON);
        builder.body(new JsonBodyParameter(data));
        builder.response(emptyResponse());

        return process(builder);
    }
}