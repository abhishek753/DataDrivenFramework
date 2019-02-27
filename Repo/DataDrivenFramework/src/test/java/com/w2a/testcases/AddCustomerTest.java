package com.w2a.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

import freemarker.core.ReturnInstruction.Return;

public class AddCustomerTest extends TestBase {
	
	@Test(dataProvider="getData")
	public void addCustomer(String firstName, String lastName, String postCode) {
		
	}
	
//	@DataProvider
//	public Object[][] getData(){
		
	

}
