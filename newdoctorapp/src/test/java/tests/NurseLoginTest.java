package tests;
import java.util.Map;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import Pages.BaseClassDoctor;
import Pages.BaseClassNurse;
import Pages.NurseLoginPage;

public class NurseLoginTest extends BaseClassNurse {
	NurseLoginPage loginPage;
	ExtentTest test;
	
	@BeforeMethod
	public void initPage() {
		loginPage = new NurseLoginPage(BaseClassNurse.driver);
		test = extent.createTest("Valid Login Test");
	}
	
	@Test(groups = {"login"})
	
	public void testValidLogin() {
		String email= "Nurse58412@iorbitteam813038.testinator.email";
		String pass = "460147581";
		//driver.executeScript("mobile: setOrientation", Map.of("orientation", "LANDSCAPE"));
		try {
			loginPage.login(email, pass);
			System.out.println("Login successful for user: " + email);
			
			
		}catch (Exception e) {
			System.out.println("Login test failed: " + e.getMessage());
			Assert.fail("Exception occured: "+ e.getMessage());
		}
	}
}
