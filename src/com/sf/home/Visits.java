package com.sf.home;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Visits {
	private WebDriver driver = null;
	private Browser browser = null;
	
	public Visits(Browser browser){
		this.browser = browser;
		this.driver = browser.getDriver();
	}
	public void createVisit(String patient, String reason, String category, String dob){
		browser.getPatientPortal().choosePatient(patient);
		driver.switchTo().defaultContent();
		WebElement leftNav = driver.findElement(By.name(GlobalConstants.LEFT_NAV_NAME));
		driver.switchTo().frame(leftNav);
		driver.findElement(By.linkText("Create Visit")).click();
		driver.switchTo().defaultContent();
		WebElement bottom = driver.findElement(By.name(GlobalConstants.RBOT_NAME));
		driver.switchTo().frame(bottom);
		WaitHandler.waitForElementPresence(driver, By.name("reason"));
		driver.findElement(By.name("reason")).sendKeys(reason);
		WebElement visitCategoryElm = driver.findElement(By.id("pc_catid"));
		Select mySelect = new Select(visitCategoryElm);
		mySelect.selectByVisibleText(category);
		driver.findElement(By.id("form_date")).sendKeys(dob);
    	driver.findElement(By.xpath("//span[text()='Save']")).click();

	}
}


