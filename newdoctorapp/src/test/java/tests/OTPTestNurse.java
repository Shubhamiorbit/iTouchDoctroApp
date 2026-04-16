package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Pages.BaseClassNurse;
import Pages.OTPClassNurse;
import io.appium.java_client.HidesKeyboard;

public class OTPTestNurse extends BaseClassNurse {
	
	
	@BeforeTest
	public void initTestReport() {
		test = extent.createTest("OTP Test");
	}
	
	@Test
	
	public void enterOTPTest() throws Exception {
		String otp = OTPClassNurse.fetchLatestOTP();
		Assert.assertNotNull(otp, "OTP should not be null");
		test.log(Status.INFO, "Waiting for OTP input fields....");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> otpfields = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//android.widget.EditText")));
		
		Assert.assertTrue(otpfields.size()>=6, "OTP input fields count is insufficient");
		test.log(Status.PASS, "OTP input fields loaded successfully");
		
		for(int i=0; i<otp.length(); i++) {
			otpfields.get(i).sendKeys(String.valueOf(otp.charAt(i)));
		}
		
		System.out.println("OTP entered, locating fro submit buuton");
		try { ((HidesKeyboard) driver).hideKeyboard();
		} catch (Exception e) {}
			WebElement submitbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.ViewGroup[@content-desc=\"Verify OTP\"]")));
		
	submitbtn.click();
	System.out.println("OTP submitted successfully");
	}
}
