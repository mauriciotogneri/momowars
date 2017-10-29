function ApiGame(api, database, model)
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
						const cellDocs = null
						const unitDocs = null

						response
							.status(200)
							.json(new model.game(accountDoc, gameDoc, playerDocs, cellDocs, unitDocs).json())
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