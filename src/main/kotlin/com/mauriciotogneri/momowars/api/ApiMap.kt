package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.exception.InternalServerErrorException
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response

class ApiMap : BaseApi()
{
    fun getMaps(request: Request, response: Response)
    {
        process(response)
        {
            throw InternalServerErrorException()
        }
    }

    fun getMap(request: Request, response: Response)
    {
        process(response)
        {
            throw InternalServerErrorException()
        }
    }
}