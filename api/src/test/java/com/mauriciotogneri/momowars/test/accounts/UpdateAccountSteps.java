package com.mauriciotogneri.momowars.test.accounts;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.model.accounts.Account;
import com.mauriciotogneri.momowars.test.BaseSteps;

import org.junit.Assert;

import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class UpdateAccountSteps extends BaseSteps
{
    @When("^I update the account with an invalid session$")
    public void updateAccountWithAnInvalidSession() throws Exception
    {
        ApiResult result = updateAccountEndPoint.execute("xxx", "password", "Momo");
        checkHttpStatus(401, result);
    }

    @When("^I update the account with invalid parameters$")
    public void updateAccountWithInvalidParameters() throws Exception
    {
        ApiResult result = updateAccountEndPoint.execute(SESSION_TOKEN, null, null);
        checkHttpStatus(400, result);
    }

    @When("^I update the account with valid parameters$")
    public void updateAccountWithValidParameters() throws Exception
    {
        ApiResult result = updateAccountEndPoint.execute(SESSION_TOKEN, "password", "Momo");
        checkHttpStatus(200, result);

        Account account = json(result, Account.class);
        Assert.assertNotEquals(null, account);
    }
}