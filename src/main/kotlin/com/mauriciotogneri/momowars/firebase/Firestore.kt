package com.mauriciotogneri.momowars.firebase

external class Firestore
{
    fun doc(documentPath: String): DocumentReference

    fun collection(collectionPath: String): CollectionReference

    fun runTransaction(function: suspend (transaction: Transaction) -> Unit)
}
