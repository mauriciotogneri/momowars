package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response

class ApiUnits : BaseApi()
{
    fun moveUnits(request: Request, response: Response)
    {
        // { movement }

        process(response)
        {
            response.status(501).send()
        }
    }

    fun recruitUnits(request: Request, response: Response)
    {
        // { type: string, quantity: int }

        process(response)
        {
            response.status(501).send()
        }
    }
}