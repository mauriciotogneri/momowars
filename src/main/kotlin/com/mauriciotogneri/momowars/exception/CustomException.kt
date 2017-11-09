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
            catch (exception: ForbiddenException)
            {
                response.status(403).send()
            }
            catch (exception: NotFoundException)
            {
                response.status(404).send()
            }
            catch (exception: ConflictException)
            {
                response.status(409).send()
            }
            catch (exception: InternalServerError)
            {
                response.status(500).send()
            }
            catch (exception: Throwable)
            {
                response.status(500).send(exception.toString())
            }
        }
    }
}

// 400
class BadRequestException : CustomException()

// 401
class UnauthorizedException : CustomException()

// 403
class ForbiddenException : CustomException()

// 404
class NotFoundException : CustomException()

// 409
class ConflictException : CustomException()

// 500
class InternalServerError : CustomException()