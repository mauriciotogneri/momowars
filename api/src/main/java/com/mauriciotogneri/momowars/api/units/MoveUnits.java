package com.mauriciotogneri.momowars.api.units;

import com.mauriciotogneri.momowars.api.units.MoveUnits.DataParameter;
import com.mauriciotogneri.momowars.model.constants.MovementType;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.PATCH;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/games/{gameId}/cells/{cellId}/units/{unitId}/move",
        method = PATCH,
        description = "Enqueues the command to move the units"
)
@Parameters(
        data = DataParameter.class
)
@Responses({
        @Response(
                code = OK,
                description = "Successful operation"
        ),
        @Response(
                code = BAD_REQUEST,
                description = "Parameters missing or invalid"
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session token not valid"
        ),
        @Response(
                code = NOT_FOUND,
                description = "Invalid gameId, cellId or unitId"
        )
})
public interface MoveUnits
{
    class DataParameter
    {
        public MovementType movement;
    }
}