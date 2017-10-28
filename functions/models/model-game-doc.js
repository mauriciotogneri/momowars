function GameDoc(doc)
{
	const data = doc.data()

	this.json = function()
	{
		return {
			foo: 123
		}
	}
}

module.exports = GameDoc