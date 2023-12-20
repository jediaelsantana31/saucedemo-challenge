package com.saucedemo.actionwords;

import com.saucedemo.utilities.base.SeleniumHelper;
import com.saucedemo.utilities.settings.ProjectSettings;

public class Utils extends SeleniumHelper {
    public void openApplication() {
        goTo(ProjectSettings.URL);
    }
}
