package com.mauriciotogneri.momowars.api.games;

import com.mauriciotogneri.momowars.api.games.EndTurn.PathParameter;
import com.mauriciotogneri.momowars.model.headers.SessionToken;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.PATCH;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/games/{gameId}",
        method = PATCH,
        description = "Ends the turn of the player for the given game"
)
@Parameters(
        header = SessionToken.class,
        path = PathParameter.class
)
@Responses({
        @Response(
                code = OK,
                description = "Successful operation"
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session token not valid"
        ),
        @Response(
                code = BAD_REQUEST,
                description = "Session token not provided"
        ),
        @Response(
                code = 412,
                description = "Not allowed to end the turn on the given game"
        )
})
public interface EndTurn
{
    class PathParameter
    {
        public String gameId;
    }
}