package com.sf.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
	
private WebDriver driver;
	
	private final String LOGIN_FRAME_NAME = "Login";
	private final String TITLE_FRAME_NAME = "Title";
	private final String USER_FIELD_NAME = "authUser";
	private final String PWD_FIELD_NAME = "clearPass";
	private final String LOGIN_BTN_XPATH = "//input[@value='Login']";
	private final String LOGOUT_LINK_ID = "logout_link";
	
	private Patient patient = null;
	private Visits visit = null;
	private Fees fee = null;
	
	
	public void open(String browser){
		initializeDriver(browser);
		driver.get("http://dev.skillfarm.co/openemr");
		driver.switchTo().frame(LOGIN_FRAME_NAME);
		WaitHandler.waitForElementPresence(driver, By.name(USER_FIELD_NAME));
	}
	
	public Patient getPatientPortal(){
		if(patient==null){
			patient = new Patient(this);
		}
		return patient;
	}
	
	public Visits getVisitsPortal(){
		if(visit==null){
			visit = new Visits(this);
		}
		return visit;
	}
	
	public Fees getFeesPortal(){
		if(fee==null){
			fee = new Fees(this);
		}
		driver.switchTo().defaultContent();
		WebElement leftFrame = driver.findElement(By.name(GlobalConstants.LEFT_NAV_NAME));
		driver.switchTo().frame(leftFrame);
		driver.findElement(By.id("feeimg")).click();
		WaitHandler.waitForElementPresence(driver, By.linkText("Billing"));
		return fee;
	}

	public void login(String usr, String pwd){
		driver.findElement(By.name(USER_FIELD_NAME)).sendKeys(usr);
		driver.findElement(By.name(PWD_FIELD_NAME)).sendKeys(pwd);
		driver.findElement(By.xpath(LOGIN_BTN_XPATH)).click();
	}
	
	public void logout(){
		driver.switchTo().defaultContent();
		driver.switchTo().frame(TITLE_FRAME_NAME);
		driver.findElement(By.id(LOGOUT_LINK_ID)).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(LOGIN_FRAME_NAME);
		WaitHandler.waitForElementPresence(driver, By.name(USER_FIELD_NAME));
	}
	
	public void close(){
		driver.close();
		driver.quit();
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	private void initializeDriver(String browser) {
		if("chrome".equalsIgnoreCase(browser))
		{
			System.setProperty("webdriver.chrome.driver",
					"/Users/bhargavi/Desktop/jarfiles/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--test-type");
			options.addArguments("chrome.switches","--disable-extensions");
			driver = new ChromeDriver(options);
		}
		else
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
	} 
}
