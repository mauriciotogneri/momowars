package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.exception.InternalServerErrorException
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response

class ApiUnit : BaseApi()
{
    fun moveUnits(request: Request, response: Response)
    {
        process(response)
        {
            throw InternalServerErrorException()
        }
    }

    fun recruitUnits(request: Request, response: Response)
    {
        process(response)
        {
            throw InternalServerErrorException()
        }
    }
}