package PageObjects;

import lib.FSC_Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

//import lib.ExcelFileReader;

public class HomePage extends FSC_Base{
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//ExcelFileReader excel = new ExcelFileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\testdata.xlsx");
	
		
	@FindBy(xpath = "//*[@id=\"uh-signin\"]") 
	public static WebElement SignIn_link;
	
	@FindBy(xpath = "//*[@id=\"uh-search-box\"]") 
	public WebElement search_box;
	
	@FindBy(xpath = "//*[@id=\"uh-search-button\"]") 
	public WebElement search_button;
	
	
	
	public void SignIn() {
	  
		Click (SignIn_link);
	}
	
	public void testTitle() {

		Assert.assertEquals(driver.getTitle(),"Yahoo");
	}
	
}
