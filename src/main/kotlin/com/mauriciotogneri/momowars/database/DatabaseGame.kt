package com.mauriciotogneri.momowars.database

import com.mauriciotogneri.momowars.constants.GameStatus
import com.mauriciotogneri.momowars.document.DocumentGame
import com.mauriciotogneri.momowars.firebase.DocumentReference
import com.mauriciotogneri.momowars.firebase.search
import com.mauriciotogneri.momowars.utils.await

class DatabaseGame(private val database: Database)
{
    suspend fun byRef(reference: DocumentReference): DocumentGame
    {
        val documentsPlayer = database.player.byGameRef(reference)

        val snapshot = reference.get().await()

        return DocumentGame(snapshot, documentsPlayer)
    }

    suspend fun getOpenGames(): List<DocumentGame>
    {
        return Database
                .firestore
                .collection("games")
                .where("status", "==", GameStatus.OPEN.value())
                .search()
                .docs
                .map { DocumentGame(it, database.player.byGameRef(it.ref)) }
    }
}