function ApiSession(api, database, model)
{
	const shajs = require('sha.js')

	this.login = function(request, response)
	{
		const email    = request.body.email
		const password = request.body.password

		if (email && password)
		{
			database.account.byEmail(email)
			.then(accountDoc =>
			{
				if (accountDoc.hasPassword(hash(shajs, password)))
				{
					const seed = Math.random().toString(36).substring(2) + new Date().getTime().toString(36)
					const sessionId = hash(shajs, seed)

					accountDoc.update({session: sessionId})
					.then(result =>
					{
						response
							.status(200)
							.set(api.SESSION_TOKEN, sessionId)
							.send()
					})
				}
				else
				{
					response.status(401).send()
				}
			})
			.catch(error =>
			{
				response.status(404).send()
			})
		}
		else
		{
			response.status(400).send()
		}
	}

	function hash(shajs, input)
	{
		return new shajs.sha512().update(input).digest('hex')
	}
}

module.exports = ApiSession