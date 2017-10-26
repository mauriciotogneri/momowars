function Api(database)
{
	this.login = function(request, response, shajs)
	{
		const email    = request.body.email
		const password = request.body.password

		if (email && password)
		{
			database.users.byEmail(email,
			userDoc =>
			{
				if (userDoc.data().password == hash(shajs, password))
				{
					const seed = Math.random().toString(36).substring(2) + new Date().getTime().toString(36)
					const sessionId = hash(shajs, seed)

					userDoc.ref.update({session: sessionId})
					.then(result =>
					{
						response
							.status(200)
							.set('Session-Token', sessionId)
							.send()
					})
					.catch(error =>
					{
						response.status(500).send()
					})
				}
				else
				{
					response.status(401).send()
				}
			},
			error =>
			{
				response.status(404).send()
			})
		}
		else
		{
			response.status(400).send()
		}
	}

	this.getGames = function(request, response)
	{
		const sessionToken = request.get('Session-Token')
		
		database.users.bySessionToken(sessionToken,
		userDoc =>
		{
			response
				.status(200)
				.json(userDoc.data().games.map(ref => ref.id))
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
	
		database.users.bySessionToken(sessionToken,
		userDoc =>
		{
			if (gameId)
			{
				const gameRef = userDoc.data().games.find(ref => ref.id == gameId)
	
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

	function hash(shajs, input)
	{
		return new shajs.sha512().update(input).digest('hex')
	}
}

module.exports = Api