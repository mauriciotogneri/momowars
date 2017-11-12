package com.mauriciotogneri.momowars.api.games;

import com.mauriciotogneri.momowars.api.games.LeaveGame.PathParameter;
import com.mauriciotogneri.momowars.model.headers.SessionToken;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.DELETE;
import static com.mauriciotogneri.stewie.types.StatusCode.FORBIDDEN;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/games/{gameId}",
        method = DELETE,
        description = "Leaves the given game"
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
                code = FORBIDDEN,
                description = "Not allowed to leave the given game"
        )
})
public interface LeaveGame
{
    class PathParameter
    {
        public String gameId;
    }
}