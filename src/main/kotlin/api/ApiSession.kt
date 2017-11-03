package api

class ApiSession
{
    fun login(request:dynamic, response:dynamic):Unit
    {
        response.status(200).send("AKANT 2")
    }
}
