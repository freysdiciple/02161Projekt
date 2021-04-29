package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;


@RunWith(Cucumber.class)
@CucumberOptions(features = "features",
		plugin = { "summary"},
		monochrome=true,
		snippets = SnippetType.CAMELCASE,
		glue = { "StepDefinitions"})
public class AcceptanceTest {

}
