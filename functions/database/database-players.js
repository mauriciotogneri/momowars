function DatabasePlayers(database, models)
{
	this.byGameRef = function(gameRef)
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

module.exports = DatabasePlayers