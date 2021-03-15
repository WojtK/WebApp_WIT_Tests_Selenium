package Methods;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static Methods.Data.*;
import static Methods.Data.CorrectPassword;
import static Methods.ID.Logout;


//dorzucic loggera do tych prawdziwych testow 'import org.apache.log4j.Logger;'
//klasa YPT_7507 jest pokryta loggerami
public class Logging {

       public static Logger log = Logger.getRootLogger();

       public static void Log_out(WebDriver driver){
              if(Is_Logged_In(driver)) driver.findElement(By.id(Logout)).click();
       }

       public static void Log_in(WebDriver driver) throws InterruptedException {
              log.info("Próba logowania username:" + CorrectUsername + " hasło:" + CorrectPassword);
              TimeUnit.SECONDS.sleep(2);
              driver.findElement(By.name("username")).sendKeys(CorrectUsername);
              driver.findElement(By.name("password")).sendKeys(CorrectPassword);
              driver.findElement(By.id("log_in_btn")).click();
       }

       public static boolean Is_Logged_In(WebDriver driver)
       {
              try
              {
                     driver.findElement(By.id(Logout));
                     return true;
              }
              catch ( Exception e)
              {
                     return false;
              }
       }

}