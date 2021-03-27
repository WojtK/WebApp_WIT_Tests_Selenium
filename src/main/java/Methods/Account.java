package Methods;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static Methods.Data.*;
import static Methods.Data.CorrectPassword;
import static Methods.ID.*;


//dorzucic loggera do tych prawdziwych testow 'import org.apache.log4j.Logger;'
//klasa YPT_7507 jest pokryta loggerami
public class Account {

    public static Logger log = Logger.getRootLogger();

    public static void Log_out(WebDriver driver) {
        if (Is_Logged_In(driver)) driver.findElement(By.id(Logout)).click();
    }

    public static void Log_in(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id(Log_In)).click();
        log.info("Próba logowania username:" + CorrectUsername + " hasło:" + CorrectPassword);
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.name("username")).sendKeys(CorrectUsername);
        driver.findElement(By.name("password")).sendKeys(CorrectPassword);
        driver.findElement(By.id("log_in_btn")).click();
    }


    public static void Log_in(WebDriver driver, String Username, String Password) throws InterruptedException {
        driver.findElement(By.id(Log_In)).click();
        log.info("Próba logowania username:" + Username + " hasło:" + Password);
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.name("username")).sendKeys(Username);
        driver.findElement(By.name("password")).sendKeys(Password);
        driver.findElement(By.id("log_in_btn")).click();
    }

    public static boolean Is_Logged_In(WebDriver driver) {
        try {
            driver.findElement(By.id(Logout));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void User_Register(WebDriver driver, String username,
                                     String name, String email,
                                     String password1, String password2) {
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("first_name")).clear();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password2")).clear();
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("first_name")).sendKeys(name);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password1);
        driver.findElement(By.name("password2")).sendKeys(password2);
        driver.findElement(By.id("register_btn")).click();
    }

    public static void Forgot_Password(WebDriver driver, String email) {
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.id("pass_reset_btn")).click();
    }

    public static int Edit_Account(WebDriver driver, String name, String email) {
        int status = 0;
        try {

            log.info("Przejscie do strony edycji konta");
            driver.findElement(By.id(Profile_Edit)).click();
            boolean form = driver.findElements(By.id("account_edit_form")).size() > 0;
            driver.findElement(By.name("first_name")).clear();
            driver.findElement(By.name("email")).clear();
            if (form) {
                status++;
            } else {
                throw new Exception();
            }
            driver.findElement(By.name("first_name")).sendKeys(name);
            driver.findElement(By.name("email")).sendKeys(email);
            driver.findElement(By.id("save_changes_btn")).click();
        } catch (Exception e) {
            if (status == 0) {
                log.warn("Nieudane przejscie do strony edycji konta");
                return 1;
            } else if (status == 1) {
                log.warn("Nieprawidłowa edycja konta");
                return 1;
            }

        }
        return 0;
    }

    public static void Share_Post(WebDriver driver, String content, String email, String to, String name) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("to")).sendKeys(to);
        driver.findElement(By.name("comments")).sendKeys(content);
        driver.findElement(By.id("send_post")).click();
    }

    public static void Add_Comment(WebDriver driver, String content, String email,String name) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("body")).sendKeys(content);
        driver.findElement(By.id("add_comment")).click();
    }

}