package api

import express.Request
import express.Response

class ApiGame
{
    fun getGame(request: Request, response: Response)
    {
        response.status(200).send("GET GAME 2")
    }
}
