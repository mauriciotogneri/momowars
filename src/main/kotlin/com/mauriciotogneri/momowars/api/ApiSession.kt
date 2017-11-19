package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.exception.UnauthorizedException
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.express.bodyParam
import com.mauriciotogneri.momowars.utils.Hash
import kotlin.js.Date
import kotlin.js.Math
import kotlin.js.json

class ApiSession : BaseApi()
{
    fun createSession(request: Request, response: Response)
    {
        process(response) { database ->

            val email = request.bodyParam("email")
            val password = request.bodyParam("password")

            checkNotEmpty(email, password)

            val documentAccount = database.account.byEmail(email)

            if (!documentAccount.hasPassword(Hash.sha512(password)))
            {
                throw UnauthorizedException()
            }

            val sessionId = newSessionId()

            val json = json()
            json["session"] = sessionId

            documentAccount.update(json)

            response
                    .status(CREATED)
                    .set(Api.SESSION_TOKEN, sessionId)
                    .send()
        }
    }

    fun newSessionId(): String
    {
        val seed = Math.random().toString().substring(2) + Date().getTime().toString()

        return Hash.sha512(seed)
    }
}
