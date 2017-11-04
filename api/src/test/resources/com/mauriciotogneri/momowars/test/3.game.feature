Feature: Game API

    Scenario: game operations
        When I get a game with an invalid session
        When I get a game with a valid session and invalid id
        When I get a game with a valid session and valid id