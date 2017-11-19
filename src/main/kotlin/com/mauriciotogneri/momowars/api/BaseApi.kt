package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.Database
import com.mauriciotogneri.momowars.exception.BadRequestException
import com.mauriciotogneri.momowars.exception.HttpException
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.utils.launch

open class BaseApi
{
    protected val OK = 200
    protected val CREATED = 201
    protected val NO_CONTENT = 204

    protected fun process(
            response: Response,
            function: suspend (database: Database) -> Unit)
    {
        launch {
            try
            {
                Database.firestore.runTransaction { transaction ->
                    function.invoke(Database(transaction))
                }
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