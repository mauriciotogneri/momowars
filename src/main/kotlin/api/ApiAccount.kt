package api

class ApiAccount
{
    fun getAccount(request: dynamic, response: dynamic)
    {
        response.status(200).send("GET ACCOUNT")
    }
}
