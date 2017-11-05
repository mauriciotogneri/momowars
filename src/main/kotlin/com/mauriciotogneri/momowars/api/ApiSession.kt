package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.DatabaseAccount
import com.mauriciotogneri.momowars.exception.BadRequestException
import com.mauriciotogneri.momowars.exception.CustomException
import com.mauriciotogneri.momowars.exception.UnauthorizedException
import com.mauriciotogneri.momowars.express.Parameter
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.utils.Hash
import com.mauriciotogneri.momowars.utils.launch
import kotlin.js.Date
import kotlin.js.Math
import kotlin.js.json

class ApiSession
{
    fun login(request: Request, response: Response)
    {
        launch {
            try
            {
                val email = Parameter.string(request.body.email)
                val password = Parameter.string(request.body.password)

                if (!email.isEmpty() && !password.isEmpty())
                {
                    val documentAccount = DatabaseAccount.byEmail(email)

                    if (documentAccount.hasPassword(Hash.sha512(password)))
                    {
                        val seed = Math.random().toString().substring(2) + Date().getTime().toString()
                        val sessionId = Hash.sha512(seed)

                        val update = json(Pair("session", sessionId))
                        documentAccount.update(update)

                        response
                                .status(200)
                                .set(Api.SESSION_TOKEN, sessionId)
                                .send()
                    }
                    else
                    {
                        throw UnauthorizedException()
                    }
                }
                else
                {
                    throw BadRequestException()
                }
            }
            catch (exception: Throwable)
            {
                CustomException.process(exception, response)
            }
        }
    }
}
