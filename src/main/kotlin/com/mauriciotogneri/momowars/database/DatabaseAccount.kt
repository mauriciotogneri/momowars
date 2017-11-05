package com.mauriciotogneri.momowars.database

import com.mauriciotogneri.momowars.documents.DocumentAccount
import com.mauriciotogneri.momowars.exception.HttpException404
import com.mauriciotogneri.momowars.firebase.CollectionReference
import com.mauriciotogneri.momowars.firebase.Query
import com.mauriciotogneri.momowars.utils.await
import kotlin.coroutines.experimental.suspendCoroutine

object DatabaseAccount
{
    suspend fun bySessionToken(token: String): DocumentAccount
    {
        return getAccount(root().where("session", "==", token))
    }

    suspend fun byEmail(email: String): DocumentAccount
    {
        return getAccount(root().where("email", "==", email))
    }

    suspend fun test(email: String): DocumentAccount =
            suspendCoroutine { cont ->

                console.log("E0")

                DatabaseAccount.root()
                        .where("email", "==", email)
                        .get()
                        .then({ docs ->

                            if (!docs.empty)
                            {
                                cont.resume(DocumentAccount(docs.docs[0]))
                            }
                            else
                            {
                                console.log("E1: throwing 404")
                                throw HttpException404()
                            }
                        })
                        .catch({ exception ->
                            cont.resumeWithException(exception)
                        })
            }

    private suspend fun getAccount(query: Query): DocumentAccount
    {
        val snapshot = query.get().await()

        if (!snapshot.empty)
        {
            return DocumentAccount(snapshot.docs[0])
        }
        else
        {
            throw Throwable()
        }
    }

    /*fun listByRef(accountRefs: List<dynamic>): Promise<String>
    {
        accountRefs.map { ref -> ref.get }

        return Promise.all()
    }*/

    private fun root(): CollectionReference
    {
        return Database.firestore.collection("accounts")
    }
}