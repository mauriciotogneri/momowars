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
}

module.exports = DatabaseGame