import dtu.library.app.Book;
import io.cucumber.java.en.Given;

public class CreateProjectSteps {
	
	@Given("there is a book with id {string}, author {string}, and signature {string}")
	public void thereIsABookWithTitleAuthorAndSignature(String title, String author, String signature) throws Exception {
		book = new Book(title,author,signature);
	}
	@Given("the user is an admin")
	public void theUserIsAnAdmin() {
		assertTrue(SoftwareAS.model.containsBookWithSignature(book.getSignature()));
	}
}
