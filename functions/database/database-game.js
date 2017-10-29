function DatabaseGame(database, document)
{
	this.byRef = function(ref)
	{
		return new Promise((resolve, reject) => 
		{
			ref.get().then(doc =>
			{
				resolve(new document.game(doc))
			})
		})
	}
}

module.exports = DatabaseGame