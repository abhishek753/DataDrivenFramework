package com.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;



public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void loginAsBankManager() {
		
//		log.debug("Inside Login Test");
		driver.findElement(By.xpath(OR.getProperty("bmlbtn"))).click();
		
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("AddCustBtn"))),"Login not Successful");
		//log.debug("Login Successfully Executed");
	}

}
