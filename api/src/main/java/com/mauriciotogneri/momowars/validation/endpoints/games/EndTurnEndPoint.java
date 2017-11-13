package com.mauriciotogneri.momowars.validation.endpoints.games;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.path.PathParameters;
import com.mauriciotogneri.momowars.api.games.EndTurn;
import com.mauriciotogneri.momowars.validation.BaseEndPoint;
import com.mauriciotogneri.momowars.validation.EndPointDefinition;

import okhttp3.RequestBody;

public class EndTurnEndPoint extends BaseEndPoint implements EndTurn
{
    public EndTurnEndPoint()
    {
        super(new EndPointDefinition(EndTurn.class));
    }

    public ApiResult execute(String sessionToken, String gameId) throws Exception
    {
        PathParameter path = new PathParameter();
        path.gameId = gameId;

        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.body(RequestBody.create(null, new byte[0]));
        builder.path(new PathParameters(path), PATH_FORMAT);
        builder.response(jsonResponse());

        return process(builder);
    }
}