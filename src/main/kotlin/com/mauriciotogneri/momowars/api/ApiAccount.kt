package com.mauriciotogneri.momowars.api

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
        process(response) { database ->

            val sessionToken = request.headerParam(Api.SESSION_TOKEN)

            checkNotEmpty(sessionToken)

            val documentAccount = database.account.bySessionToken(sessionToken)

            response
                    .status(OK)
                    .json(documentAccount.toJson())
        }
    }

    fun createAccount(request: Request, response: Response)
    {
        process(response) { database ->

            val email = request.bodyParam("email")
            val password = request.bodyParam("password")
            val nickname = request.bodyParam("nickname")

            checkNotEmpty(email, password, nickname)

            if (database.account.exists(email))
            {
                throw ConflictException()
            }

            val documentAccount = database.account.createAccount(email, password, nickname)

            response
                    .status(CREATED)
                    .set(Api.SESSION_TOKEN, documentAccount.session())
                    .json(documentAccount.toJson())
        }
    }

    fun updateAccount(request: Request, response: Response)
    {
        process(response) { database ->

            val sessionToken = request.headerParam(Api.SESSION_TOKEN)
            val password = request.bodyParam("password")
            val nickname = request.bodyParam("nickname")

            checkNotEmpty(sessionToken, password, nickname)

            val documentAccount = database.account.bySessionToken(sessionToken)

            val json = json()
            json["password"] = Hash.sha512(password)
            json["nickname"] = nickname

            documentAccount.update(json)

            response.status(OK).json(database.account.bySessionToken(sessionToken).toJson())
        }
    }
}
