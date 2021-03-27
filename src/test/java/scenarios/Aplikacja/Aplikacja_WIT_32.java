package scenarios.Aplikacja;

import Methods.Account;
import Methods.BeforeClassMethod;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static Methods.Account.*;
import static Methods.Account.log;
import static Methods.ID.Register;
import static org.junit.Assert.assertEquals;

/*
   Scenariusz WIT_32 - [Aplikacja]: Moja strona główna

*/


public class Aplikacja_WIT_32 {

    static boolean AssertFlag = true;
    static boolean flag = true;
    static WebDriver driver;

    @BeforeClass
    public static void Setup() throws InterruptedException {
        driver = BeforeClassMethod.Start();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("### Scenariusz Aplikacja_WIT_32 - Moja strona główna");
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
            driver.findElement(By.id("dashboard")).click();
            log.info("Prawidłowe przejscie do strony głównej");
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieprawidłowe przejscie do strony głównej");
        }
        assertEquals(true, flag);
    }

    @Test
    public void Test3() {
        flag = true;
        try {
            String text = driver.findElement(By.id("content")).getText();
            if (text.contains("Strona główna") & text.contains("Witaj na stronie głównej. Możesz edytować swój profil albo zmienić hasło."))
                log.info("Strona główna ma prawidlowa zawartosc");
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieprawidłowa zawartosc strony głównej");
        }
        assertEquals(true, flag);
    }


    @AfterClass
    public static void Close() {
        if (AssertFlag)
            log.info("### Scenariusz Aplikacja_WIT_32 ukończony pomyślnie ");
        else
            log.warn("### Scenariusz Aplikacja_WIT_32 ukończony niepomyślnie ");
        driver.close();
    }

}
