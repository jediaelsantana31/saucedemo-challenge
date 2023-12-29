package com.saucedemo.utilities.base;

import com.saucedemo.utilities.settings.ScenarioContext;

public class BaseTest {
    protected ScenarioContext context;

    public BaseTest() {
        context = ScenarioContext.getInstance();
    }

    public void setVariable(String key, Object value) {
        context.setContextData(key, value);
    }

    public Object getVariable(String key) {
        return context.getContextData(key);
    }
}
