package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.DatabaseAccount
import com.mauriciotogneri.momowars.express.Parameter
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response
import com.mauriciotogneri.momowars.utils.launch

class ApiAccount
{
    fun getAccount(request: Request, response: Response)
    {
        launch {
            try
            {
                val sessionToken = Parameter.string(request.get(Api.SESSION_TOKEN))

                val documentAccount = DatabaseAccount.bySessionToken(sessionToken)

                response.status(200).send(documentAccount)
            }
            catch (exception: Throwable)
            {
                response.status(401).send()
            }
        }
    }

    /*private suspend fun getAccountByEmail(email: String): DocumentAccount
    {
        val doc = Database.firestore
                .collection("accounts")
                .where("email", "==", email)
                .get()
                .await().docs[0]

        return DocumentAccount(doc)
    }*/
}
