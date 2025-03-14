package com.devstack.automation.tests.login;

import com.devstack.automation.functions.commons.LIB_Common;
import com.devstack.automation.model.LoginTestData;
import com.devstack.automation.tests.BaseTest;
import com.devstack.automation.utils.ExcelHandler;
import com.devstack.automation.utils.PropertyHandler;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "commonDataProvider" , dataProviderClass = ExcelHandler.class)
    public void loginTest(LoginTestData loginTestData) {
        LIB_Common common = new LIB_Common(driver);
//        common.bc_login(PropertyHandler.getProperty("username"), PropertyHandler.getProperty("password"));
        common.bc_login(loginTestData.getUserName(), loginTestData.getPassword());

//comment added
        //comment 2 added
        //comment 3 added
        //comment 4 added
    }

}
