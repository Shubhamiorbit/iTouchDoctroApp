package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Pages.BaseClassDoctor;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        new File("reports").mkdirs();

        String timeStamp =
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        ExtentSparkReporter spark =
                new ExtentSparkReporter("reports/Test-Report-" + timeStamp + ".html");

        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("Tech Admin Automation");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Env", "QA");

        System.out.println(">>> EXTENT STARTED");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        try {
            String screenshot =
                    BaseClassDoctor.captureScreen(result.getMethod().getMethodName());
            test.get().addScreenCaptureFromPath(screenshot);
        } catch (Exception e) {
            test.get().warning("Screenshot failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println(">>> EXTENT FINISHED");
    }
}
