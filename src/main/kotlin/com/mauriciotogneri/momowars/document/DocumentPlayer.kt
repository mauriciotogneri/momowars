package com.mauriciotogneri.momowars.document

import com.mauriciotogneri.momowars.firebase.DocumentSnapshot
import kotlin.js.Json
import kotlin.js.json

class DocumentPlayer(doc: DocumentSnapshot, private val account: DocumentAccount)
{
    val color = doc.data().color
    val status = doc.data().status

    fun toJson(): Json
    {
        val json = json()
        json["nickname"] = account.nickname
        json["color"] = color
        json["status"] = status

        return json
    }

/*players: [
	{
		nickname: string,
		color: string,
		status: turn_status,

		cells: int,
		bases: int,
		units: int
	}
]*/
}