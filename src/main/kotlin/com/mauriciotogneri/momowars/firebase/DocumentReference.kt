package com.mauriciotogneri.momowars.firebase

import kotlin.js.Promise

external class DocumentReference
{
    val id: String

    fun update(value: dynamic): Promise<Nothing>
}
