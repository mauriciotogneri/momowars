package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.DatabaseAccount
import com.mauriciotogneri.momowars.exception.ConflictException
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.express.bodyParam
import com.mauriciotogneri.momowars.express.headerParam
import com.mauriciotogneri.momowars.utils.launch

class ApiAccount : BaseApi()
{
    fun getAccount(request: Request, response: Response)
    {
        launch {
            try
            {
                val sessionToken = request.headerParam(Api.SESSION_TOKEN)

                val documentAccount = DatabaseAccount.bySessionToken(sessionToken)

                response
                        .status(200)
                        .json(documentAccount.toJson())
            }
            catch (exception: Throwable)
            {
                processException(exception, response)
            }
        }
    }

    fun createAccount(request: Request, response: Response)
    {
        launch {
            try
            {
                val email = request.bodyParam("email")
                val password = request.bodyParam("password")
                val nickname = request.bodyParam("nickname")

                checkNotEmpty(email, password, nickname)

                if (DatabaseAccount.exists(email))
                {
                    throw ConflictException()
                }

                val documentAccount = DatabaseAccount.createAccount(email, password, nickname)

                response
                        .status(200)
                        .set(Api.SESSION_TOKEN, documentAccount.session)
                        .json(documentAccount.toJson())
            }
            catch (exception: Throwable)
            {
                processException(exception, response)
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
                processException(exception, response)
            }
        }
    }
}
