package cucumber;

import apiEngine.EndPoints;
import dataProvider.ConfigReader;
import enums.Context;

public class TestContext {

    private EndPoints endPoints = new EndPoints(ConfigReader.getInstance().getBaseUrl());
    private ScenarioContext scenarioContext;

    public TestContext() {
        scenarioContext = new ScenarioContext();
        scenarioContext.setContext(Context.USER_ID, ConfigReader.getInstance().getUserID());
    }

    public EndPoints getEndPoints() {
        return endPoints;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
