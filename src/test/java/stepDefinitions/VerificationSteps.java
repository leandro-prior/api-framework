package stepDefinitions;

import apiEngine.IRestResponse;
import apiEngine.model.Book;
import apiEngine.model.responses.UserAccount;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import utility.Log;

public class VerificationSteps extends BaseStep {

    public VerificationSteps(TestContext testContext) {
        super(testContext);
    }

    @Then("^The book is added$")
    public void bookIsAdded() {
        Log.step("The book is added");
        Book book = (Book) getScenarioContext().getContext(Context.BOOK);
        String userId = (String) getScenarioContext().getContext(Context.USER_ID);
        IRestResponse<UserAccount> userAccountResponse = (IRestResponse<UserAccount>) getScenarioContext().getContext(Context.USER_ACCOUNT_RESPONSE);

        Assert.assertTrue(userAccountResponse.isSuccessful());
        Assert.assertEquals(userAccountResponse.getStatusCode(), 201);

        Assert.assertEquals(userAccountResponse.getBody().books.get(0).isbn, book.isbn);
    }

    @Then("^The book is removed$")
    public void bookIsRemoved() {
        Log.step("The book is removed");
        String userId = (String) getScenarioContext().getContext(Context.USER_ID);
        Response response = (Response) getScenarioContext().getContext(Context.BOOK_REMOVE_RESPONSE);

        Assert.assertEquals(response.getStatusCode(), 204);

        IRestResponse<UserAccount> userAccountResponse = getEndPoints().getUserAccount(userId);
        Assert.assertEquals(userAccountResponse.getStatusCode(), 200);

        Assert.assertEquals(userAccountResponse.getBody().books.size(), 0);
    }
}
