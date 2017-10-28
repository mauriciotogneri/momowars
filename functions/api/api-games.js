function ApiGames(database)
{
	this.getGame = function(request, response)
	{
		const sessionToken = request.get('Session-Token')
		const gameId = request.param('gameId')
	
		database.accounts.bySessionToken(sessionToken)
		.then(accountDoc =>
		{
			if (gameId)
			{
				const gameRef = accountDoc.gameRef(gameId)
	
				if (gameRef)
				{
					database.games.byRef(gameRef)
					.then(gameDoc =>
					{
						response
							.status(200)
							.json(gameDoc.json())	
					})
					.catch(error =>
					{
						response.status(500).send()
					})
				}
				else
				{
					response.status(404).send()
				}
			}
			else
			{
				response.status(400).send()
			}
		})
		.catch(error =>
		{
			response.status(401).send()
		})
	}
}

module.exports = ApiGames