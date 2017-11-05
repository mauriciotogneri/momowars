package com.mauriciotogneri.momowars.firebase

external class DocumentSnapshot
{
    val id: String

    val ref: DocumentReference

    fun data(): dynamic
}
