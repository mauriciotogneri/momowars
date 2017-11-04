package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response

class ApiAccount
{
    fun getAccount(request: Request, response: Response)
    {
        response.status(200).send("GET ACCOUNT")
    }
}
