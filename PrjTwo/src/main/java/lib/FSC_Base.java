package lib;


import java.io.IOException;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.util.*;



import lib.ConfigFileReader;


public class FSC_Base {
	
public static  WebDriver  driver;
public static ConfigFileReader configFileReader = new ConfigFileReader();
public static Properties config = new Properties();



public static WebDriverWait wait;
public static Actions a;
public static WebElement mainmenu;


@BeforeSuite
public void setUp()throws IOException
{
	String browserName = configFileReader.getBrowserName();
	
if (browserName.equals("IE"))
{
	//execute in IE
	File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Executables\\IEDriverServer.exe");
	System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
	DesiredCapabilities capabilities=DesiredCapabilities.internetExplorer();
	capabilities.setJavascriptEnabled(true);
	capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
	//System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\IEDriverServer.exe");
	driver=new InternetExplorerDriver(capabilities);
	
}
else if (browserName.equals("chrome"))
{
	//execute in Chrome
	File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Executables\\chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	driver = new ChromeDriver();
	//test.log(Status.INFO, "Initiated driver");
}


//maximizing the window
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
//Navigate to Home page
driver.get(configFileReader.getApplicationUrl());

}

public void navigateToHomepage() {
	
	String homepage = configFileReader.getApplicationUrl();
	driver.get(homepage);
	//test.log(Status.INFO, "Navigated to home page");
}

public static void Click(WebElement element1) {
	element1.click();
	String locator = element1.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
	
	//test.log(Status.INFO, "Clicking on field: " + locator);
}

public static void Type(WebElement element1, String value) {
	element1.sendKeys(value);
	//test.log(Status.INFO, "Entered value as " + value);
}


public static boolean isElementPresent(By by) {

	try {

		driver.findElement(by);
		return true;

	} catch (NoSuchElementException e) {

		return false;

	}

}

protected static int getRandomNumberInBetween(int lowerBound, int upperBound) {
    Random r = new Random();
    return (r.nextInt(upperBound) + lowerBound);
}


// This method would select the particular option from the drop down
public static void selectSpecifiedValueFromDropdown(WebElement element, String value)
{
	Select dropdown = new Select (element);
	dropdown.selectByValue(value);
}

//This method would select the particular text from the drop down
public static void selectSpecifiedTextFromDropdown(WebElement element, String text)
{
	Select dropdown = new Select (element);
	dropdown.selectByVisibleText(text);
}

//This method would select a random item from the drop down
public static void selectRandomItemFromDropdown(WebElement element)
{
	Select dropdown = new Select (element);
	List<WebElement> drlist = dropdown.getOptions();
	int maxIndex = drlist.size() - 1;
	
	int index = getRandomNumberInBetween(0, maxIndex);
	dropdown.selectByIndex(index);
}


public  void selectMenuLink(WebElement main, WebElement sub) throws InterruptedException
{
	  a= new Actions(driver);
	  a.moveToElement(main).click().build().perform();
	  sub.click();

}


	
public  void selectdate(String Date, WebElement Calendar, WebElement YearMonth, WebElement Prev) throws InterruptedException 
{
	String dat=Date;
	String date,month,year;
	String caldt,calyear;		
	/*
	 * Split the String into String Array
	 */
	String dateArray[]= dat.split("/");
	
	date=dateArray[0];
	month=dateArray[1];
	if (month.equals("01"))
		month = "January";
	if (month.equals("02"))
		month = "February";
	if (month.equals("03"))
			month = "March";
	if (month.equals("04"))
		month = "April";
	if (month.equals("05"))
		month = "May";
	if (month.equals("06"))
		month = "June";
	if (month.equals("07"))
		month = "July";
	if (month.equals("08"))
		month = "August";
	if (month.equals("09"))
		month = "September";
	if (month.equals("10"))
		month = "October";
	if (month.equals("11"))
		month = "November";
	if (month.equals("12"))
		month = "December";
	
	year=dateArray[2];

	WebElement cal;
	cal = Calendar;
	calyear = YearMonth.getText();
	String[] disyear = calyear.split(" ");
    calyear = disyear[1];
    String calmonth = disyear[0];


	/**
	 * Select the year
	 */
	while (!calyear.equals(year)) 
	{
		Prev.click();
		calyear = YearMonth.getText();
		disyear = calyear.split(" ");
	    calyear = disyear[1];
	    
	}

	
	/**
	 * Select the Month
	 */
	
	calmonth = YearMonth.getText();
	String[] dismonth = calmonth.split(" ");
	calmonth = dismonth[0];
	
	
	
	while (!calmonth.equalsIgnoreCase(month)) 
	{
			
		Prev.click();
		calmonth = YearMonth.getText();
		dismonth = calmonth.split(" ");
		calmonth = dismonth[0];
		
	}
	

	/**
	 * Select the Date
	 */
	
	List<WebElement> rows,cols;
	int flag = 0;
	rows=cal.findElements(By.tagName("tr"));
	
	for (int i = 1; i < rows.size(); i++) 
	{
		cols=rows.get(i).findElements(By.xpath("td[@class = 'day']"));
		
		for (int j = 0; j < cols.size(); j++) 
		{
			
			
			caldt=cols.get(j).getText();
			System.out.println(caldt);
			if (caldt.equals(date))
			{
				cols.get(j).click();
				flag = 1;
				break;
				
			}
			}
		
		if (flag == 1)
		{
			break;
		}
	}
	

}


public boolean validPhone(String phone)
{
	
	boolean valid = false;
	String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
	CharSequence str = phone;
	Pattern pattern = Pattern.compile(expression);
	Matcher matcher = pattern.matcher(str);
	if(matcher.matches())
	valid = true;
	
	return valid;
}



@AfterSuite
public void tearDown() {

	if (driver != null) {
		driver.quit();
	}

}



}


