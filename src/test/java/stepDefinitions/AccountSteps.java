package stepDefinitions;

import apiEngine.model.requests.AuthorizationRequest;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Given;
import utility.Log;

public class AccountSteps extends BaseStep{

    public AccountSteps(TestContext testContext){
        super(testContext);
    }

    @Given("^I am an authorized user$")
    public void iAmAnAuthorizedUser() {
        Log.step("I am an authorized user");
        AuthorizationRequest authRequest = new AuthorizationRequest("TOOLSQA-Test", "Test@@123");
        getEndPoints().authenticateUser(authRequest);
        getEndPoints().removeBooks((String)getScenarioContext().getContext(Context.USER_ID));
    }
}
