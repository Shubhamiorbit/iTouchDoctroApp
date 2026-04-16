package Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdmissionPatient extends BaseClassNurse{

	AppiumDriver driver;
	WebDriverWait wait;
	
	public AdmissionPatient(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
	}
	
	@AndroidFindBy(xpath="//com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.GroupView[5]/com.horcrux.svg.RectView[1]")
	public WebElement bedselection;
	
	@AndroidFindBy(accessibility="Confirm")
	public WebElement confirmadmission;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"Start\"]")
	public WebElement startmonitoringbutton;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"android:id/button1\")")
	public WebElement monitoringstartedconfirmation;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"✕\")")
	public WebElement closedialogbox;
	
	@AndroidFindBy(uiAutomator="new UiSelector().description(\"Stop\")")
	public WebElement stopmonitoringbutton;
	
	public void monitoring() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(bedselection)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(confirmadmission)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(bedselection)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(startmonitoringbutton)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(monitoringstartedconfirmation)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(closedialogbox)).click();
		
		Thread.sleep(5000);
		
		wait.until(ExpectedConditions.elementToBeClickable(bedselection)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(stopmonitoringbutton)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(closedialogbox)).click();		
		
	}
	
	
	
	
}
