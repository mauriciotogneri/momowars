function PlayerDoc(player, account)
{
	const data = player.data()

	this.json = function()
	{
		return {
			nickname: account.data().nickname,
			color: data.color,
			status: data.status //turn_status
		}
	}
}

module.exports = PlayerDoc