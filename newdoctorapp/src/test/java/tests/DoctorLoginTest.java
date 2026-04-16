package tests;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import Pages.BaseClassDoctor;
import Pages.DoctorLoginPage;


public class DoctorLoginTest extends BaseClassDoctor {
	DoctorLoginPage doctorloginPage;
	ExtentTest test;
	
	@BeforeMethod
	public void initPage() {
		doctorloginPage = new DoctorLoginPage(BaseClassDoctor.driver);
		test = extent.createTest("Valid Login Test");
	}
	
	@Test(groups = {"login"})
	
	public void testValidLogin() {
		String email= "Dr58401@iorbitteam813038.testinator.email";
		String pass = "101608774";
		//driver.executeScript("mobile: setOrientation", Map.of("orientation", "LANDSCAPE"));
		try {
			doctorloginPage.login(email, pass);
			System.out.println("Login successful for user: " + email);
			
		}catch (Exception e) {
			System.out.println("Login test failed: " + e.getMessage());
			Assert.fail("Exception occured: "+ e.getMessage());
		}
	}
}

