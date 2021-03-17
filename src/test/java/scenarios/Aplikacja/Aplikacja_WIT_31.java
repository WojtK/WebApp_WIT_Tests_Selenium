package scenarios.Aplikacja;

import Methods.BeforeClassMethod;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static Methods.Account.Is_Logged_In;
import static Methods.Account.log;
import static Methods.Data.*;
import static org.junit.Assert.assertEquals;

/*
   Scenariusz WIT_31 - [Aplikacja]: Logowanie - nieprawidłowe dane

*/


public class Aplikacja_WIT_31 {

    static boolean AssertFlag = true;
    static boolean flag = true;
    static WebDriver driver;

    @BeforeClass
    public static void Setup() throws InterruptedException {
        driver = BeforeClassMethod.Start();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("### Scenariusz Aplikacja_WIT_31 - Logowanie - nieprawidłowe dane");
    }


    @Test
    public void Test1() throws InterruptedException {
        flag = true;
        try {
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("password")).clear();
            log.info("Próba logowania incorrect username:" + IncorrectUsername + " hasło:" + CorrectPassword);
            driver.findElement(By.name("username")).sendKeys(IncorrectUsername);
            driver.findElement(By.name("password")).sendKeys(CorrectPassword);
            driver.findElement(By.id("log_in_btn")).click();
            driver.findElement(By.id("incorrect_login"));
            log.info("Prawidłowa obsługa przy logowaniu z błędnym username");
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Niepoprawna obsługa");
        }
        assertEquals(true, flag);
    }

    @Test
    public void Test2() throws InterruptedException {
        flag = true;
        try {
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("password")).clear();
            log.info("Próba logowania incorrect username:" + CorrectUsername + " incorrect hasło:" + IncorrectPassword);
            driver.findElement(By.name("username")).sendKeys(CorrectUsername);
            driver.findElement(By.name("password")).sendKeys(IncorrectPassword);
            driver.findElement(By.id("log_in_btn")).click();
            driver.findElement(By.id("incorrect_login"));
            log.info("Prawidłowa obsługa przy logowaniu z błędnym hasłem");
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Niepoprawna obsługa");
        }
        assertEquals(true, flag);
    }

    @Test
    public void Test3() throws InterruptedException {
        flag = true;
        try {
            if (Is_Logged_In(driver)) {
                log.warn("Konto zalogowane, cos jest nie tak");
                flag = false;
                AssertFlag = false;
            } else {
                log.info("Niezalogowane konto");
            }
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
        }
        assertEquals(true, flag);
    }


    @AfterClass
    public static void Close() {
        if (AssertFlag)
            log.info("### Scenariusz Aplikacja_WIT_31 ukończony pomyślnie ");
        else
            log.warn("### Scenariusz Aplikacja_WIT_31 ukończony niepomyślnie ");
        driver.close();
    }

}
