function DatabaseUsers(database)
{
	this.bySessionToken = function(token, success, failure)
	{
		return root().where('session', '==', token).get()
		.then(snapshot =>
		{
			singleUser(snapshot, success, failure)
		})
		.catch(error =>
		{
			failure(error)
		})
	}

	this.byEmail = function(email, success, failure)
	{
		return root().where('email', '==', email).get()
		.then(snapshot =>
		{
			singleUser(snapshot, success, failure)
		})
		.catch(error =>
		{
			failure(error)
		})
	}

	function singleUser(snapshot, success, failure)
	{
		if (snapshot.size == 1)
		{
			var user = null

			snapshot.forEach(doc =>
			{
				user = doc
			})

			if (user)
			{
				success(user)
			}
			else
			{
				failure()
			}
		}
		else
		{
			failure()
		}
	}

	function root()
	{
		return database.root().collection('users')
	}
}

module.exports = DatabaseUsers