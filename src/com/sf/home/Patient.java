package com.sf.home;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Patient {
	
	private WebDriver driver = null;
	private Browser browser = null;
	
	private final String FIRST_NAME_ID = "form_fname";
	
	public Patient(Browser browser){
		this.browser = browser;
		this.driver = browser.getDriver();
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param dob provide date of birth in yyyy-mm-dd format
	 * @param sex
	 */
	public boolean createNew(String firstName, String lastName, String dob, String sex){
		boolean isSuccess = true;
		driver.switchTo().defaultContent();
		WebElement leftNav = driver.findElement(By.name(GlobalConstants.LEFT_NAV_NAME));
		driver.switchTo().frame(leftNav);
		driver.findElement(By.linkText("New/Search")).click();
		driver.switchTo().defaultContent();
		WebElement topFrame = driver.findElement(By.name("RTop"));
		driver.switchTo().frame(topFrame);
		WaitHandler.waitForElementPresence(driver, By.id(FIRST_NAME_ID));
		driver.findElement(By.id(FIRST_NAME_ID)).sendKeys(firstName);
		driver.findElement(By.id("form_lname")).sendKeys(lastName);
		driver.findElement(By.id("form_DOB")).sendKeys(dob);
		WebElement sexElmt = driver.findElement(By.id("form_sex"));
		Select mySelect = new Select(sexElmt);
		mySelect.selectByVisibleText(sex);
		driver.findElement(By.name("create")).click();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> handlesI = handles.iterator();
		String handle1 = handlesI.next();
		String handle2 = handlesI.next();
		driver.switchTo().window(handle2);
		driver.findElement(By.cssSelector("input[value='Confirm Create New Patient']")).click();
		driver.switchTo().window(handle1);
		topFrame = driver.findElement(By.name("RTop"));
		driver.switchTo().frame(topFrame);
		String lastNameUpper = lastName.substring(0,1).toUpperCase()+lastName.substring(1);
		String firstNameUpper = firstName.substring(0,1).toUpperCase()+firstName.substring(1);
		try{
			driver.findElement(By.xpath("//span[text()='"+lastNameUpper+", "+firstNameUpper+"']"));
		}catch(NoSuchElementException e){
			isSuccess = false;
		}
		return isSuccess;
	}

	/*public boolean createNew(String firstName, String lastName, String dob,
			String sex) {
		boolean isSuccess = true;
		driver.switchTo().defaultContent();
		WebElement leftNav = driver.findElement(By.name(GlobalConstants.LEFT_NAV_NAME));
		driver.switchTo().frame(leftNav);
		driver.findElement(By.linkText("New/Search")).click();
		driver.switchTo().defaultContent();
		WebElement topFrame = driver.findElement(By.name("RTop"));
		driver.switchTo().frame(topFrame);
		WaitHandler.waitForElementPresence(driver, By.id(FIRST_NAME_ID));
		driver.findElement(By.id(FIRST_NAME_ID)).sendKeys(firstName);
		driver.findElement(By.id("form_lname")).sendKeys(lastName);
		driver.findElement(By.id("form_DOB")).sendKeys(dob);
		WebElement sexElem = driver.findElement(By.id("form_sex"));
		Select mySelect = new Select(sexElem);
		mySelect.selectByVisibleText(sex);
		driver.findElement(By.name("create")).click();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> handlesI = handles.iterator();
		String handle1 = handlesI.next();
		String handle2 = handlesI.next();
		driver.switchTo().window(handle2);
		driver.findElement(
				By.cssSelector("input[value = 'Confirm Create New Patient']"))
				.click();
		driver.switchTo().window(handle1);
		driver.switchTo().frame(topFrame);
		String lastNameUpper = lastName.substring(0, 1).toUpperCase()
				+ lastName.substring(1);
		String firstNameUpper = firstName.substring(0, 1).toLowerCase()
				+ firstName.substring(1);
		try {
			driver.findElement(By.xpath("//span[text()= '" + firstNameUpper
					+ "," + lastNameUpper + "']"));
		} catch (NoSuchElementException e) {
			isSuccess = false;

		}
		return isSuccess;

	}*/
	public WebElement searchPatient(String name){
		clickOnPatients();
		WebElement searchBox = driver.findElement(By.xpath("(//input[@class='search_init'])[1]"));
		searchBox.sendKeys(name);
		WaitHandler.sleep(3);
		return driver.findElement(By.id("pt_table"));
	}
	
	private void clickOnPatients(){
		driver.switchTo().defaultContent();
		WebElement leftNav = driver.findElement(By.name(GlobalConstants.LEFT_NAV_NAME));
		driver.switchTo().frame(leftNav);
		driver.findElement(By.linkText("Patients")).click();
		driver.switchTo().defaultContent();
		WebElement topFrame = driver.findElement(By.name("RTop"));
		driver.switchTo().frame(topFrame);
	}
	
	public void choosePatient(String patient){
		clickOnPatients();
		driver.findElement(By.xpath("//td[text()='"+patient+"']")).click();
	}
}


	
	

	/*public WebElement searchPatient(String name) {
	    driver.switchTo().defaultContent();
		WebElement leftNav = driver.findElement(By.name(LEFT_NAV_NAME));
		driver.switchTo().frame(leftNav);
		driver.findElement(By.xpath("//a[text()='Patients']")).click();
		driver.switchTo().defaultContent();
		WebElement topFrame = driver.findElement(By.name("RTop"));
		driver.switchTo().frame(topFrame);
		WebElement searchBox = driver.findElement(By
				.xpath("(//input[@class='search_init'])[1]"));
		searchBox.sendKeys(name);
		WaitHandler.sleep(3);
		return driver.findElement(By.id("pt_table"));
	}
	}*/


