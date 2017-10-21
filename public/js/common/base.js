'use strict'

const app = angular.module('gameApp', [])

app.filter('gravatar', () =>
{
	return function(input)
	{
		return `http://www.gravatar.com/avatar/${md5(input)}?s=30`
	}
})

function controller(id)
{
	return angular.element($(`[ng-controller=${id}]`)).scope()
}

firebase.initializeApp({
	apiKey: "AIzaSyBAeU-RK3pVMeO-5kocs2fz8PQREHuOrIg",
    authDomain: "momo-wars.firebaseapp.com",
    databaseURL: "https://momo-wars.firebaseio.com",
    projectId: "momo-wars",
    storageBucket: "momo-wars.appspot.com",
    messagingSenderId: "315836553038"
})