package scenarios.Aplikacja;

import Methods.BeforeClassMethod;
import Methods.Account;
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
   Scenariusz WIT_30 - [Aplikacja]: Edycja konta

*/

public class Aplikacja_WIT_30 {

    static boolean AssertFlag = true;
    static boolean flag = true;
    static WebDriver driver;
    static String IncorrectEmail = "test@test";
    static String CorrectEmail = "testedycja@test.com";
    static String OldEmail = "test@test.com";
    static String NewUsername = "test_edycja";
    static String OldUsername = "test";

    @BeforeClass
    public static void Setup() throws InterruptedException {
        driver = BeforeClassMethod.Start();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("### Scenariusz Aplikacja_WIT_30 - Edycja konta");
        Log_in(driver);
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
            int status = Edit_Account(driver, NewUsername, IncorrectEmail);
            log.info("Edycja ustawien konta - nieprawidlowy email");
            if (status == 1) {
                flag = false;
                AssertFlag = false;
                log.warn("Nieprawidłowa obsługa nieprawidłowego emaila");
            } else {
                log.info("Prawidłowa obsługa nieprawidłowego emaila");
            }
        } catch (Exception e) {
        }
        assertEquals(true, flag);
    }

    @Test
    public void Test3() {
        flag = true;
        try {
            log.info("Edycja ustawien konta - prawidlowe imie i email");
            Edit_Account(driver, NewUsername, CorrectEmail);
            log.info("Prawidłowa aktualizacja profilu");
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieprawidłowa aktualizacja profilu");
        }
        assertEquals(true, flag);
    }

    @Test
    public void Test4() throws InterruptedException {
        flag = true;
        try {
            log.info("Edycja ustawien konta - przywrócenie pierwotnych danych");
            Edit_Account(driver, OldUsername, OldEmail);
            log.info("Prawidłowe przywrócenie pierwotnych danych");
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieprawidłowe przywrócenie pierwotnych danych");
        }
        TimeUnit.SECONDS.sleep(5);
        assertEquals(true, flag);
    }


    @AfterClass
    public static void Close() {
        if (AssertFlag)
            log.info("### Scenariusz Aplikacja_WIT_30 ukończony pomyślnie ");
        else
            log.warn("### Scenariusz Aplikacja_WIT_30 ukończony niepomyślnie ");
        driver.close();
    }

}
