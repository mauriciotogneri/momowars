function ModelGame(accountDoc, gameDoc, playerDocs, cellDocs, unitDocs)
{
	this.json = function()
	{
		return {
			id:        gameDoc.id,
			status:    gameDoc.status,
			map:       gameDoc.map,
			resources: 0, // TODO
			players:   [], //playerDocs.map(player => player.json()), // TODO
			cells:     [] // TODO
		}
	}
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

module.exports = ModelGame