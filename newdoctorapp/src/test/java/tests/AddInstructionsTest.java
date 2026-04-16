package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import Pages.BaseClassDoctor;
import Pages.DoctorLoginPage;
import Pages.InstructionsPage;

public class AddInstructionsTest extends BaseClassDoctor{
	
	DoctorLoginPage doctorlogin;
	ExtentTest test;
	InstructionsPage instructions;
	@BeforeMethod
	public void initPage() {
		instructions = new InstructionsPage(BaseClassDoctor.driver);
		test=extent.createTest("Valid login test");
		
	}
	
	@Test
	public void instructionstest() throws InterruptedException {
		instructions.addinstructions();
	}


}
