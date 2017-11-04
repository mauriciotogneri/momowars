package com.mauriciotogneri.momowars.firebase

import kotlin.js.Promise

external class Query
{
    fun get(): Promise<QuerySnapshot>
}
