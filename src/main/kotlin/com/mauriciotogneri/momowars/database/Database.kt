package com.mauriciotogneri.momowars.database

import com.mauriciotogneri.momowars.firebase.Firestore
import com.mauriciotogneri.momowars.firebase.Transaction

class Database(val transaction: Transaction)
{
    val account = DatabaseAccount(this)
    val game = DatabaseGame(this)
    val player = DatabasePlayer(this)

    companion object
    {
        lateinit var firestore: Firestore

        fun initialize(admin: dynamic)
        {
            firestore = admin.firestore()
        }
    }
}