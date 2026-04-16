package Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class InstructionsPage extends BaseClassDoctor{
	
	AppiumDriver driver;
	WebDriverWait wait;
	
	public InstructionsPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
	}
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"pallavi gowda, M , Age: 25\"]")
	public WebElement patientbox;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"Instructions\"]")
	public WebElement instructionsoption;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"+ Add New Instruction\"]")
	public WebElement addnewinstructions;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"Custom Instructions\"]")
	public WebElement custominstructions;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text=\"Type your custom instruction here...\"]")
	public WebElement typeinstruction;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"Save Instruction\"]")
	public WebElement saveinstruction;
	
	public void addinstructions() throws InterruptedException {
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(patientbox)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(instructionsoption)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(addnewinstructions)).click();
		wait.until(ExpectedConditions.elementToBeClickable(custominstructions)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(typeinstruction)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(typeinstruction)).sendKeys("Change the High heart rate alarm limit to 220 bpm");
		
		try { ((HidesKeyboard) driver).hideKeyboard(); } catch (Exception e) {}
		
		wait.until(ExpectedConditions.elementToBeClickable(saveinstruction)).click();
		
		Thread.sleep(8000);
		
	}

}
