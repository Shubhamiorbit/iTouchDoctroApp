package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import Pages.AdmissionPatient;
import Pages.BaseClassNurse;
import Pages.BaseClassNurse;
import Pages.NurseLoginPage;

public class AdmissionPatientTest extends BaseClassNurse{

	NurseLoginPage loginPage;
	ExtentTest test;
	AdmissionPatient admissionpatient;
	@BeforeMethod
	public void initPage() {
		admissionpatient = new AdmissionPatient(BaseClassNurse.driver);
		test = extent.createTest("Valid Login Test");
	}
	
	@Test
	public void admissionpatient() throws InterruptedException {
		Thread.sleep(8000);
		admissionpatient.monitoring();
	}
}
