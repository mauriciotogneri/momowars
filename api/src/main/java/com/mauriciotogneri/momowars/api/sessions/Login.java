package com.mauriciotogneri.momowars.api.sessions;

import com.mauriciotogneri.jsonschema.annotations.Format;
import com.mauriciotogneri.momowars.api.sessions.Login.DataParameter;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.POST;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;

@EndPoint(
        path = "/v1/session",
        method = POST,
        description = "Creates a new session"
)
@Parameters(
        data = DataParameter.class
)
@Responses({
        @Response(
                code = OK,
                description = "Successful operation"
        )
})
public interface Login
{
    class DataParameter
    {
        @Format("email")
        public String email;

        public String password;
    }
}