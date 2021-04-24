import dtu.library.app.Book;
import io.cucumber.java.en.Given;

public class CreateProjectSteps {
	
	@Given("there is an admin with id {string} and database {DataBase}")
	public void thereIsAnAdminWithIDAndDataBase(String id, DataBase database) throws Exception {
		developer developer = new developer(id,database);
	}
	@Given("the user is an admin")
	public void theUserIsAnAdmin() {
		assertTrue(SoftwareAS.model.theUserIsAnAdmin(developer.isAdmin()));
	}
}
