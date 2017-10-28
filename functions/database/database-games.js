function DatabaseGame(database, models)
{
	this.byRef = function(ref)
	{
		return new Promise((resolve, reject) => 
		{
			ref.get().then(snapshot =>
			{
				resolve(new models.gameDoc(snapshot))
			})
		})
	}

	this.players = function(gameRef)
	{
		return new Promise((resolve, reject) => 
		{
			gameRef.collection('players').get().then(snapshot =>
			{
				const accountRefs = []

				snapshot.forEach(doc =>
				{
					accountRefs.push(doc.data().account.get())
				})

				Promise.all(accountRefs)
				.then(accountDocs =>
				{
					const playerDocs = []

					for (var i = 0; i < snapshot.docs.length; i++)
					{
						playerDocs.push(new models.playerDoc(snapshot.docs[i], accountDocs[i]))
					}

					resolve(playerDocs)
				})
			})
		})
	}
}

module.exports = DatabaseGame