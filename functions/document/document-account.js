function DocumentAccount(doc)
{
	const data = doc.data()
	
	this.email    = data.email
	this.nickname = data.nickname
	this.password = data.password
	this.games    = data.games

	this.hasPassword = function(password)
	{
		return (this.password == password)
	}

	this.update = function(value)
	{
		return doc.ref.update(value)
	}

	this.gameRef = function(gameId)
	{
		return this.games.find(ref => ref.id == gameId)
	}
}

module.exports = DocumentAccount