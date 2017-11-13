package com.mauriciotogneri.momowars.test.games;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.model.games.Game;
import com.mauriciotogneri.momowars.test.BaseSteps;
import com.mauriciotogneri.momowars.test.session.CreateSessionSteps;

import org.junit.Assert;

import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class GetOpenGamesSteps extends BaseSteps
{
    @When("^I get the open games with an invalid session$")
    public void getTheOpenGamesWithAnInvalidSession() throws Exception
    {
        ApiResult result = getOpenGamesEndPoint.execute("xxx");
        checkHttpStatus(401, result);
    }

    @When("^I get the open games with a valid session$")
    public void getTheOpenGamesWithAValidSession() throws Exception
    {
        ApiResult result = getOpenGamesEndPoint.execute(CreateSessionSteps.SESSION_TOKEN);
        checkHttpStatus(200, result);

        Game[] games = json(result, Game[].class);
        Assert.assertNotEquals(null, games);
    }
}