package com.mauriciotogneri.momowars.test;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.validation.BaseEndPoint;

import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class SessionSteps extends BaseSteps
{
    @When("^I login with no parameters$")
    public void loginWithNoParameters() throws Exception
    {
        ApiResult result = createSessionEndPoint.execute(null, null);
        checkHttpStatus(400, result);
    }

    @When("^I login with an invalid email$")
    public void loginWithAnInvalidEmail() throws Exception
    {
        ApiResult result = createSessionEndPoint.execute("name@email.com", "password");
        checkHttpStatus(404, result);
    }

    @When("^I login with an invalid password$")
    public void loginWithAnInvalidPassword() throws Exception
    {
        ApiResult result = createSessionEndPoint.execute("mauricio.togneri@gmail.com", "1234");
        checkHttpStatus(401, result);
    }

    @When("^I login with valid credentials$")
    public void loginWithValidCredentials() throws Exception
    {
        ApiResult result = createSessionEndPoint.execute("mauricio.togneri@gmail.com", "password");
        checkHttpStatus(200, result);
        SESSION_TOKEN = result.response().header(BaseEndPoint.HEADER_SESSION_TOKEN);
    }
}