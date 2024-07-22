package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/Features", plugin="json:target/jsonReports/cucumber-report.json", glue= {"StepDefination"})
public class TestRunner {
//, tags="@DeletePlaceTag"
}
