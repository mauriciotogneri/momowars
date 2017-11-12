package com.mauriciotogneri.momowars.test;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.model.accounts.Account;

import org.junit.Assert;

import java.util.UUID;

import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class AccountSteps extends BaseSteps
{
    @When("^I create a new account with missing data$")
    public void createANewAccountWithMissingData() throws Exception
    {
        ApiResult result = createAccountEndPoint.execute(null, null, null);
        checkHttpStatus(400, result);
    }

    @When("^I create a new account with an existing email$")
    public void createANewAccountWithAnExistingEmail() throws Exception
    {
        String email = String.format("%s@email.com", System.currentTimeMillis());
        String password = UUID.randomUUID().toString();
        String nickname = "Nick";

        ApiResult result = createAccountEndPoint.execute(email, password, nickname);
        checkHttpStatus(200, result);

        Account account = json(result, Account.class);
        Assert.assertEquals(email, account.email);
        Assert.assertEquals(nickname, account.nickname);
        Assert.assertEquals(new String[0], account.games);
    }

    @When("^I create a new account with valid data$")
    public void createANewAccountWithValidData() throws Exception
    {
        ApiResult result = createAccountEndPoint.execute("mauricio.togneri@gmail.com", "xxx", "Nick");
        checkHttpStatus(409, result);
    }

    // ========================================================================================== \\

    @When("^I get the account with an invalid session$")
    public void getAccountWithAnInvalidSession() throws Exception
    {
        ApiResult result = getAccountEndPoint.execute("xxx");
        checkHttpStatus(401, result);
    }

    @When("^I get the account with a valid session$")
    public void getAccountWithAValidSession() throws Exception
    {
        ApiResult result = getAccountEndPoint.execute(SESSION_TOKEN);
        checkHttpStatus(200, result);

        ACCOUNT = json(result, Account.class);
        Assert.assertEquals("mauricio.togneri@gmail.com", ACCOUNT.email);
        Assert.assertEquals("Momo", ACCOUNT.nickname);
    }
}