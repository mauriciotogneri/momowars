package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response

class ApiMap : BaseApi()
{
    fun getMaps(request: Request, response: Response)
    {
        process(response)
        {
            response.status(501).send()
        }
    }

    fun getMap(request: Request, response: Response)
    {
        process(response)
        {
            response.status(501).send()
        }
    }
}