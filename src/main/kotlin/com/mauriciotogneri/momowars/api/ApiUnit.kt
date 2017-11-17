package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response

class ApiUnit : BaseApi()
{
    fun moveUnits(request: Request, response: Response)
    {
        process(response)
        {
            response.status(501).send()
        }
    }

    fun recruitUnits(request: Request, response: Response)
    {
        process(response)
        {
            response.status(501).send()
        }
    }
}