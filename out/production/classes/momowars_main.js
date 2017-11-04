(function (_, Kotlin) {
  'use strict';
  var Unit = Kotlin.kotlin.Unit;
  function main$lambda(request, response) {
    return response.status(501).send();
  }
  function main$lambda_0(request, response) {
    return response.status(501).send();
  }
  function main$lambda_1(request, response) {
    return response.status(501).send();
  }
  function main$lambda_2(request, response) {
    return response.status(501).send();
  }
  function main$lambda_3(request, response) {
    return response.status(501).send();
  }
  function main$lambda_4(request, response) {
    return response.status(501).send();
  }
  function main$lambda_5(request, response) {
    return response.status(501).send();
  }
  function main$lambda_6(request, response) {
    return response.status(501).send();
  }
  function main$lambda_7(request, response) {
    return response.status(501).send();
  }
  function main$lambda_8(request, response) {
    return response.status(501).send();
  }
  function main$lambda_9(request, response) {
    return response.status(501).send();
  }
  function main(args) {
    var functions = require('firebase-functions');
    var admin = require('firebase-admin');
    admin.initializeApp(functions.config().firebase);
    var express = require('express');
    var app = express();
    var api = new Api();
    app.post('/v1/session', Kotlin.getCallableRef('login', function ($receiver, request, response) {
      return $receiver.login_phqgcc$(request, response), Unit;
    }.bind(null, api.session)));
    app.get('/v1/account', Kotlin.getCallableRef('getAccount', function ($receiver, request, response) {
      return $receiver.getAccount_phqgcc$(request, response), Unit;
    }.bind(null, api.account)));
    app.post('/v1/account', main$lambda);
    app.patch('/v1/account', main$lambda_0);
    app.get('/v1/games/:gameId', Kotlin.getCallableRef('getGame', function ($receiver, request, response) {
      return $receiver.getGame_phqgcc$(request, response), Unit;
    }.bind(null, api.game)));
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
    this.session = new ApiSession();
    this.account = new ApiAccount();
    this.game = new ApiGame();
  }
  Api.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'Api',
    interfaces: []
  };
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
  ApiSession.prototype.login_phqgcc$ = function (request, response) {
    response.status(200).send('LOGIN');
  };
  ApiSession.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'ApiSession',
    interfaces: []
  };
  _.main_kand9s$ = main;
  var package$com = _.com || (_.com = {});
  var package$mauriciotogneri = package$com.mauriciotogneri || (package$com.mauriciotogneri = {});
  var package$momowars = package$mauriciotogneri.momowars || (package$mauriciotogneri.momowars = {});
  var package$api = package$momowars.api || (package$momowars.api = {});
  package$api.Api = Api;
  package$api.ApiAccount = ApiAccount;
  package$api.ApiGame = ApiGame;
  package$api.ApiSession = ApiSession;
  main([]);
  Kotlin.defineModule('momowars_main', _);
  return _;
}(module.exports, require('kotlin')));
