package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.DatabaseAccount
import com.mauriciotogneri.momowars.express.Parameter
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.utils.Hash
import kotlin.js.Date
import kotlin.js.Math
import kotlin.js.json

@Suppress("IMPLICIT_CAST_TO_ANY")
class ApiSession
{
    fun login(request: Request, response: Response)
    {
        val email = Parameter.string(request.body.email)
        val password = Parameter.string(request.body.password)

        if (!email.isEmpty() && !password.isEmpty())
        {
            DatabaseAccount
                    .byEmail(email)
                    .then({ documentAccount ->

                        if (documentAccount.hasPassword(Hash.sha512(password)))
                        {
                            val seed = Math.random().toString().substring(2) + Date().getTime().toString()
                            val sessionId = Hash.sha512(seed)

                            documentAccount.update(json(Pair("session", sessionId)))
                                    .then({
                                        response
                                                .status(200)
                                                .set(Api.SESSION_TOKEN, sessionId)
                                                .send()
                                    })
                        }
                        else
                        {
                            response.status(401).send()
                        }
                    })
                    .catch({
                        response.status(404).send()
                    })
        }
        else
        {
            response.status(400).send()
        }
    }
}
