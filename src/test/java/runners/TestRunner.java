package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utility.Log;

import java.io.IOException;
import java.net.MalformedURLException;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/functionalTests",
        glue = {"stepDefinitions"},
        tags = "@smoke",
        plugin = {"pretty","json:target/site/cucumber-reports/jsonReports.json"},
        monochrome = true,
        publish = true,
        stepNotifications = true
)
public class TestRunner {

    @BeforeClass
    public static void launchServer() throws MalformedURLException {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);
        Log.startTestCase();
    }

    @AfterClass
    public static void killServer() throws IOException {
        Log.endTestCase("E-N-D");
    }
}
