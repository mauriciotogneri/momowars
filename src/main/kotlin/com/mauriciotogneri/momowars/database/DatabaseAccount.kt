package com.mauriciotogneri.momowars.database

import com.mauriciotogneri.momowars.firebase.CollectionReference
import com.mauriciotogneri.momowars.firebase.DocumentSnapshot
import kotlin.js.Promise

class DatabaseAccount
{
    fun bySessionToken(token: String): Promise<DocumentSnapshot>
    {
        return getAccount(root().where("session", "==", token).get())
    }

    fun byEmail(email: String): Promise<DocumentSnapshot>
    {
        return getAccount(root().where("email", "==", email).get())
    }

    /*fun listByRef(accountRefs: List<dynamic>): Promise<String>
    {
        accountRefs.map { ref -> ref.get }

        return Promise.all()
    }*/

    private fun getAccount(queryPromise: Promise<dynamic>): Promise<DocumentSnapshot>
    {
        return Promise { resolve, reject ->

            queryPromise.then({ docList ->

                if (!docList.empty)
                {
                    //resolve(new document . account (docList.docs[0]))
                    resolve(docList.docs[0])
                }
                else
                {
                    reject
                }
            })
        }
    }

    private fun root(): CollectionReference
    {
        return Database.firestore.collection("accounts")
    }
}
