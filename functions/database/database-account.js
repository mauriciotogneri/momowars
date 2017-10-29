function DatabaseAccount(database, document)
{
	/**
	 * @param {String} token
	 * @returns {Promise<DocumentAccount>}
	 */
	this.bySessionToken = function(token)
	{
		return getAccount(root().where('session', '==', token).get())
	}

	/**
	 * @param {String} email
	 * @returns {Promise<DocumentAccount>}
	 */
	this.byEmail = function(email)
	{
		return getAccount(root().where('email', '==', email).get())
	}

	/**
	 * @param {Array<DocumentReference[Account]>} accountRefs
	 * @returns {Promise<Array<DocumentSnapshot[Account]>>}
	 */
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
					resolve(new document.account(docList.docs[0]))
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

module.exports = DatabaseAccount