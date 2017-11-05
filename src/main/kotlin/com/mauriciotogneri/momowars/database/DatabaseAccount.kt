package com.mauriciotogneri.momowars.database

import com.mauriciotogneri.momowars.document.DocumentAccount
import com.mauriciotogneri.momowars.exception.CustomException
import com.mauriciotogneri.momowars.exception.NotFoundException
import com.mauriciotogneri.momowars.exception.UnauthorizedException
import com.mauriciotogneri.momowars.firebase.CollectionReference
import com.mauriciotogneri.momowars.firebase.Query
import com.mauriciotogneri.momowars.utils.await

object DatabaseAccount
{
    suspend fun bySessionToken(token: String): DocumentAccount
    {
        return getAccount(root().where("session", "==", token), UnauthorizedException())
    }

    suspend fun byEmail(email: String): DocumentAccount
    {
        return getAccount(root().where("email", "==", email), NotFoundException())
    }

    private suspend fun getAccount(query: Query, exception: CustomException): DocumentAccount
    {
        val snapshot = query.get().await()

        if (!snapshot.empty)
        {
            return DocumentAccount(snapshot.docs[0])
        }
        else
        {
            throw exception
        }
    }

    /*fun listByRef(accountRefs: List<dynamic>): Promise<String>
    {
        accountRefs.map { ref -> ref.get }

        return Promise.all()

        return Promise.all(accountRefs.map(ref => ref.get()))
    }*/

    private fun root(): CollectionReference
    {
        return Database.firestore.collection("accounts")
    }
}