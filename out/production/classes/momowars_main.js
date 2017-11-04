(function (_, Kotlin) {
  'use strict';
  var Unit = Kotlin.kotlin.Unit;
  function main$lambda(f, response) {
    return response.status(501).send();
  }
  function main$lambda_0(f, response) {
    return response.status(501).send();
  }
  function main$lambda_1(f, response) {
    return response.status(501).send();
  }
  function main$lambda_2(f, response) {
    return response.status(501).send();
  }
  function main$lambda_3(f, response) {
    return response.status(501).send();
  }
  function main$lambda_4(f, response) {
    return response.status(501).send();
  }
  function main$lambda_5(f, response) {
    return response.status(501).send();
  }
  function main$lambda_6(f, response) {
    return response.status(501).send();
  }
  function main$lambda_7(f, response) {
    return response.status(501).send();
  }
  function main$lambda_8(f, response) {
    return response.status(501).send();
  }
  function main$lambda_9(f, response) {
    return response.status(501).send();
  }
  function main(args) {
    var functions = require('firebase-functions');
    var admin = require('firebase-admin');
    admin.initializeApp(functions.config().firebase);
    Database_getInstance().firestore = admin.firestore();
    var express = require('express');
    var app = express();
    app.post('/v1/session', Kotlin.getCallableRef('login', function ($receiver, request, response) {
      return $receiver.login_phqgcc$(request, response), Unit;
    }.bind(null, Api_getInstance().session)));
    app.get('/v1/account', Kotlin.getCallableRef('getAccount', function ($receiver, request, response) {
      return $receiver.getAccount_phqgcc$(request, response), Unit;
    }.bind(null, Api_getInstance().account)));
    app.post('/v1/account', main$lambda);
    app.patch('/v1/account', main$lambda_0);
    app.get('/v1/games/:gameId', Kotlin.getCallableRef('getGame', function ($receiver, request, response) {
      return $receiver.getGame_phqgcc$(request, response), Unit;
    }.bind(null, Api_getInstance().game)));
    app.patch('/v1/games/:gameId', main$lambda_1);
    app.post('/v1/games', main$lambda_2);
    app.get('/v1/games', main$lambda_3);
    app.post('/v1/games/:gameId', main$lambda_4);
    app.delete('/v1/games/:gameId', main$lambda_5);
    app.patch('/v1/games/:gameId/cells/:cellId/units/:unitId/move', main$lambda_6);
    app.put('/v1/games/:gameId/cells/:cellId/units', main$lambda_7);
    app.get('/v1/maps', main$lambda_8);
    app.get('/v1/maps/:mapId', main$lambda_9);
    var apiExpress = express();
    apiExpress.use('/api', app);
    exports.api = functions.https.onRequest(apiExpress);
  }
  function Api() {
    Api_instance = this;
    this.session = new ApiSession();
    this.account = new ApiAccount();
    this.game = new ApiGame();
  }
  Api.$metadata$ = {
    kind: Kotlin.Kind.OBJECT,
    simpleName: 'Api',
    interfaces: []
  };
  var Api_instance = null;
  function Api_getInstance() {
    if (Api_instance === null) {
      new Api();
    }
    return Api_instance;
  }
  function ApiAccount() {
  }
  ApiAccount.prototype.getAccount_phqgcc$ = function (request, response) {
    response.status(200).send('GET ACCOUNT');
  };
  ApiAccount.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'ApiAccount',
    interfaces: []
  };
  function ApiGame() {
  }
  ApiGame.prototype.getGame_phqgcc$ = function (request, response) {
    response.status(200).send('GET GAME 2');
  };
  ApiGame.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'ApiGame',
    interfaces: []
  };
  function ApiSession() {
  }
  function ApiSession$login$lambda(closure$response) {
    return function (docList) {
      if (!docList.empty) {
        return closure$response.status(200).send(docList.docs[0].data().email);
      }
       else {
        return closure$response.status(404).send();
      }
    };
  }
  ApiSession.prototype.login_phqgcc$ = function (request, response) {
    var email = request.body.email;
    var password = request.body.password;
    if (email != null && password != null) {
      Database_getInstance().firestore.collection('accounts').where('email', '==', email).get().then(ApiSession$login$lambda(response));
    }
     else {
      response.status(400).send();
    }
  };
  ApiSession.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'ApiSession',
    interfaces: []
  };
  function Database() {
    Database_instance = this;
    this.firestore = this.firestore;
  }
  Database.$metadata$ = {
    kind: Kotlin.Kind.OBJECT,
    simpleName: 'Database',
    interfaces: []
  };
  var Database_instance = null;
  function Database_getInstance() {
    if (Database_instance === null) {
      new Database();
    }
    return Database_instance;
  }
  function DatabaseAccount() {
  }
  DatabaseAccount.prototype.bySessionToken_61zpoe$ = function (token) {
    return this.getAccount_0(this.root_0().where('session', '==', token).get());
  };
  DatabaseAccount.prototype.byEmail_61zpoe$ = function (email) {
    return this.getAccount_0(this.root_0().where('email', '==', email).get());
  };
  function DatabaseAccount$getAccount$lambda$lambda(closure$resolve, closure$reject) {
    return function (docList) {
      if (!docList.empty) {
        return closure$resolve(docList.docs[0]);
      }
       else {
        return closure$reject;
      }
    };
  }
  function DatabaseAccount$getAccount$lambda(closure$queryPromise) {
    return function (resolve, reject) {
      closure$queryPromise.then(DatabaseAccount$getAccount$lambda$lambda(resolve, reject));
      return Unit;
    };
  }
  DatabaseAccount.prototype.getAccount_0 = function (queryPromise) {
    return new Promise(DatabaseAccount$getAccount$lambda(queryPromise));
  };
  DatabaseAccount.prototype.root_0 = function () {
    return Database_getInstance().firestore.collection('accounts');
  };
  DatabaseAccount.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'DatabaseAccount',
    interfaces: []
  };
  _.main_kand9s$ = main;
  var package$com = _.com || (_.com = {});
  var package$mauriciotogneri = package$com.mauriciotogneri || (package$com.mauriciotogneri = {});
  var package$momowars = package$mauriciotogneri.momowars || (package$mauriciotogneri.momowars = {});
  var package$api = package$momowars.api || (package$momowars.api = {});
  Object.defineProperty(package$api, 'Api', {
    get: Api_getInstance
  });
  package$api.ApiAccount = ApiAccount;
  package$api.ApiGame = ApiGame;
  package$api.ApiSession = ApiSession;
  var package$database = package$momowars.database || (package$momowars.database = {});
  Object.defineProperty(package$database, 'Database', {
    get: Database_getInstance
  });
  package$database.DatabaseAccount = DatabaseAccount;
  main([]);
  Kotlin.defineModule('momowars_main', _);
  return _;
}(module.exports, require('kotlin')));
