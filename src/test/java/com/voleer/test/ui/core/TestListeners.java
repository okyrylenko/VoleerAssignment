package com.voleer.test.ui.core;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IClass;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class TestListeners implements ITestListener {


    public void onTestFailure(ITestResult result) {
        File screenshotFile = ((TakesScreenshot) ThreadStorage.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            String path = "src/test/java/com/valeer/test/ui/tests/screenshots/" + result.getMethod().getMethodName() +"/" +  result.getTestContext().getAttribute("browser") + "_error.png";
            FileUtils.copyFile(screenshotFile , new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
