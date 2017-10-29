function DatabaseGame(database, models)
{
	this.byRef = function(ref)
	{
		return new Promise((resolve, reject) => 
		{
			ref.get().then(doc =>
			{
				resolve(new models.gameDoc(doc))
			})
		})
	}
}

module.exports = DatabaseGame