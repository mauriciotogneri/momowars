package com.mauriciotogneri.momowars.document

import com.mauriciotogneri.momowars.firebase.DocumentReference
import com.mauriciotogneri.momowars.firebase.DocumentSnapshot
import com.mauriciotogneri.momowars.utils.await
import kotlin.js.Json
import kotlin.js.json

class DocumentAccount(private val doc: DocumentSnapshot)
{
    private val email: String = doc.data().email
    private val nickname: String = doc.data().nickname
    private val password: String = doc.data().password
    private val session: String = doc.data().session
    private val games: Array<DocumentReference> = doc.data().games

    fun id() = doc.id

    fun session() = session

    fun nickname() = nickname

    fun hasPassword(text: String): Boolean
    {
        return (password == text)
    }

    suspend fun update(json: Json)
    {
        doc.ref.update(json).await()
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