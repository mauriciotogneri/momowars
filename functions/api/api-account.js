function ApiAccount(api, database)
{
	this.getAccount = function(request, response)
	{
		const sessionToken = request.get(api.SESSION_TOKEN)
		
		database.account.bySessionToken(sessionToken)
		.then(doc =>
		{
			response
				.status(200)
				.json(doc.json())
		})
		.catch(error =>
		{
			response.status(401).send()
		})
	}
}

module.exports = ApiAccount