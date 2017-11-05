package com.mauriciotogneri.momowars.database

import com.mauriciotogneri.momowars.documents.DocumentAccount
import com.mauriciotogneri.momowars.firebase.CollectionReference
import com.mauriciotogneri.momowars.firebase.QuerySnapshot
import com.mauriciotogneri.momowars.utils.await
import kotlin.js.Promise

object DatabaseAccount
{
    /*fun bySessionToken2(token: String): Promise<DocumentAccount>
    {
        return getAccount(root().where("session", "==", token).get())
    }*/

    suspend fun bySessionToken(token: String): DocumentAccount
    {
        return DocumentAccount(root()
                .where("session", "==", token)
                .get()
                .await().docs[0])
    }

    fun byEmail(email: String): Promise<DocumentAccount>
    {
        return getAccount(root().where("email", "==", email).get())
    }

    /*fun listByRef(accountRefs: List<dynamic>): Promise<String>
    {
        accountRefs.map { ref -> ref.get }

        return Promise.all()
    }*/

    private fun getAccount(queryPromise: Promise<QuerySnapshot>): Promise<DocumentAccount>
    {
        return Promise { resolve, reject ->

            queryPromise.then({ docList ->

                if (!docList.empty)
                {
                    resolve(DocumentAccount(docList.docs[0]))
                }
                else
                {
                    reject(Throwable())
                }
            })
        }
    }

    private fun root(): CollectionReference
    {
        return Database.firestore.collection("accounts")
    }
}
