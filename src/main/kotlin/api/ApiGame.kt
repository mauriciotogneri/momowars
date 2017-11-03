package api

class ApiGame
{
    fun getGame(request: dynamic, response: dynamic)
    {
        response.status(200).send("GET GAME")
    }
}
