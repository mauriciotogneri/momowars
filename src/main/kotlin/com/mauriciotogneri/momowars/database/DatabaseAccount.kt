package com.mauriciotogneri.momowars.database

import com.mauriciotogneri.momowars.api.Api
import com.mauriciotogneri.momowars.document.DocumentAccount
import com.mauriciotogneri.momowars.exception.HttpException
import com.mauriciotogneri.momowars.exception.NotFoundException
import com.mauriciotogneri.momowars.exception.UnauthorizedException
import com.mauriciotogneri.momowars.firebase.CollectionReference
import com.mauriciotogneri.momowars.firebase.Query
import com.mauriciotogneri.momowars.utils.Hash
import com.mauriciotogneri.momowars.utils.await
import kotlin.js.json

class DatabaseAccount(private val database: Database)
{
    suspend fun createAccount(email: String, password: String, nickname: String): DocumentAccount
    {
        val data = json()
        data["email"] = email
        data["password"] = Hash.sha512(password)
        data["nickname"] = nickname
        data["session"] = Api.session.newSessionId()
        data["games"] = arrayOf<String>()

        return DocumentAccount(root().add(data).await().get().await())
    }

    suspend fun bySessionToken(token: String): DocumentAccount
    {
        return getAccount(root().where("session", "==", token), UnauthorizedException())
    }

    suspend fun byEmail(email: String): DocumentAccount
    {
        return getAccount(root().where("email", "==", email), NotFoundException())
    }

    suspend fun exists(email: String): Boolean
    {
        return !root().where("email", "==", email).get().await().empty
    }

    private suspend fun getAccount(query: Query, exception: HttpException): DocumentAccount
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

    private fun root(): CollectionReference
    {
        return Database.firestore.collection("accounts")
    }
}