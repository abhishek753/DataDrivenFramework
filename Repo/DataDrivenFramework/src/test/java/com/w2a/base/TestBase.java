package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	
	/*
	 * WebDriver - done
	 * Properties - done
	 * Logs - log4j jar , .log , log4j.properties,Logger
	 * Extent Reports
	 * DB
	 * Excel
	 * Mail
	 * ReportNG
	 * Jenkins
	 * 
	 */
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Properties prop = new Properties();
	
	//public static Logger log = Logger.getLogger("devpinoyLogger");
	
	@BeforeSuite
	public void setUp() {
		
		if(driver==null) {
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
			//	log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				//log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				//log.debug("Chrome Launched !!!");
			}
			else if(config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				
			}
			driver.get(config.getProperty("testsuiteurl"));
			//log.debug("NAvigated to : "+config.getProperty("testsuiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		}
//		try {
//			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\w2a\\utilities\\log4j.properties");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		prop.setProperty("log4j.rootLogger", "WARN");
//		PropertyConfigurator.configure(prop);
	}
	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e) {
			
			return false;
		}
	}
	
	@AfterSuite
	public void tearDown() {
		
		if(driver!=null) {
			driver.quit();
		}
		
		//log.debug("Test Execution Completed !!!");
	}

}
