Feature: Game API

    Scenario: get game
        When I get a game with an invalid session
        When I get a game with a valid session and invalid id
        When I get a game with a valid session and valid id
        
    Scenario: get open games
        When I get the open games