package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.exception.BadRequestException
import com.mauriciotogneri.momowars.exception.HttpException
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.utils.launch

open class BaseApi
{
    protected fun process(response: Response, block: suspend () -> Unit)
    {
        launch {
            try
            {
                block.invoke()
            }
            catch (exception: Throwable)
            {
                HttpException.process(exception, response)
            }
        }
    }

    protected fun checkNotEmpty(vararg list: String)
    {
        for (element in list)
        {
            if (element.isEmpty())
            {
                throw BadRequestException()
            }
        }
    }
}