package Methods;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverInit {
     public static org.openqa.selenium.WebDriver NewDriver(String DriverName)
     {

         switch(DriverName.toLowerCase())
         {
             case "firefox":
                 return new FirefoxDriver();
             case "chrome":
                 return new ChromeDriver();
         }
         return null;
     }
}
