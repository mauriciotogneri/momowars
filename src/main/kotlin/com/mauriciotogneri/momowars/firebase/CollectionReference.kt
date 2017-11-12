package com.mauriciotogneri.momowars.firebase

import kotlin.js.Json
import kotlin.js.Promise

external class CollectionReference
{
    fun add(json: Json): Promise<DocumentReference>

    fun get(): Promise<QuerySnapshot>

    fun where(fieldPath: String, opStr: String, value: dynamic): Query
}