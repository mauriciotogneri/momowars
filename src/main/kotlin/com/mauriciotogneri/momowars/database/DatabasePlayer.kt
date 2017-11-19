package com.mauriciotogneri.momowars.database

import com.mauriciotogneri.momowars.document.DocumentAccount
import com.mauriciotogneri.momowars.document.DocumentPlayer
import com.mauriciotogneri.momowars.firebase.DocumentReference
import com.mauriciotogneri.momowars.utils.await

class DatabasePlayer(private val database: Database)
{
    suspend fun byGameRef(gameRef: DocumentReference): List<DocumentPlayer>
    {
        val snapshot = gameRef.collection("players").get().await()

        return snapshot.docs.map {
            DocumentPlayer(it, documentAccount(it.data().account))
        }
    }

    private suspend fun documentAccount(ref: DocumentReference): DocumentAccount = DocumentAccount(ref.get().await())
}