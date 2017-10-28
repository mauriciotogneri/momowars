function DatabaseAccounts(database)
{
	this.bySessionToken = function(token)
	{
		return getAccount(root().where('session', '==', token).get())
	}

	this.byEmail = function(email)
	{
		return getAccount(root().where('email', '==', email).get())
	}

	function getAccount(queryPromise)
	{
		return new Promise((resolve, reject) => 
		{
			queryPromise.then(snapshot =>
			{
				if (!snapshot.empty)
				{
					resolve(snapshot.docs[0])
				}
				else
				{
					reject()
				}
			})
		})
	}

	function root()
	{
		return database.root().collection('accounts')
	}
}

module.exports = DatabaseAccounts