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

	this.listByRef = function(accountRefs)
	{
		return Promise.all(accountRefs.map(ref => ref.get()))
	}

	function getAccount(queryPromise)
	{
		return new Promise((resolve, reject) => 
		{
			queryPromise.then(docList =>
			{
				if (!docList.empty)
				{
					resolve(new models.accountDoc(docList.docs[0]))
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
		return database.root.collection('accounts')
	}
}

module.exports = DatabaseAccounts