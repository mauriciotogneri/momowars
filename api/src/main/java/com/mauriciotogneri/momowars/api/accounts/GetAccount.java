package com.mauriciotogneri.momowars.api.accounts;

import com.mauriciotogneri.momowars.model.accounts.Account;
import com.mauriciotogneri.momowars.model.session.SessionToken;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;
import com.mauriciotogneri.stewie.types.MimeType;

import static com.mauriciotogneri.stewie.types.Method.GET;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;

@EndPoint(
        path = "/v1/account",
        method = GET,
        description = "Returns the user's account"
)
@Parameters(
        header = SessionToken.class
)
@Responses({
        @Response(
                code = OK,
                description = "Successful operation",
                produces = MimeType.JSON,
                type = Account.class
        )
})
public interface GetAccount
{
}