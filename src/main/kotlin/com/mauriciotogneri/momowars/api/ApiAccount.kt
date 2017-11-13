package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.DatabaseAccount
import com.mauriciotogneri.momowars.exception.ConflictException
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.express.bodyParam
import com.mauriciotogneri.momowars.express.headerParam
import com.mauriciotogneri.momowars.utils.Hash
import kotlin.js.json

class ApiAccount : BaseApi()
{
    fun getAccount(request: Request, response: Response)
    {
        process(response)
        {
            val sessionToken = request.headerParam(Api.SESSION_TOKEN)

            checkNotEmpty(sessionToken)

            val documentAccount = DatabaseAccount.bySessionToken(sessionToken)

            response
                    .status(200)
                    .json(documentAccount.toJson())
        }
    }

    fun createAccount(request: Request, response: Response)
    {
        process(response)
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
                    .set(Api.SESSION_TOKEN, documentAccount.session())
                    .json(documentAccount.toJson())
        }
    }

    fun updateAccount(request: Request, response: Response)
    {
        process(response)
        {
            val sessionToken = request.headerParam(Api.SESSION_TOKEN)
            val password = request.bodyParam("password")
            val nickname = request.bodyParam("nickname")

            checkNotEmpty(sessionToken, password, nickname)

            val documentAccount = DatabaseAccount.bySessionToken(sessionToken)

            val json = json()
            json["password"] = Hash.sha512(password)
            json["nickname"] = nickname

            documentAccount.update(json)

            response.status(200).json(DatabaseAccount.bySessionToken(sessionToken).toJson())
        }
    }
}
