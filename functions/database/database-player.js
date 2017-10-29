function DatabasePlayer(database, document)
{
	this.byGameRef = function(gameRef)
	{
		return new Promise((resolve, reject) => 
		{
			gameRef.collection('players').get()
			.then(docList =>
			{
				const accountRefs = docList.docs.map(doc => doc.data().account)

				database.account.listByRef(accountRefs)
				.then(accountDocs =>
				{
					const playerDocs = []

					for (var i = 0; i < docList.docs.length; i++)
					{
						playerDocs.push(new document.player(docList.docs[i], accountDocs[i]))
					}

					resolve(playerDocs)
				})
			})
		})
	}
}

module.exports = DatabasePlayer