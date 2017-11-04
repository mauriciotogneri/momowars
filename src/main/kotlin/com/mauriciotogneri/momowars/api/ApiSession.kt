package com.mauriciotogneri.momowars.api

import com.mauriciotogneri.momowars.database.Database
import com.mauriciotogneri.momowars.express.Request
import com.mauriciotogneri.momowars.express.Response

class ApiSession
{
    fun login(request: Request, response: Response)
    {
        val email = request.body.email
        val password = request.body.password

        if ((email != null) && (password != null))
        {
            Database.firestore
                .collection("accounts")
                .where("email", "==", email)
                .get()
                .then { docList ->

                    if (!docList.empty)
                    {
                        response.status(200).send(docList.docs[0].data())
                    }
                    else
                    {
                        response.status(200).send("ERROR")
                    }
                }
        }
        else
        {
            response.status(400).send("")
        }

        //response.status(200).send("LOGIN")
    }
}
