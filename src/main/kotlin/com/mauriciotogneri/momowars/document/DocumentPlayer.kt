package com.mauriciotogneri.momowars.document

import com.mauriciotogneri.momowars.firebase.DocumentSnapshot

class DocumentPlayer(doc: DocumentSnapshot, val account: DocumentAccount)
{
    val color = doc.data().color
    val status = doc.data().status
}