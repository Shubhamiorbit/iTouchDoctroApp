package Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import io.appium.java_client.AppiumDriver;


public class DoctorLoginPage extends BaseClassDoctor{
	
	AppiumDriver driver;
	WebDriverWait wait;
	
	public DoctorLoginPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
	}
	

	@AndroidFindBy(accessibility="E-mail Address")
	public WebElement loginwithemailaddressbutton;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Email Address\")")
	public WebElement emailaddressfield;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Password\")")
	public WebElement passwordfield;
	
	@AndroidFindBy(accessibility="Get OTP")
	public WebElement getotpbutton;
	
	public void login(String email, String pass) {
		wait.until(ExpectedConditions.visibilityOf(loginwithemailaddressbutton));
		loginwithemailaddressbutton.click();
		
		try {
			wait.until(ExpectedConditions.visibilityOf(emailaddressfield));
			emailaddressfield.sendKeys(email);
			
			wait.until(ExpectedConditions.visibilityOf(passwordfield));
			passwordfield.sendKeys(pass);
			
			wait.until(ExpectedConditions.elementToBeClickable(getotpbutton)).click();
		} catch(Exception e) {
			System.out.println("Login failed: " +e.getMessage());
			throw e;
		}
	}
	
	
	
	
	

}
