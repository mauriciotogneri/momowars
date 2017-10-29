function DocumentAccount(doc)
{
	const data = doc.data()
	
	this.hasPassword = function(password)
	{
		return data.password == password
	}

	this.update = function(value)
	{
		return doc.ref.update(value)
	}

	this.gameRef = function(gameId)
	{
		return data.games.find(ref => ref.id == gameId)
	}

	this.json = function()
	{
		return {
			email: data.email,
			nickname: data.nickname,
			games: data.games.map(game => game.ref.id)
		}
	}
}

module.exports = DocumentAccount