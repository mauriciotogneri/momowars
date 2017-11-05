package com.mauriciotogneri.momowars.exception

import com.mauriciotogneri.momowars.express.Response

open class CustomException : Exception()
{
    companion object
    {
        fun process(exception: Throwable, response: Response)
        {
            try
            {
                throw exception
            }
            catch (exception: HttpException401)
            {
                response.status(401).send()
            }
            catch (exception: HttpException404)
            {
                response.status(404).send()
            }
            catch (exception: HttpException405)
            {
                response.status(405).send()
            }
            catch (exception: Throwable)
            {
                response.status(500).send()
            }
        }
    }
}

class HttpException401 : CustomException()

class HttpException404 : CustomException()

class HttpException405 : CustomException()