package com.mauriciotogneri.momowars.test.games;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.model.games.Game;
import com.mauriciotogneri.momowars.test.BaseSteps;
import com.mauriciotogneri.momowars.test.session.CreateSessionSteps;

import org.junit.Assert;

import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class GetGameSteps extends BaseSteps
{
    @When("^I get a game with an invalid session$")
    public void getAGameWithAnInvalidSession() throws Exception
    {
        ApiResult result = getGameEndPoint.execute("xxx", "xxx");
        checkHttpStatus(401, result);
    }

    @When("^I get a game with invalid parameters$")
    public void getAGameWithInvalidParameters() throws Exception
    {
        ApiResult result = getGameEndPoint.execute("", "xxx");
        checkHttpStatus(400, result);
    }

    @When("^I get a game with a valid session and invalid id$")
    public void getAGameWithAValidSessionAndInvalidId() throws Exception
    {
        ApiResult result = getGameEndPoint.execute(CreateSessionSteps.SESSION_TOKEN, "xxx");
        checkHttpStatus(403, result);
    }

    @When("^I get a game with a valid session and valid id$")
    public void getAGameWithAValidSessionAndValidId() throws Exception
    {
        for (String gameId : ACCOUNT.games)
        {
            ApiResult result = getGameEndPoint.execute(CreateSessionSteps.SESSION_TOKEN, gameId);
            checkHttpStatus(200, result);

            Game game = json(result, Game.class);
            Assert.assertNotEquals(null, game);
        }
    }
}