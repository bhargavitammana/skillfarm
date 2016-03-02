package com.sf.test;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.sf.home.Browser;
import com.sf.home.Fees;
import com.sf.home.Patient;

public class FirstTest {

private Browser browser = null;
	
	@Before
	public void setUp(){
		browser = new Browser();
		browser.open("Chrome");
		browser.login("admin", "Admin123$");
	}

	@Test
	public void testCreateNewPatient(){
		Patient portal = browser.getPatientPortal();
		boolean isCreated = portal.createNew("Doctor1", "Pqr", "2015-04-25", "Male");
		Assert.assertTrue(isCreated);
	}
	
	@Test
	public void testSearchPatient(){
		String searchStr = "patient";
		Patient portal = browser.getPatientPortal();
		WebElement result = portal.searchPatient(searchStr);
		List<WebElement> results = result.findElements(By.className(" sorting_1"));
		Iterator<WebElement> resultsI = results.iterator();
		boolean success = true;
		while(resultsI.hasNext()){
			WebElement curr = resultsI.next();
			System.out.println(curr.getText());
			if(!curr.getText().toLowerCase().contains(searchStr)){
				success = false;
				break;
			}
		}
		Assert.assertTrue(success);
	}
	
	@Test
	public void testCreateVisit(){
		browser.getVisitsPortal().createVisit("ABC, Patient2", "Casual visit","Reserved","2015-04-25"); 
		System.out.println();
	}
	
	@Test
	public void doBilling() {
		Fees fees = browser.getFeesPortal();
		//fees.doBilling("Date of Billing");
		fees.doBilling("Claim Type");
		//fees.setDateOfBilling("Last Month");
		fees.setClaimType("Paper");
		System.out.println();
	}
	
	/*@After
	public void tearDown(){
		browser.logout();
		browser.close();
	}*/
}

	

