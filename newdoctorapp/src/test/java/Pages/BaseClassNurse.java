package Pages;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseClassNurse {
		public static AndroidDriver driver;
		public static ExtentReports extent;
		public static ExtentTest test;
		
		@BeforeTest
		public void setup() throws MalformedURLException {
			ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
			  
			extent = new ExtentReports();
			extent.attachReporter(spark);
			Pages.DeviceUtils deviceutils = new DeviceUtils();
			File file = new File("D:\\iTouchNurseApp\\newnurseapp\\Apps\\iTouch-NurseApp-release.apk");
			System.out.println(file.exists());
			
			File app = new File("D:\\iTouchNurseApp\\newnurseapp\\Apps\\iTouch-NurseApp-release.apk");
			UiAutomator2Options options = new UiAutomator2Options();
			
			String deviceName = System.getProperty("deviceName");
			String udid = System.getProperty("udid");
			String platformVersion = System.getProperty("platformVersion");
			
			if(udid == null) {
				udid = deviceutils.getConnectedDevice();
			}
			
			if(deviceName == null) {
				deviceName = udid;
			}
			
			options.setPlatformName("Android");
			options.setAutomationName("UiAutomator2");
			options.setDeviceName(deviceName);
			options.setUdid(udid);
			options.setPlatformVersion(platformVersion);
			options.setApp(app.getAbsolutePath());
			
			options.setAutoGrantPermissions(true);
	        options.setNoReset(false); 
	        options.setDisableWindowAnimation(true);
	        
	        options.setNewCommandTimeout(Duration.ofSeconds(600));
	        options.setAdbExecTimeout(Duration.ofSeconds(120));
	        options.setUiautomator2ServerInstallTimeout(Duration.ofSeconds(120));
	        options.setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(120));

	        options.setIgnoreHiddenApiPolicyError(true);
	        options.setSkipDeviceInitialization(false);
	        options.setSkipServerInstallation(false);

	        options.setCapability("waitForIdleTimeout", 10000);

	        driver = new AndroidDriver(
	        	    new URL("http://127.0.0.1:4723"), 
	        	    options
	        	);
	        
		}
		public static String captureScreen(String tname) {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			TakesScreenshot takeScreenshot= (TakesScreenshot) driver;
			File sourcefile= takeScreenshot.getScreenshotAs(OutputType.FILE);
			String targetFilePath= System.getProperty("user.dir")+"\\Screenshots\\"+tname+ timeStamp+ ".png";
			File targetfile= new File(targetFilePath);
			sourcefile.renameTo(targetfile);

			return targetFilePath; 
		}

}
