package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.utils.launch

class ApiMaps: BaseApi()
{
    fun getMaps(request: Request, response: Response)
    {
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

    fun getMap(request: Request, response: Response)
    {
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