function DatabaseAccounts(database, models)
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
					resolve(new models.accountDoc(snapshot.docs[0]))
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
		return database.collection('accounts')
	}
}

module.exports = DatabaseAccounts