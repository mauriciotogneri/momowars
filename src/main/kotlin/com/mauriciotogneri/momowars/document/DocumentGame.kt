package com.mauriciotogneri.momowars.document

import com.mauriciotogneri.momowars.firebase.DocumentSnapshot

class DocumentGame(doc: DocumentSnapshot)
{
    val id = doc.id
    val status = doc.data().status
    val map = doc.data().map
}