function DatabaseUsers(database)
{
	this.bySessionToken = function(token, success, error)
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
				error()
			}
		})
		.catch(error =>
		{
			error(error)
		})
	}

	function root()
	{
		return database.root().collection('users')
	}
}

module.exports = DatabaseUsers