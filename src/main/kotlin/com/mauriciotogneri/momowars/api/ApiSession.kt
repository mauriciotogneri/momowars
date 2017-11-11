package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.DatabaseAccount
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
        process(response)
        {
            val email = request.bodyParam("email")
            val password = request.bodyParam("password")

            checkNotEmpty(email, password)

            val documentAccount = DatabaseAccount.byEmail(email)

            if (!documentAccount.hasPassword(Hash.sha512(password)))
            {
                throw UnauthorizedException()
            }

            val sessionId = newSessionId()

            val update = json()
            update["session"] = sessionId

            documentAccount.update(update)

            response
                    .status(200)
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
