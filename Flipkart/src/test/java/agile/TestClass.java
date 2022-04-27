package agile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass {
 public static void main(String []args)
 {
	 System.setProperty("webdriver.chrome.driver", "F:\\Software Testing\\Chrome\\chromedriver.exe");
	 WebDriver driver = new ChromeDriver();
	 
	 driver.get("https://www.flipkart.com/");
 }
}
