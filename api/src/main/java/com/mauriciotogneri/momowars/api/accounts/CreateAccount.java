package com.mauriciotogneri.momowars.api.accounts;

import com.mauriciotogneri.jsonschema.annotations.Format;
import com.mauriciotogneri.momowars.api.accounts.CreateAccount.DataParameter;
import com.mauriciotogneri.momowars.model.accounts.Account;
import com.mauriciotogneri.momowars.model.headers.ApplicationJson;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;
import com.mauriciotogneri.stewie.types.MimeType;

import static com.mauriciotogneri.stewie.types.Method.POST;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;

@EndPoint(
        path = "/v1/account",
        method = POST,
        description = "Creates a user's account"
)
@Parameters(
        header = ApplicationJson.class,
        data = DataParameter.class
)
@Responses({
        @Response(
                code = OK,
                description = "Successful operation",
                produces = MimeType.JSON,
                type = Account.class
        )
})
public interface CreateAccount
{
    class DataParameter
    {
        public String email;

        @Format("email")
        public String password;

        public String nickname;
    }
}