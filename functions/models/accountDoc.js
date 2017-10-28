function AccountDoc(doc)
{
	const data = doc.data()

	this.email = data.email
	this.password = data.password

	this.update = function(value)
	{
		return doc.ref.update(value)
	}
}

module.exports = AccountDoc