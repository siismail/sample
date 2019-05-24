package Test;

import org.testng.annotations.Test;
import PageObjects.HomePage;
import lib.FSC_Base;

import org.openqa.selenium.support.PageFactory;




public class HomePageTest extends FSC_Base{
	
	
	HomePage home;

	public HomePageTest() {
		super();
	}
	
 
  
  
  @Test  
  public void titleTest() {
	  
	 HomePage home = PageFactory.initElements(driver, HomePage.class);
	 home.testTitle();
	  
  }

  
 

  
}

