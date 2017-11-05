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
            catch (exception: BadRequestException)
            {
                response.status(400).send()
            }
            catch (exception: UnauthorizedException)
            {
                response.status(401).send()
            }
            catch (exception: NotFoundException)
            {
                response.status(404).send()
            }
            catch (exception: InternalServerError)
            {
                response.status(500).send()
            }
            catch (exception: Throwable)
            {
                response.status(500).send()
            }
        }
    }
}

class BadRequestException : CustomException()

class UnauthorizedException : CustomException()

class NotFoundException : CustomException()

class InternalServerError : CustomException()