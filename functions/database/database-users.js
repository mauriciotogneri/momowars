function DatabaseUsers(database)
{
	this.bySessionToken = function(token, success, failure)
	{
		return root().where('session', '==', token).get()
		.then(snapshot =>
		{
			if (snapshot.size == 1)
			{
				var user = null

				snapshot.forEach(doc =>
				{
					user = doc.data()
				})

				success(user)
			}
			else
			{
				failure()
			}
		})
		.catch(error =>
		{
			failure(error)
		})
	}

	function root()
	{
		return database.root().collection('users')
	}
}

module.exports = DatabaseUsers