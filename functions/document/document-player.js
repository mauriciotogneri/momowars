function DocumentPlayer(player, account)
{
	const data = player.data()

	this.json = function(accountId)
	{
		return {
			nickname: account.data().nickname,
			color: data.color,
			status: data.status
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

module.exports = DocumentPlayer