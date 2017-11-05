package com.mauriciotogneri.momowars.model

import com.mauriciotogneri.momowars.document.DocumentGame
import com.mauriciotogneri.momowars.document.DocumentPlayer
import kotlin.js.Json
import kotlin.js.json

class ModelGame(private val documentGame: DocumentGame, private val documentsPlayer: List<DocumentPlayer>)
{
    fun toJson(): Json
    {
        return json(
                Pair("id", documentGame.id),
                Pair("status", documentGame.status),
                Pair("map", documentGame.map),
                Pair("resources", 0), // TODO
                Pair("players", documentsPlayer.map { ModelPlayer(it).toJson() }),
                Pair("cells", "[]") // TODO
        )
    }

    /*
cells: [
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