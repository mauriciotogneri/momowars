function ApiGames(database)
{
	this.getGame = function(request, response)
	{
		const sessionToken = request.get('Session-Token')
		const gameId = request.param('gameId')
	
		database.accounts.bySessionToken(sessionToken,
		accountDoc =>
		{
			if (gameId)
			{
				const gameRef = accountDoc.data().games.find(ref => ref.id == gameId)
	
				if (gameRef)
				{
					gameRef.get()
					.then(snapshot =>
					{
						response
							.status(200)
							.json(snapshot.data())	
					})
					.catch(error =>
					{
						response.status(404).send()
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
		},
		error =>
		{
			response.status(401).send()
		})
	}
}

module.exports = ApiGames