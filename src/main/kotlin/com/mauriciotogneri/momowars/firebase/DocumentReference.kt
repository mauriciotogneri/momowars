package com.mauriciotogneri.momowars.firebase

import kotlin.js.Json
import kotlin.js.Promise

external class DocumentReference
{
    val id: String

    fun get(): Promise<DocumentSnapshot>

    fun update(value: Json): Promise<Nothing>

    fun collection(collectionPath: String): CollectionReference
}
