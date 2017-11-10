package com.mauriciotogneri.momowars.firebase

import com.mauriciotogneri.momowars.utils.await
import kotlin.js.Promise

external class Query
{
    fun get(): Promise<QuerySnapshot>
}

suspend fun Query.search(): QuerySnapshot = get().await()
