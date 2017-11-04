package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.DatabaseAccount
import com.mauriciotogneri.momowars.express.Parameter
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response

class ApiSession
{
    fun login(request: Request, response: Response)
    {
        val email = Parameter.string(request.body.email)
        val password = Parameter.string(request.body.password)

        if (!email.isEmpty() && !password.isEmpty())
        {
            DatabaseAccount.byEmail(email)
                    .then({ documentAccount ->
                        response.status(200).send("PASSWORD: " + documentAccount.password)
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
