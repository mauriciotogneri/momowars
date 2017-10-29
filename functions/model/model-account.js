function ModeAccount(doc)
{
	this.json = function()
	{
		return {
			email:    doc.email,
			nickname: doc.nickname,
			games:    doc.games.map(game => game.ref.id)
		}
	}
}

module.exports = ModeAccount