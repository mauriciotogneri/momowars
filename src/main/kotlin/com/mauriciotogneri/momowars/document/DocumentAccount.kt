package com.mauriciotogneri.momowars.document

import com.mauriciotogneri.momowars.firebase.DocumentReference
import com.mauriciotogneri.momowars.firebase.DocumentSnapshot
import com.mauriciotogneri.momowars.utils.await
import kotlin.js.Json
import kotlin.js.json

class DocumentAccount(private val doc: DocumentSnapshot)
{
    val email: String = doc.data().email
    val nickname: String = doc.data().nickname
    val password: String = doc.data().password
    val session: String = doc.data().session
    val games: Array<DocumentReference> = doc.data().games

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

    fun toJson(): Json
    {
        val json = json()
        json["email"] = email
        json["nickname"] = nickname
        json["games"] = games.map { it.id }

        return json
    }
}