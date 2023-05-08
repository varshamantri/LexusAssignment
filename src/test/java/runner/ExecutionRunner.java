package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@assignment",
        features = {"src/test/resources/features"},
        glue = {"steps"},
        plugin = {}
)

public class ExecutionRunner extends AbstractTestNGCucumberTests {

}
