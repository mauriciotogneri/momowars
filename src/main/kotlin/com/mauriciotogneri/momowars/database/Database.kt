package com.mauriciotogneri.momowars.database

import com.mauriciotogneri.momowars.firebase.Firestore

object Database
{
    lateinit var firestore: Firestore

    fun initialize(admin: dynamic)
    {
        firestore = admin.firestore()
    }
}
