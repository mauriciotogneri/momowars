package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.DatabaseAccount
import com.mauriciotogneri.momowars.exception.CustomException
import com.mauriciotogneri.momowars.express.Parameter
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.utils.launch

class ApiAccount
{
    fun getAccount(request: Request, response: Response)
    {
        launch {
            try
            {
                val sessionToken = Parameter.string(request.get(Api.SESSION_TOKEN))

                val documentAccount = DatabaseAccount.bySessionToken(sessionToken)

                response
                        .status(200)
                        .json(documentAccount.toJson())
            }
            catch (exception: Throwable)
            {
                CustomException.process(exception, response)
            }
        }
    }

    fun createAccount(request: Request, response: Response)
    {
        launch {
            try
            {
                val email = Parameter.string(request.body.email)
                val password = Parameter.string(request.body.password)
                val nickname = Parameter.string(request.body.nickname)

                // TODO: check if email exists

                val documentAccount = DatabaseAccount.createAccount(email, password, nickname)

                console.log("DONE")
                console.log(documentAccount.toJson())

                response
                        .status(200)
                        .set(Api.SESSION_TOKEN, documentAccount.session)
                        .json(documentAccount.toJson())
            }
            catch (exception: Throwable)
            {
                CustomException.process(exception, response)
            }
        }
    }

    fun updateAccount(request: Request, response: Response)
    {
        launch {
            try
            {
                response.status(501).send()
            }
            catch (exception: Throwable)
            {
                CustomException.process(exception, response)
            }
        }
    }
}
