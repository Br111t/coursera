package web_testing_app;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import web_testing_app.WebTesting;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.*;

import java.util.concurrent.TimeUnit;
import java.lang.Thread;
import java.time.Duration;



/* READ THIS FIRST
 * 
 * General instructions are given above each of the 4 test classes. Make sure to use the correct "By"
 * function for selection. You will either submit the line where you added your code fragment or the entire section
 * of code that you write for later problems on this assignment marked by CODE FRAGMENT or CODE.
 * 
 */

public class Assignment {
	private static Duration shortTimeout = Duration.ofSeconds(10);
	private static Duration longTimeout =  Duration.ofSeconds(25);
	
	@BeforeClass
	public static void setUp() {
		WebTesting.initSystemProperties();
	}
	
	@AfterClass
	public static void tearDownAll() {
		if (WebTesting.getDriver() != null) {
			WebTesting.quitDriver();
		}
	}

	@After
	public void tearDown() {
		if (WebTesting.getDriver() != null) {
			WebTesting.quitDriver();
		}
	}
	
	

	
	
	/* Speed Test Explicit Instructions
	 * 
	 * Use "By.xpath" to select elements in these code fragments.
	 * "By.id" and "By.name" should be used where possible.
	 * 
	 */
	
	@Test
	public void testGoogleSpeedTestExplicitly() {
		/* Use one */
		//FirefoxDriver driver = (FirefoxDriver) WebTesting.launchDriver("https://www.google.com", "firefox");
		ChromeDriver driver = (ChromeDriver) WebTesting.launchDriver("https://www.google.com", "chrome");
		
		if (driver == null) {
			fail("Driver not created.");
		}
		
		/* You can change these timeout values to what you see fit for your machine */
		WebDriverWait shortWait = new WebDriverWait(driver, shortTimeout);
		WebDriverWait longWait = new WebDriverWait(driver, longTimeout);
		
		
		
		/* FIRST TEST */
		
		// #1: TODO (ensure that the search bar is visible before accessing it)
		// HINT: the search bar has attribute name="q"
		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		} catch (TimeoutException ex) {
			fail("Search bar not found.");
		}
		
		
		
		
		// #2: TODO (enter "internet speed test" into the search bar then press the "Return" key)
		driver.findElement(By.name("q")).sendKeys("internet speed test" + Keys.RETURN);
		
		
		

		// #3: TODO (ensure that the "RUN SPEED TEST" button is clickable)
		// HINT: the button has attribute id="knowledge-verticals-internetspeedtest__test_button"
		try {
			shortWait.until(By.id("knowledge-verticals-internetspeedtest__test_button"));
		} catch (TimeoutException ex) {
			fail("\"RUN SPEED TEST\" button not found.");
		}
		
		
		
		
		// #4: TODO (click the "RUN SPEED TEST" button by its "id" attribute)
		driver.findElement(By.id("knowledge-verticals-internetspeedtest__test_button")).click();

		

		
		// #5: TODO (ensure that the "CANCEL" button is clickable directly after this)
		// HINT: the button is a tag <g-raised-button> with attribute jsaction="dArJMd"
		try {
			shortWait.until(By.tagName("dArJMd"));
		} catch (TimeoutException ex) {
			fail("\"CANCEL\" button not found.");
		}
		
		
		

		// #6: TODO (wait for the test to finish)
		// HINT: The "CANCEL" button should not be clickable. Is shortWait long enough?
		try {
			longWait.until(By.tagName("dArJMd"));
		} catch (TimeoutException ex) {
			fail("Speed test did not finish.");
		}
		
		
		
		
		// #7: TODO ("RETRY" signifies a failure)
		// HINT: the "RETRY" button is a tag <g-raised-button> with attribute jsaction="i0JLwd"
		try {
			shortWait.until(By.tagName("i0JLwd"));
			fail("Speed test failed to run.");
		} catch (TimeoutException ex) {
			
		}
		
		
		
		
		// #8: TODO ("TEST AGAIN" signifies a success)
		// HINT: the "TEST AGAIN" button is a tag <g-raised-button> with attribute jsaction="iyDKIb"
		try {
			shortWait.until(By.tagName("iyDKIb"));
		} catch (TimeoutException ex) {
			fail("Speed test failed to run.");
		}
	}
	
	
	
	
	
	/* Speed Test Implicit Instructions
	 * 
	 * Use "By.cssSelector" to select elements in these code fragments.
	 * "By.id" and "By.name" should be used where possible.
	 * 
	 */
	
	@Test
	public void testGoogleSpeedTestImplicitly() throws InterruptedException {
		/* Use one */
//		FirefoxDriver driver = (FirefoxDriver) WebTesting.launchDriver("https://www.google.com", "firefox");
		ChromeDriver driver = (ChromeDriver) WebTesting.launchDriver("https://www.google.com", "chrome");
		
		if (driver == null) {
			fail("Driver not created.");
		}
		
		/* You can change these timeout values to what you see fit for your machine */
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		long shortSleep = 6000; // 6 seconds
		long longSleep = 60000; // 60 seconds
		
		
		
		
		// #9: TODO (enter "internet speed test" into the search bar then press the "Return" key)
		// HINT: the search bar has attribute name="q"
		try {
			driver.findElement(By.name("q")).sendKeys("internet speed test" + Keys.RETURN);
		} catch (NoSuchElementException ex) {
			fail("Search bar not found.");
		}
		
		
		
		
		// #10: TODO (click the "RUN SPEED TEST" button by its "id" attribute)
		// HINT: the button has attribute id="knowledge-verticals-internetspeedtest__test_button"
		try {
			driver.findElement(By.id("knowledge-verticals-internetspeedtest__test_button")).click();
		} catch (NoSuchElementException ex) {
			fail("\"RUN SPEED TEST\" button not found.");
		}

		
		
		
		// #11: TODO (ensure that the "CANCEL" is displayed directly after this)
		// HINT: WebElement has a useful method though we are required to use
		//       sleep(long millis) as the buttons never leave the page.
		Thread.sleep(shortSleep);
		
		WebElement cancelButton = null;
		
		try {
			cancelButton = driver.findElement(By.tagName("dArJMd"));
		} catch (NoSuchElementException ex) {
			fail("\"CANCEL\" button not found.");
		}
		
		if (!cancelButton.isDisplayed()) {
			fail("\"CANCEL\" button not found.");
		}
		
		
		
		
		// #12: TODO ("RETRY" signifies a failure)
		// HINT: the "RETRY" button is a tag <g-raised-button> with attribute jsaction="i0JLwd"
		Thread.sleep(longSleep);

		WebElement retryButton = null;
		
		try {
			retryButton = driver.findElement(By.tagName("i0JLwd"));
		} catch (NoSuchElementException ex) {
			fail("\"RETRY\" button not found.");
		}
		
		if (retryButton.isDisplayed()) {
			fail("Speed test did not finish.");
		}
		
		
		
		
		// #13: TODO ("TEST AGAIN" signifies a success)
		// HINT: the "TEST AGAIN" button is a tag <g-raised-button> with attribute jsaction="iyDKIb"
		WebElement testAgainButton = null;
		
		try {
			testAgainButton = driver.findElement(By.tagName("iyDKIb"));
		} catch (NoSuchElementException ex) {
			fail("\"TEST AGAIN\" button not found.");
		}
		
		if (!testAgainButton.isDisplayed()) {
			fail("Speed test did not finish.");
		}
	}
	
	
	
	
	
	/* Calculator Explicit Instructions
	 * 
	 * Use "By.xpath" to select elements in these code fragments.
	 * "By.id" and "By.name" should be used where possible.
	 * 
	 */
	
	@Test
	public void testCalculatorExplicitly() {
		/* Use one */
//		FirefoxDriver driver = (FirefoxDriver) WebTesting.launchDriver("https://www.google.com", "firefox");
		ChromeDriver driver = (ChromeDriver) WebTesting.launchDriver("https://www.google.com", "chrome");
		
		if (driver == null) {
			fail("Driver not created.");
		}
		
		/* You can change these timeout values to what you see fit for your machine */
		WebDriverWait shortWait = new WebDriverWait(driver, shortTimeout);
		WebDriverWait longWait = new WebDriverWait(driver, longTimeout);
		
		
		
		
		// #14: TODO (ensure that the search bar is visible before accessing it)
		// HINT: the search bar has attribute name="q"
		try {
			shortWait.until(By.name("q"));
		} catch (TimeoutException ex) {
			fail("Search bar not found.");
		}
				
				
				
				
		// #15: TODO (enter "calculator" into the search bar then press the "Return" key)
		// HINT: you don't need to check for its existence again
		driver.findElement(By.name("q")).sendKeys("calculator" + Keys.RETURN);
		
		
		
		// #16: TODO (ensure that the Calculator is visibile)
		// HINT: a relevant div has class="tyYmIf"
		try {
			shortWait.until(By.className("tyYmIf"));
		} catch (TimeoutException ex) {
			fail("Calculator not found.");
		}
		
		
		
		
		WebElement calculatorText = driver.findElement(By.id("cwos"));
		
		// ###: TODO (enter 1 and check whether the value was input into the text field)
		
		// Think about what could go wrong with this? //
		
		driver.findElement(By.id("cwos")).click();
		
		String text = calculatorText.getAttribute("innerHTML");
		
		if (text == null || !text.equals("1")) {
			fail("\"1\" was not input.");
		}
		
		
		
		
		// #17: TODO (enter 1 and check whether the value was input into the text field)
		// HINT: the "1" button is a div with jsname="N10B9"
		driver.findElement(By.tagName("N10B9")).click();
				
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "1"));
		} catch (TimeoutException ex) {
			fail("\"1\" was not input.");
		}
		
		
		
		
		// #18: TODO (enter "+" and check whether the value was input into the text field)
		// HINT: the "+" button is a div with jsname="XSr6wc"
		driver.findElement(By.tagName("XSr6wc")).click();
						
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "1 +"));
		} catch (TimeoutException ex) {
			fail("\"+\" was not input.");
		}
		
		
		
		
		// #19: TODO (enter "-" and check whether the value was input correctly into the text field)
		// HINT: the "-" button is a div with jsname="pPHzQc"
		driver.findElement(By.tagName("pPHzQc")).click();
								
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "1 -"));
		} catch (TimeoutException ex) {
			fail("\"-\" was not input.");
		}
		
		
		
		
		// #20: TODO (enter "2" and check whether the value was input into the text field)
		// HINT: the "2" button is a div with jsname="lVjWed"
		driver.findElement(By.tagName("lVjWed")).click();
										
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "1 - 2"));
		} catch (TimeoutException ex) {
			fail("\"2\" was not input.");
		}
		
		
		
		
		// #21: TODO (press "=" and check whether the value was solved correctly)
		// HINT: the "=" button is a div with jsname="Pt8tGc"
		driver.findElement(By.tagName("Pt8tGc")).click();
												
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "-1"));
		} catch (TimeoutException ex) {
			fail("\"-1\" was not the solution.");
		}
		
		
		
		
		// #22: TODO (press "AC" button and check whether the value was cleared)
		// HINT: the clear button is a div with jsname="SLn8gc"
		driver.findElement(By.tagName("SLn8gc")).click();
														
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		
		
		/*
		 *  Continue solving in the same format as above while clearing after solution is
		 *  found. It's suggested to copy-and-paste to ensure correct format.
		 */
		
		// #23: TODO (Solve "87 + 52" with correct usage of WebDriverWait)
		/*CODE*/
		// (enter "8" and check whether the value was input into the text field)
		driver.findElement(By.tagName("T7PMFe")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "8"));
		} catch (TimeoutException ex) {
			fail("\8"\" was not input.");
		}
		
		// (enter "7" and check whether the value was input into the text field)
		driver.findElement(By.tagName("rk7bOd")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "87"));
		} catch (TimeoutException ex) {
			fail("\7"\" was not input.");
		}
		
		// (enter "+" and check whether the value was input into the text field)
		driver.findElement(By.tagName("XSr6wc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "87 +"));
		} catch (TimeoutException ex) {
			fail("\+"\" was not input.");
		}
		
		// (enter "5" and check whether the value was input into the text field)
		driver.findElement(By.tagName("Ax5wH")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "87 + 5"));
		} catch (TimeoutException ex) {
			fail("\5"\" was not input.");
		}
		
		
		// (enter "2" and check whether the value was input into the text field)
		driver.findElement(By.tagName("lVjWed")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "87 + 52"));
		} catch (TimeoutException ex) {
			fail("\2"\" was not input.");
		}
		
		// (press "=" and check whether the value was solved correctly)
		driver.findElement(By.tagName("Pt8tGc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "139"));
		} catch (TimeoutException ex) {
			fail("\139"\" was not the solution.");
		}
		
		// (press "AC" button and check whether the value was cleared)
		driver.findElement(By.tagName("SLn8gc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		
		// #24: TODO (Solve "63 × 21" with correct usage of WebDriverWait)
		/*CODE*/
		// (enter "6" and check whether the value was input into the text field)
		driver.findElement(By.tagName("abcgof")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "6"));
		} catch (TimeoutException ex) {
			fail("\6"\" was not input.");
		}
		
		// (enter "3" and check whether the value was input into the text field)
		driver.findElement(By.tagName("KN1kY")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "63"));
		} catch (TimeoutException ex) {
			fail("\3"\" was not input.");
		}
		
		// (enter "×" and check whether the value was input into the text field)
		driver.findElement(By.tagName("YovRWb")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "63 ×"));
		} catch (TimeoutException ex) {
			fail("\×"\" was not input.");
		}
		
		// (enter "2" and check whether the value was input into the text field)
		driver.findElement(By.tagName("lVjWed")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "63 × 2"));
		} catch (TimeoutException ex) {
			fail("\2"\" was not input.");
		}
		
		
		// (enter "1" and check whether the value was input into the text field)
		driver.findElement(By.tagName("N10B9")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "63 × 21"));
		} catch (TimeoutException ex) {
			fail("\1"\" was not input.");
		}
		
		// (press "=" and check whether the value was solved correctly)
		driver.findElement(By.tagName("Pt8tGc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "1323"));
		} catch (TimeoutException ex) {
			fail("\1323"\" was not the solution.");
		}
		
		// (press "AC" button and check whether the value was cleared)
		driver.findElement(By.tagName("SLn8gc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		
		
		// #25: TODO (Solve "45 ÷ 9" with correct usage of WebDriverWait)
		/*CODE*/
		// (enter "4" and check whether the value was input into the text field)
		driver.findElement(By.tagName("xAP7E")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "4"));
		} catch (TimeoutException ex) {
			fail("\4"\" was not input.");
		}
		
		// (enter "5" and check whether the value was input into the text field)
		driver.findElement(By.tagName("Ax5wH")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "45"));
		} catch (TimeoutException ex) {
			fail("\5"\" was not input.");
		}
		
		// (enter "÷" and check whether the value was input into the text field)
		driver.findElement(By.tagName("WxTTNd")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "45 ÷"));
		} catch (TimeoutException ex) {
			fail("\÷"\" was not input.");
		}
		
		// (enter "9" and check whether the value was input into the text field)
		driver.findElement(By.tagName("XoxYJ")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "45 × 9"));
		} catch (TimeoutException ex) {
			fail("\9"\" was not input.");
		}

		
		// (press "=" and check whether the value was solved correctly)
		driver.findElement(By.tagName("Pt8tGc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "5"));
		} catch (TimeoutException ex) {
			fail("\5"\" was not the solution.");
		}
		
		// (press "AC" button and check whether the value was cleared)
		driver.findElement(By.tagName("SLn8gc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		
		
		// #26: TODO (Solve "72 ÷ 10" with correct usage of WebDriverWait)
		/*CODE*/
		// (enter "7" and check whether the value was input into the text field)
		driver.findElement(By.tagName("rk7bOd")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "7"));
		} catch (TimeoutException ex) {
			fail("\7"\" was not input.");
		}
		
		// (enter "2" and check whether the value was input into the text field)
		driver.findElement(By.tagName("lVjWed")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "72"));
		} catch (TimeoutException ex) {
			fail("\2"\" was not input.");
		}
		
		// (enter "÷" and check whether the value was input into the text field)
		driver.findElement(By.tagName("WxTTNd")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "72 ÷"));
		} catch (TimeoutException ex) {
			fail("\÷"\" was not input.");
		}
		
		// (enter "1" and check whether the value was input into the text field)
		driver.findElement(By.tagName("N10B9")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "72 × 1"));
		} catch (TimeoutException ex) {
			fail("\1"\" was not input.");
		}
		
		// (enter "0" and check whether the value was input into the text field)
		driver.findElement(By.tagName("bkEvMb")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "72 × 10"));
		} catch (TimeoutException ex) {
			fail("\0"\" was not input.");
		}

		
		// (press "=" and check whether the value was solved correctly)
		driver.findElement(By.tagName("Pt8tGc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "7.2"));
		} catch (TimeoutException ex) {
			fail("\7.2"\" was not the solution.");
		}
		
		// (press "AC" button and check whether the value was cleared)
		driver.findElement(By.tagName("SLn8gc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		
		
		// #27: TODO (Solve "log(58 × 6 ÷ 2 - 74)" with correct usage of WebDriverWait)
		// HINT: you don't need the last parentheses
		/*CODE*/
		// (enter "log" and check whether the value was input into the text field)
		driver.findElement(By.tagName("DfiOAc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log()"));
		} catch (TimeoutException ex) {
			fail("\log"\" was not input.");
		}
		
		// (enter "5" and check whether the value was input into the text field)
		driver.findElement(By.tagName("Ax5wH")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(5)"));
		} catch (TimeoutException ex) {
			fail("\5"\" was not input.");
		}
		
		// (enter "8" and check whether the value was input into the text field)
		driver.findElement(By.tagName("T7PMFe")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58)"));
		} catch (TimeoutException ex) {
			fail("\8"\" was not input.");
		}
		
		// (enter "×" and check whether the value was input into the text field)
		driver.findElement(By.tagName("YovRWb")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 ×)"));
		} catch (TimeoutException ex) {
			fail("\×"\" was not input.");
		}
		
		// (enter "6" and check whether the value was input into the text field)
		driver.findElement(By.tagName("abcgof")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6)"));
		} catch (TimeoutException ex) {
			fail("\6"\" was not input.");
		}
		
		// (enter "÷" and check whether the value was input into the text field)
		driver.findElement(By.tagName("WxTTNd")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6 ÷)"));
		} catch (TimeoutException ex) {
			fail("\÷"\" was not input.");
		}
		
		// (enter "2" and check whether the value was input into the text field)
		driver.findElement(By.tagName("lVjWed")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6 ÷ 2)"));
		} catch (TimeoutException ex) {
			fail("\2"\" was not input.");
		
		// (enter "-" and check whether the value was input into the text field)
		driver.findElement(By.tagName("pPHzQc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6 ÷ 2 -)"));
		} catch (TimeoutException ex) {
			fail("\-"\" was not input.")
		}
		
		// (enter "7" and check whether the value was input into the text field)
		driver.findElement(By.tagName("rk7bOd")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6 ÷ 2 - 7)"));
		} catch (TimeoutException ex) {
			fail("\7"\" was not input.");
		}
		
		// (enter "4" and check whether the value was input into the text field)
		driver.findElement(By.tagName("xAP7E")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6 ÷ 2 - 74)"));
		} catch (TimeoutException ex) {
			fail("\4"\" was not input.");
		}

		
		// (press "=" and check whether the value was solved correctly)
		driver.findElement(By.tagName("Pt8tGc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "2.22271647115"));
		} catch (TimeoutException ex) {
			fail("\2.22271647115"\" was not the solution.");
		}
		
		// (press "AC" button and check whether the value was cleared)
		driver.findElement(By.tagName("SLn8gc")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
	}
	
	
	
	
	
	/* Calculator Implicit Instructions
	 * 
	 * Use "By.cssSelector" to select elements in these code fragments.
	 * "By.id" and "By.name" should be used where possible.
	 * 
	 */
	
	@Test
	public void testCalculatorImplicitly() throws InterruptedException {
		/* Use one */
//		FirefoxDriver driver = (FirefoxDriver) WebTesting.launchDriver("https://www.google.com", "firefox");
//		ChromeDriver driver = (ChromeDriver) WebTesting.launchDriver("https://www.google.com", "chrome");
		
		if (driver == null) {
			fail("Driver not created.");
		}
		
		/* You can change this timeout value to what you see fit for your machine */
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
		
		// #28: TODO (enter "calculator" into the search bar then press the "Return" key)
		// HINT: the search bar has attribute name="q"
		try {
			driver.findElement(/*CODE FRAGMENT*/).sendKeys("calculator" + Keys.RETURN);
		} catch (NoSuchElementException ex) {
			fail("Search bar not found.");
		}
		
		
		
		// #29: TODO (ensure that the Calculator is visibile)
		// HINT: a relevant div has class="tyYmIf"
		try {
			driver.findElement(/*CODE FRAGMENT*/);
		} catch (NoSuchElementException ex) {
			fail("Calculator not found.");
		}
		
		
		
		
		// #30: TODO (enter 1 and check whether the value was input into the text field)
		// HINT: the "1" button is a div with jsname="N10B9"
		driver.findElement(/*CODE FRAGMENT*/).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'1\']"));
		} catch (NoSuchElementException ex) {
			fail("\"1\" was not input.");
		}
		
		
		
		
		// #31: TODO (enter "+" and check whether the value was input into the text field)
		// HINT: the "+" button is a div with jsname="XSr6wc"
		driver.findElement(/*CODE FRAGMENT*/).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'1 + \']"));
		} catch (NoSuchElementException ex) {
			fail("\"+\" was not input.");
		}
		
		
		
		
		// #32: TODO (enter "-" and check whether the value was input correctly into the text field)
		// HINT: the "-" button is a div with jsname="pPHzQc"
		driver.findElement(/*CODE FRAGMENT*/).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'1 - \']"));
		} catch (NoSuchElementException ex) {
			fail("\"-\" was not input.");
		}
		
		
		
		
		// #33: TODO (enter "2" and check whether the value was input into the text field)
		// HINT: the "2" button is a div with jsname="lVjWed"
		driver.findElement(/*CODE FRAGMENT*/).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'1 - 2\']"));
		} catch (NoSuchElementException ex) {
			fail("\"2\" was not input.");
		}
		
		
		
		
		// #34: TODO (press "=" and check whether the value was solved correctly)
		// HINT: the "=" button is a div with jsname="Pt8tGc"
		driver.findElement(/*CODE FRAGMENT*/).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'-1\']"));
		} catch (NoSuchElementException ex) {
			fail("\"-1\" was not the solution.");
		}
		
		
		
		
		// #35: TODO (press "AC" button and check whether the value was cleared)
		// HINT: the "AC" button is a div with jsname="SLn8gc"
		driver.findElement(/*CODE FRAGMENT*/).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'0\']"));
		} catch (NoSuchElementException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		
		
		/*
		 *  Continue solving in the same format as above while clearing after solution is
		 *  found. It's suggested to copy-and-paste to ensure correct format.
		 */

		// #36: TODO (Solve "87 + 52" with correct usage of ImplicitlyWait)
		/*CODE*/
		// (enter "8" and check whether the value was input into the text field)
		
		
		
		
		// #37: TODO (Solve "63 × 21" with correct usage of ImplicitlyWait)
		/*CODE*/
		
		
		
		// #38: TODO (Solve "45 ÷ 9" with correct usage of ImplicitlyWait)
		/*CODE*/
		
		
		
		// #39: TODO (Solve "72 ÷ 10" with correct usage of ImplicitlyWait)
		/*CODE*/
		
		
		
		// #40: TODO (Solve "log(58 × 6 ÷ 2 - 74)" with correct usage of ImplicitlyWait)
		// HINT: you don't need the last parentheses
		/*CODE*/
	}
}