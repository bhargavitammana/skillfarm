package com.sf.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Fees {
	private Browser browser = null;
	private WebDriver driver = null;

	private final String CHOOSE_CRITERIA = "choose_this_page_criteria";

	public Fees(Browser browser) {
		this.browser = browser;
		driver = browser.getDriver();

	}

	public void doBilling(String criteria) {
		driver.findElement(By.linkText("Billing")).click();
		driver.switchTo().defaultContent();
		WebElement topFrame = driver.findElement(By
				.name(GlobalConstants.RTOP_NAME));
		driver.switchTo().frame(topFrame);
		WaitHandler.waitForElementPresence(driver, By.id(CHOOSE_CRITERIA));
		WebElement selectBox = driver.findElement(By.id(CHOOSE_CRITERIA));
		Select selectElmt = new Select(selectBox);
		selectElmt.selectByVisibleText(criteria);
		System.out.println();

	}
/**
 * This method is used to set the date of billing. If u choose date of billing as criteria, u shud call this method.
 * @param billingType
 */
	public void setDateOfBilling(String billingType) {
		WebElement dateMaster = driver.findElement(By
				.id("date_master_criteria_claims_process_time"));
		Select dateMasterSel = new Select(dateMaster);
		dateMasterSel.selectByVisibleText(billingType);
	}
	
	public void setClaimType(String claimType){
		if("All".equals(claimType)){
			driver.findElement(By.id("radio_claims_target0")).click();
		}else if("eClaims".equals(claimType)){
			driver.findElement(By.id("radio_claims_target1")).click();
		}else if("Paper".equals(claimType)){
			driver.findElement(By.id("radio_claims_target2")).click();
			
		}
		
	}
}
