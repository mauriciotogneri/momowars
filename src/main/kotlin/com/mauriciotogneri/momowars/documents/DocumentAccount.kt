package com.mauriciotogneri.momowars.documents

import com.mauriciotogneri.momowars.firebase.DocumentReference
import com.mauriciotogneri.momowars.firebase.DocumentSnapshot
import com.mauriciotogneri.momowars.utils.await
import kotlin.js.Json

class DocumentAccount(document: DocumentSnapshot)
{
    private val doc = document
    private val data = doc.data()

    val email: String = data.email
    val nickname: String = data.nickname
    val password: String = data.password
    val games: Array<DocumentReference> = data.games

    fun hasPassword(text: String): Boolean
    {
        return (password == text)
    }

    suspend fun update(value: Json)
    {
        doc.ref.update(value).await()
    }

    fun gameRef(gameId: String): DocumentReference?
    {
        return games.find { ref -> ref.id == gameId }
    }
}