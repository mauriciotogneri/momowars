function ApiGames(api, database)
{
	this.getGame = function(request, response)
	{
		const sessionToken = request.get(api.SESSION_TOKEN)
		const gameId = request.param('gameId')
	
		database.accounts.bySessionToken(sessionToken)
		.then(accountDoc =>
		{
			const gameRef = accountDoc.gameRef(gameId)

			if (gameRef)
			{
				database.games.byRef(gameRef)
				.then(gameDoc =>
				{
					database.games.players(gameRef)
					.then(playerDocs =>
					{
						response
							.status(200)
							.json(gameDoc.json(playerDocs))
					})
				})
			}
			else
			{
				response.status(403).send()
			}
		})
		.catch(error =>
		{
			response.status(401).send()
		})
	}
}

module.exports = ApiGames