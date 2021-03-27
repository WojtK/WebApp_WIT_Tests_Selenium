package scenarios.Aplikacja;

import Methods.Account;
import Methods.BeforeClassMethod;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static Methods.Account.Log_in;
import static Methods.Account.log;
import static org.junit.Assert.assertEquals;

/*
   Scenariusz WIT_33 - Blog - lista postów

*/


public class Aplikacja_WIT_33 {

    static boolean AssertFlag = true;
    static boolean flag = true;
    static WebDriver driver;

    @BeforeClass
    public static void Setup() throws InterruptedException {
        driver = BeforeClassMethod.Start();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("### Scenariusz Aplikacja_WIT_33 - Blog - lista postów");
    }


    @Test
    public void Test1() throws InterruptedException {
        flag = true;
        try {
            if (Account.Is_Logged_In(driver)) log.info("Zalogowane konto");
            else {
                log.warn("Brak zalogowanego konta");
                Log_in(driver);
            }
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieudana próba logowania");
        }
        assertEquals(true, flag);
    }


    @Test
    public void Test2() throws InterruptedException {
        flag = true;
        try {
            driver.findElement(By.id("blog")).click();
            log.info("Prawidłowe przejscie do bloga");
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieprawidłowe przejscie do bloga");
        }
        assertEquals(true, flag);
    }

    @Test
    public void Test3() {
        flag = true;
        try {
            driver.findElement(By.id("blog_post"));
            driver.findElement(By.id("public_date"));
            driver.findElement(By.id("current_page"));
            driver.findElement(By.id("content"));
            log.info("Prawidłowa zawartosc bloga");
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieprawidłowa zawartosc bloga");
        }
        assertEquals(true, flag);
    }


    @AfterClass
    public static void Close() {
        if (AssertFlag)
            log.info("### Scenariusz Aplikacja_WIT_33 ukończony pomyślnie ");
        else
            log.warn("### Scenariusz Aplikacja_WIT_33 ukończony niepomyślnie ");
        driver.close();
    }

}
