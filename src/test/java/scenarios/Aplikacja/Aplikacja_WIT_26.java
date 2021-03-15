package scenarios.Aplikacja;

import Methods.BeforeClassMethod;
import org.junit.AfterClass;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Methods.Data.*;

import java.util.concurrent.TimeUnit;

import static Methods.ID.*;
import static Methods.Logging.*;
import static org.junit.Assert.assertEquals;

/*
   Scenariusz WIT_26 - [Aplikacja]: Logowanie - prawidłowe dane

*/

public class Aplikacja_WIT_26 {

    static boolean AssertFlag = true;
    static boolean flag = true;
    static WebDriver driver;

    @BeforeClass
    public static void Setup() throws InterruptedException {
        driver = BeforeClassMethod.Start();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("# Scenariusz Aplikacja_WIT_26 - Logowanie - prawidłowe dane");
    }


    @Test
    public void Test1() throws InterruptedException {
        flag = true;
        try {
            log.info("Próba logowania username:" + CorrectUsername + " hasło:" + CorrectPassword);
            TimeUnit.SECONDS.sleep(5);
            driver.findElement(By.name("username")).sendKeys(CorrectUsername);
            driver.findElement(By.name("password")).sendKeys(CorrectPassword);
            driver.findElement(By.id("log_in_btn")).click();
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieudana próba logowania");
        }
        assertEquals(true, AssertFlag);
    }


    @Test
    public void Test2() throws InterruptedException {
        flag = true;
        try {
            if (Is_Logged_In(driver)) {
                log.info("Udało się prawidłowo zalogować");
            } else {
                log.warn("Nieudane logowanie");
                flag = false;
                AssertFlag = false;
            }
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
        }
        TimeUnit.SECONDS.sleep(4);
        assertEquals(true, AssertFlag);
    }


    @AfterClass
    public static void Close() {
        if (AssertFlag)
            log.info("### Scenariusz Aplikacja_WIT_26 ukończony pomyślnie ");
        else
            log.warn("### Scenariusz Aplikacja_WIT_26 ukończony niepomyślnie ");
        driver.close();
    }

}
