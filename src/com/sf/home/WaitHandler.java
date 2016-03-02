package com.sf.home;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Wait;

import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHandler {

	public static void waitForElementPresence(WebDriver driver, By by) {

		Wait<WebDriver> wait = new WebDriverWait(driver, 300);

		wait.until(ExpectedConditions.presenceOfElementLocated(by));

	}

	public static void sleep(int seconds) {

		try {

			Thread.sleep(seconds * 1000);

		} catch (InterruptedException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
	}
}
