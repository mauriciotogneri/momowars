package com.mauriciotogneri.momowars.firebase

import kotlin.js.Json
import kotlin.js.Promise

external class Transaction
{
    fun create(documentReference: DocumentReference, data: dynamic): Transaction

    fun delete(documentReference: DocumentReference): Transaction

    fun get(documentReference: DocumentReference): Promise<DocumentSnapshot>

    fun get(query: Query): Promise<QuerySnapshot>

    fun set(documentReference: DocumentReference, data: dynamic): Transaction

    fun update(documentReference: DocumentReference, value: Json): Transaction
}