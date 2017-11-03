package api

import express.Request
import express.Response

class ApiAccount
{
    fun getAccount(request: Request, response: Response)
    {
        response.status(200).send("GET ACCOUNT")
    }
}
