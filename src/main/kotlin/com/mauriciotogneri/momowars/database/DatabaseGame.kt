package com.mauriciotogneri.momowars.database

import com.mauriciotogneri.momowars.document.DocumentGame
import com.mauriciotogneri.momowars.firebase.DocumentReference
import com.mauriciotogneri.momowars.utils.await

object DatabaseGame
{
    suspend fun byRef(reference: DocumentReference): DocumentGame
    {
        val documentsPlayer = DatabasePlayer.byGameRef(reference)

        val snapshot = reference.get().await()

        return DocumentGame(snapshot, documentsPlayer)
    }
}