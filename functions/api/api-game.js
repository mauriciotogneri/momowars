function ApiGame(api, database)
{
	this.getGame = function(request, response)
	{
		const sessionToken = request.get(api.SESSION_TOKEN)
		const gameId = request.param('gameId')
	
		database.account.bySessionToken(sessionToken)
		.then(accountDoc =>
		{
			const gameRef = accountDoc.gameRef(gameId)

			if (gameRef)
			{
				database.game.byRef(gameRef)
				.then(gameDoc =>
				{
					database.player.byGameRef(gameRef)
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

module.exports = ApiGame