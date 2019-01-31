package Myntra;

import org.testng.annotations.Test;


import excelio.Input;
import excelio.Output;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.remote.server.handler.DismissAlert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import dataProvider.ConfigfileReader;

public class NewTest {
	private WebDriver driver=null;
	private List<String> temp=new ArrayList<String>();
	ConfigfileReader ConfigfileReader;
	
  @Test
  public void f() throws InterruptedException, IOException, DocumentException {
	  ConfigfileReader= new ConfigfileReader();
	System.setProperty("webdriver.chrome.driver", ConfigfileReader.getDriverPath());
	  driver.manage().deleteAllCookies();
	//String baseUrl = "https://login.naukri.com/nLogin/Login.php";
	String baseUrl = ConfigfileReader.getApplicationUrl();
    
			driver.get(baseUrl);

    driver.manage().window().maximize();
    //WebDriverWait myWaitVar= new WebDriverWait(driver, 10);
    
    
    driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
    
    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	  
    WebElement account=driver.findElement(By.xpath("//span[contains (@class,'myntraweb-sprite desktop-iconUser sprites-user')]"));
    Mouse mouse = ((HasInputDevices) driver).getMouse();
    Locatable hoverItem = (Locatable) account;
    mouse.mouseMove(hoverItem.getCoordinates());
    Thread.sleep(5000);
    WebDriverWait wait1=new WebDriverWait(driver, 50L);
    WebElement wd=driver.findElement(By.xpath("//a[contains (@class,'desktop-linkButton')] [contains(text(),'login')]"));
    Locatable clickItem = (Locatable) wd;
    mouse.mouseDown(clickItem.getCoordinates());
    mouse.mouseUp(clickItem.getCoordinates());
    
    String filePath=ConfigfileReader.getExcelPath();
    String fileName=ConfigfileReader.getExcelName();
    String sheetName=ConfigfileReader.getSheetName();
	
	
    temp = Input.readExcel(filePath, fileName, sheetName);
    
    driver.findElement(By.xpath("//input[@class='login-user-input-email login-user-input']")).sendKeys(temp.get(0));
    driver.findElement(By.xpath("//input[@class='login-user-input-password login-user-input']")).sendKeys(temp.get(1));
    
    driver.findElement(By.xpath("//button[@class='login-login-button']")).click();
  
    try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
	}
    
    Actions action = new Actions(driver);
    
    File inputFile = new File(System.getProperty("user.dir") +"\\NewFile.xml");
    SAXReader saxReader = new SAXReader();
    Document document = saxReader.read(inputFile);
    String category=document.selectSingleNode("//menu/category").getText();
    String subcategory=document.selectSingleNode("//menu/subcategory").getText();
    String brand=document.selectSingleNode("//menu/brand").getText();
    
    WebElement wb =driver.findElement(By.xpath(category));
    action.moveToElement(wb).moveToElement(driver.findElement(By.xpath(subcategory))).click().build().perform();
    WebElement optionNike = driver.findElement(By.xpath(brand)); 
    optionNike.click();
    if (optionNike.isSelected()) {
    	System.out.println("Checkbox is ON");
    }
    else {
    	System.out.println("Checkbox is OFF");
    }
    
    Thread.sleep(5000);
    
    driver.findElement(By.xpath("//img[@class='product-thumb'] [contains (@alt,'Adidas Manchester United Men Red Home Jersey')]")).click();
    driver.findElement(By.xpath("//p[@class='size-buttons-unified-size'] [contains (text(),'XL')]")).click();
    driver.findElement(By.xpath("//button[@class='pdp-add-to-bag pdp-button']")).click();
    
    driver.navigate().back();
    String brand1=document.selectSingleNode("//menu/brand1").getText();
    WebElement optionAdidas = driver.findElement(By.xpath(brand1));
    optionAdidas.click();
    if (optionAdidas.isSelected()) {
    	System.out.println("ON");
    }
    else {
    	System.out.println("OFF");
    }
    
    driver.findElement(By.xpath("//img[@class='product-thumb'] [contains (@alt,'Nike Men Blue FC Barcelona Stadium Home Printed Round Neck Football Jersey')]")).click();
    driver.findElement(By.xpath("//p[@class='size-buttons-unified-size'] [contains (text(),'XL')]")).click();
    driver.findElement(By.xpath("//button[@class='pdp-add-to-bag pdp-button']")).click();
    
    driver.findElement(By.xpath("//span[@class='myntraweb-sprite desktop-iconBag sprites-bag']")).click();
   // driver.findElement(By.xpath("//span[@class='text m-gray confirm-delete-item tappable'][2]")).click();
   
    //driver.findElement(By.xpath("Nike Men Blue Chelsea Football Club Stadium Home Soccer Jersey"));
    List<WebElement> list1=driver.findElements(By.xpath(".//*[@class=\"text m-gray confirm-delete-item tappable\"]"));
    list1.get(1).click();
    
    driver.findElement(By.xpath("//button[@class='btn primary-btn btn-remove m-button']")).click();
    
    //driver.findElement(By.xpath("//a[@class='c-gray'] [contains (text(),'Nike Men Blue Chelsea Football Club Stadium Home Soccer Jersey')]//div[@class='edit-move-delete']//div[@class='actions']//span[@class='text m-gray confirm-delete-item tappable']")).click();
    //driver.findElement(By.xpath("//button[@class='btn primary-btn btn-remove m-button']")).click();
    
    Thread.sleep(5000);
    
    WebElement couponUnApplied = driver.findElement(By.xpath("//a[@class='blue apply-coupon apply m-hide']"));
    
   // WebElement couponApplied = null;
    
   
    	
    	 driver.findElement(By.xpath("//a[@class='blue apply-coupon apply m-hide']")).click();
     	 WebElement coupon = driver.findElement(By.xpath("//span[@class='coupon-opt opt selected']"));
     	
         if (coupon.isDisplayed()) {
         	String couponcode=driver.findElement(By.xpath("//span[@class='coupon-code']")).getText();
         	driver.findElement(By.name("coupon_code")).sendKeys(couponcode);
         	driver.findElement(By.name("btn-apply")).click();
    	
    	
        
        }
        
        else {
        	
            
        	driver.findElement(By.xpath("//button[@class='btn normal-btn btn-cancel clickable']")).click();
        	
       
        }
         
         
         Thread.sleep(5000);
    	
            
                driver.findElement(By.xpath("//button[@class='btn primary-btn btn-continue green-button clickable  ']")).click();
                
                driver.findElement(By.xpath("//span[@class='address-type'][contains (text(),'HOME')]")).click();
                
                Thread.sleep(5000);
                
            	driver.findElement(By.xpath("//button[@class='btn primary-btn btn-continue green-button clickable undefined']")).click();
            	
            	driver.navigate().back();
            	
            	driver.findElement(By.xpath("//div[@class='txt'] [contains (text(),'Bag')]")).click();
            	
            	driver.findElement(By.xpath("//span[@class='text m-gray confirm-delete-item tappable']")).click();
            	
            	driver.findElement(By.xpath("//button[@class='btn primary-btn btn-remove m-button']")).click();
            	
            	Thread.sleep(5000);
            	
            	driver.findElement(By.className("logo")).click();
            	
            	WebElement user = driver.findElement(By.xpath("//span[@class='myntraweb-sprite desktop-iconUser sprites-user']"));
            	action.moveToElement(user).moveToElement(driver.findElement(By.xpath("//div[@class='desktop-accInfoSection'] [contains (text(),'Logout')]"))).click().build().perform();
  
   
    
   
    
    /*
    driver.findElement(By.xpath("//a[@class='blue apply-coupon apply m-hide']")).click();
    
    WebElement coupon = driver.findElement(By.xpath("//span[@class='coupon-opt opt selected']"));
    if (coupon.isDisplayed()) {
    	String couponcode=driver.findElement(By.xpath("//span[@class='coupon-code']")).getText();
    	driver.findElement(By.name("coupon_code")).sendKeys(couponcode);
    	driver.findElement(By.name("btn-apply")).click();
    }
    
    else {
    	driver.findElement(By.xpath("//button[@class='btn normal-btn btn-cancel clickable']")).click();
    }
    
    */
    
    
    
    /*
    String sheetName1=ConfigfileReader.getSheetName1();
    String text = driver.findElement(By.xpath("//span['Go Cashless & get Rs.100 OFF on online orders. Use coupon ONLINE100 to avail. TCA.']")).getText();
    String arr[]= {text};
    Output.writeExcel(filePath, fileName, sheetName1, arr);
    */
    
    
    /*
    WebElement we = driver.findElement(By.xpath("//a[@class='desktop-main']"));
    action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//a[contains (@href,'men-sports-wear-tshirts')] [contains(text(),'Active T-Shirts')]"))).click().build().perform();
    driver.findElement(By.xpath("//img[@class='product-thumb'] [contains (@alt,'Adidas Manchester United Men Red Home Jersey')]")).click();
    driver.findElement(By.xpath("//p[@class='size-buttons-unified-size'] [contains (text(),'XL')]")).click();
    driver.findElement(By.xpath("//button[@class='pdp-add-to-bag pdp-button']")).click();
    */
    
    /*
    driver.findElement(By.xpath("//a[@class='desktop-main']")).click();
    Mouse mouse1 = ((HasInputDevices) driver).getMouse();
    Locatable hoverItem1 = (Locatable) account;
    mouse1.mouseMove(hoverItem1.getCoordinates());
    System.out.println("Check");
    Thread.sleep(5000);
    
    WebDriverWait wait2=new WebDriverWait(driver, 50L);
    WebElement wd1=driver.findElement(By.xpath("//a[contains (@href,'men-sports-wear-tshirts')] [contains(text(),'Active T-Shirts')]"));
    Locatable clickItem1 = (Locatable) wd1;
    mouse1.mouseDown(clickItem1.getCoordinates());
    mouse1.mouseUp(clickItem1.getCoordinates());
    */
    
    
    //wd.click();
    System.out.println(driver.getTitle());
    
    
    
    
  }
  @BeforeTest
  public void beforeTest() {
	  //driver = new ChromeDriver();
	  //Map<String, Object> prefs= new HashMap<String, Object>;
	  ChromeOptions options = new ChromeOptions();
	  options.addArguments("--disable-notifications");
	  driver = new ChromeDriver(options);
	  

  }

  @AfterTest
  public void afterTest() throws IOException {
	  System.out.println(driver.getTitle());
	  
	  	String filePath=ConfigfileReader.getExcelPath();
	    String fileName=ConfigfileReader.getExcelName();
	    String sheetName1=ConfigfileReader.getSheetName1();
	    String arr[]= {"Status" , "Successfully Completed"};
	    Output.writeExcel(filePath, fileName, sheetName1, arr);
	    driver.manage().deleteAllCookies();
	    driver.close();
	    
  }

}
