package com.mauriciotogneri.momowars.exception

import com.mauriciotogneri.momowars.express.Response

open class HttpException(val code: Int) : Exception()
{
    companion object
    {
        fun process(exception: Throwable, response: Response)
        {
            try
            {
                throw exception
            }
            catch (exception: HttpException)
            {
                response.status(exception.code).send(exception.toString())
            }
            catch (exception: Throwable)
            {
                response.status(500).send(exception.toString())
            }
        }
    }
}

class BadRequestException : HttpException(400)
class UnauthorizedException : HttpException(401)
class ForbiddenException : HttpException(403)
class NotFoundException : HttpException(404)
class ConflictException : HttpException(409)
class PreconditionFailedException : HttpException(412)
class InternalServerErrorException : HttpException(500)