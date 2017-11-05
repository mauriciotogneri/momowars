package com.mauriciotogneri.momowars.firebase

import kotlin.js.Promise

external class CollectionReference
{
    fun get(): Promise<QuerySnapshot>

    fun where(fieldPath: String, opStr: String, value: dynamic): Query
}
