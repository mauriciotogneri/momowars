function ApiAccounts(database)
{
	this.getAccount = function(request, response)
	{
		const sessionToken = request.get('Session-Token')
		
		database.accounts.bySessionToken(sessionToken)
		.then(accountDoc =>
		{
			response
				.status(200)
				.json(accountDoc.json())
		})
		.catch(error =>
		{
			response.status(401).send()
		})
	}
}

module.exports = ApiAccounts