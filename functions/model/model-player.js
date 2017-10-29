function ModelPlayer(doc)
{
	this.json = function()
	{
		return {
			nickname: doc.nickname,
			color:    doc.color,
			status:   doc.status
		}
	}
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

module.exports = ModelPlayer