package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.exception.BadRequestException
import com.mauriciotogneri.momowars.exception.CustomException
import com.mauriciotogneri.momowars.express.Response

open class BaseApi
{
    fun checkNotEmpty(vararg list: String)
    {
        for (element in list)
        {
            if (element.isEmpty())
            {
                throw BadRequestException()
            }
        }
    }

    fun processException(exception: Throwable, response: Response) = CustomException.process(exception, response)
}