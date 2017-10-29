function ApiAccount(api, database, model)
{
	this.getAccount = function(request, response)
	{
		const sessionToken = request.get(api.SESSION_TOKEN)
		
		database.account.bySessionToken(sessionToken)
		.then(doc =>
		{
			response
				.status(200)
				.json(new model.account(doc).json())
		})
		.catch(error =>
		{
			response.status(401).send()
		})
	}
}

module.exports = ApiAccount