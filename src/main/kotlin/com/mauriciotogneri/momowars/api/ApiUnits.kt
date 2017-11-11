package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.utils.launch

class ApiUnits: BaseApi()
{
    fun moveUnits(request: Request, response: Response)
    {
        // { movement }
        launch {
            try
            {
                response.status(501).send()
            }
            catch (exception: Throwable)
            {
                processException(exception, response)
            }
        }
    }

    fun recruitUnits(request: Request, response: Response)
    {
        // { type: string, quantity: int }
        launch {
            try
            {
                response.status(501).send()
            }
            catch (exception: Throwable)
            {
                processException(exception, response)
            }
        }
    }
}