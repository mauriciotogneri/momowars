function Api(database)
{
	this.login = function(request, response, shajs)
	{
		const sessionId = new shajs.sha512().update(new Date().getTime()).digest('hex')
		
		return response
					.status(200)
					.set('Session-Token', sessionId)
					.json({foo:"bar"})
	}

	this.getGames = function(request, response)
	{
		const sessionToken = request.get('Session-Token')
		
		return database.users.bySessionToken(sessionToken,
		user =>
		{
			response
				.status(200)
				.json(user.games.map(ref => ref.id))
		},
		error =>
		{
			response.status(401).send()
		})
	}

	this.getGame = function(request, response)
	{
		const sessionToken = request.get('Session-Token')
		const gameId = request.param('gameId')
	
		return database.users.bySessionToken(sessionToken,
		user =>
		{
			if (gameId)
			{
				var gameRef = null
	
				user.games.forEach(ref =>
				{
					if (ref.id == gameId)
					{
						gameRef = ref
					}
				})
	
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

module.exports = Api