package scenarios.Aplikacja;


import Methods.BeforeClassMethod;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static Methods.ID.*;
import static Methods.Account.*;
import static Methods.Account.log;
import static org.junit.Assert.assertEquals;

/*
   Scenariusz WIT_28 - [Aplikacja]: Formularz "Zapomniałeś hasła?"

*/

public class Aplikacja_WIT_28 {

    static boolean AssertFlag = true;
    static boolean flag = true;
    static WebDriver driver;

    @BeforeClass
    public static void Setup() throws InterruptedException {
        driver = BeforeClassMethod.Start();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("### Scenariusz Aplikacja_WIT_28 - Formularz \"Zapomniałeś hasła?\"");
    }


    @Test
    public void Test1() throws InterruptedException {
        flag = true;
        try {
            driver.findElement(By.id(Forgot_Pass)).click();
            log.info("Przejscie do formularza resetowania hasla");

        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Przejście do formularza nie powiodło sie");
        }
        assertEquals(true, flag);
    }

    @Test
    public void Test2() throws InterruptedException {
        flag = true;
        try {
            boolean form = driver.findElements(By.id("reset_form")).size() > 0;
            if (form) {
                log.info("Wpisywanie niepoprawnego adresu email");
                Forgot_Password(driver, "test@test");
                form = driver.findElements(By.className("errorlist")).size() > 0;
                if (form) {
                    log.info("Pojawienie sie komunikatu o nieprawidlowym adresie email");
                } else {
                    log.warn("Nieprawidłowa obsługa niepoprawnego emaila");
                    flag = false;
                    AssertFlag = false;
                }
            }
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
        }
        assertEquals(true, flag);
    }


    @Test
    public void Test3() throws InterruptedException {
        flag = true;
        try {
            boolean form = driver.findElements(By.id("reset_form")).size() > 0;
            if (form) {
                log.info("Wpisywanie adresu email");
                Forgot_Password(driver, "test@test.com");
                TimeUnit.SECONDS.sleep(2);
                if (driver.findElement(By.id("content")).getText().contains("Wysłaliśmy email z instrukacji jak zresetować hasło.")) {
                    log.info("Prawidłowe wysłanie linku do resetu hasła");

                } else {
                    log.warn("Nieudana próba zresetowania hasła");
                    flag = false;
                    AssertFlag = false;
                }
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
            log.info("### Scenariusz Aplikacja_WIT_28 ukończony pomyślnie ");
        else
            log.warn("### Scenariusz Aplikacja_WIT_28 ukończony niepomyślnie ");
        driver.close();
    }

}
