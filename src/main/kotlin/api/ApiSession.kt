package api

import express.Request
import express.Response

class ApiSession
{
    fun login(request: Request, response: Response)
    {
        response.status(200).send("LOGIN")
    }
}
