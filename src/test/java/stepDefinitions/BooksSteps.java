package stepDefinitions;

import apiEngine.IRestResponse;
import apiEngine.model.Book;
import apiEngine.model.requests.AddBooksRequest;
import apiEngine.model.requests.ISBN;
import apiEngine.model.requests.RemoveBookRequest;
import apiEngine.model.responses.Books;
import apiEngine.model.responses.UserAccount;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utility.Log;

public class BooksSteps extends BaseStep{

    public BooksSteps(TestContext testContext){
        super(testContext);
    }

    private Response response;
    private IRestResponse<UserAccount> userAccountResponse;
    private Book book;

    @Given("^A list of books are available$")
    public void listOfBooksAreAvailable() {
        Log.step("A list of books are available");
        IRestResponse<Books> booksResponse = getEndPoints().getBooks();
        book = booksResponse.getBody().books.get(0);
        getScenarioContext().setContext(Context.BOOK, book);
    }

    @When("^I add a book to my reading list$")
    public void addBookInList() {
        Log.step("I add a book to my reading list");
        Book book = (Book) getScenarioContext().getContext(Context.BOOK);
        String userId = (String) getScenarioContext().getContext(Context.USER_ID);

        ISBN isbn = new ISBN(book.isbn);
        AddBooksRequest addBooksRequest = new AddBooksRequest(userId, isbn);

        IRestResponse<UserAccount> userAccountResponse = getEndPoints().addBook(addBooksRequest);
        getScenarioContext().setContext(Context.USER_ACCOUNT_RESPONSE, userAccountResponse);
    }

    @When("^I remove a book from my reading list$")
    public void removeBookFromList() {
        Log.step("I remove a book from my reading list");
        Book book = (Book) getScenarioContext().getContext(Context.BOOK);
        String userId = (String) getScenarioContext().getContext(Context.USER_ID);

        RemoveBookRequest removeBookRequest = new RemoveBookRequest(userId, book.isbn);

        Response response = getEndPoints().removeBook(removeBookRequest);
        getScenarioContext().setContext(Context.BOOK_REMOVE_RESPONSE, response);
    }
}
