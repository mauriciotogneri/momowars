Feature: Game API

	Scenario: get open games
        When I get the open games with an invalid session
        When I get the open games with a valid session

    Scenario: get game
        When I get a game with an invalid session
        When I get a game with invalid parameters
        When I get a game with a valid session and invalid id
        When I get a game with a valid session and valid id
        
    Scenario: end turn
        When I end a turn with an invalid session
        When I end a turn with invalid parameters
        #When I end a turn on an invalid game
        #When I end a turn on a valid game