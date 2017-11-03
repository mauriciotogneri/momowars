package api

class ApiSession
{
    fun login(request: dynamic, response: dynamic)
    {
        response.status(200).send("LOGIN")
    }
}
