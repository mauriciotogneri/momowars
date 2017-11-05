package com.mauriciotogneri.momowars.database

import com.mauriciotogneri.momowars.document.DocumentAccount
import com.mauriciotogneri.momowars.document.DocumentPlayer
import com.mauriciotogneri.momowars.firebase.DocumentReference
import com.mauriciotogneri.momowars.utils.await

object DatabasePlayer
{
    suspend fun byGameRef(gameRef: DocumentReference): List<DocumentPlayer>
    {
        val snapshot = gameRef.collection("players").get().await()

        return snapshot.docs.map {
            val accountRef = it.data().account as DocumentReference

            DocumentPlayer(it, DocumentAccount(accountRef.get().await()))
        }
    }
}