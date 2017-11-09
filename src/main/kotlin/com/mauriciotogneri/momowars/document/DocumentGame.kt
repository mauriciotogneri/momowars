package com.mauriciotogneri.momowars.document

import com.mauriciotogneri.momowars.firebase.DocumentSnapshot
import kotlin.js.Json
import kotlin.js.json

class DocumentGame(doc: DocumentSnapshot, private val documentsPlayer: List<DocumentPlayer>)
{
    val id = doc.id
    val status = doc.data().status
    val map = doc.data().map

    fun toJson(): Json
    {
        val json = json()
        json["id"] = id
        json["status"] = status
        json["map"] = map
        json["resources"] = 0 // TODO
        json["players"] = documentsPlayer.map { it.toJson() }
        json["cells"] = arrayOf<String>() // TODO

        return json
    }

/*cells: [
	{
		id: string,
		x: int,
		y: int,
		owner: bool,
		color: string
		type: cell_type,
		units: [
			{
				id: string,
				type: unit_type,
				move: movement_type
			}
		],
		queue: [
			{
				type: unit_type,
				quantity: int
			}
		]
	}
]*/
}