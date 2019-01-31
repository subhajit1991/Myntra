package Myntra;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class NewTest {
	private WebDriver driver=null;
  @Test
  public void f() {
	  System.setProperty("webdriver.chrome.driver","C:\\Users\\SUBROY\\Downloads\\Chrome\\chromedriver.exe");
	  driver.manage().deleteAllCookies();
	//String baseUrl = "https://login.naukri.com/nLogin/Login.php";
	String baseUrl = "https://www.myntra.com/";
    //String baseUrl1 = "https://www.naukri.com/nlogin/login";
	//if (baseUrl == "https://login.naukri.com/nLogin/Login.php");
			driver.get(baseUrl);

    driver.manage().window().maximize();
    //WebDriverWait myWaitVar= new WebDriverWait(driver, 10);
    
    
    driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
    
    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	  
  }
  @BeforeTest
  public void beforeTest() {
	  driver = new ChromeDriver();
  }

  @AfterTest
  public void afterTest() {
  }

}
