function DatabaseGame(database, documents)
{
	this.byRef = function(ref)
	{
		return new Promise((resolve, reject) => 
		{
			ref.get().then(doc =>
			{
				resolve(new documents.game(doc))
			})
		})
	}
}

module.exports = DatabaseGame