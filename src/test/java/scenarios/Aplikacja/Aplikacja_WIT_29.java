package scenarios.Aplikacja;

import Methods.BeforeClassMethod;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static Methods.ID.*;
import static Methods.Account.User_Register;
import static Methods.Account.*;
import static org.junit.Assert.assertEquals;

/*
   Scenariusz WIT_29 - [Aplikacja]: Rejestrowanie nowego użytkownika

*/


public class Aplikacja_WIT_29 {

    static boolean AssertFlag = true;
    static boolean flag = true;
    static WebDriver driver;
    static String username = "test6";
    static String IncorrectUsername = "test";
    static String name = "test2";
    static String CorrectEmail = "test@test.com";
    static String IncorrecEmail = "test@test";
    static String CorrectPass = "test2";
    static String IncorrectPass = "test";

    @BeforeClass
    public static void Setup() throws InterruptedException {
        driver = BeforeClassMethod.Start();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("### Scenariusz Aplikacja_WIT_29 - Rejestrowanie nowego użytkownika");
    }


    @Test
    public void Test1() throws InterruptedException {
        flag = true;
        try {
            driver.findElement(By.id(Register)).click();
            log.info("Przejscie do formularza rejestracji uzytkownika");

        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Przejście do formularza rejestracji nie powiodło sie");
        }
        assertEquals(true, flag);
    }

    @Test
    public void Test2() throws InterruptedException {
        flag = true;
        try {
            boolean form = driver.findElements(By.id("register_form")).size() > 0;
            if (form) {
                log.info("Wpisywanie nazwy istniejącego juz uzytkownika");
                User_Register(driver, IncorrectUsername, name, CorrectEmail, CorrectPass, CorrectPass);
                form = driver.findElements(By.className("errorlist")).size() > 0;
                if (form) {
                    log.info("Pojawienie sie komunikatu o istniejacym uzytkowniku o tej nazwie");
                } else {
                    log.warn("Nieprawidłowa obsługa zajetej nazwy uzytkownika");
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
            boolean form = driver.findElements(By.id("register_form")).size() > 0;
            if (form) {
                log.info("Wpisywanie nieprawidlowego adresu email");
                User_Register(driver, username, name, IncorrecEmail, CorrectPass, CorrectPass);
                TimeUnit.SECONDS.sleep(2);
                form = driver.findElements(By.className("errorlist")).size() > 0;
                if (form) {
                    log.info("Poprawna obsluga nieprawidlowego adresu email");

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
    public void Test4() throws InterruptedException {
        flag = true;
        try {
            boolean form = driver.findElements(By.id("register_form")).size() > 0;
            if (form) {
                log.info("Nieprawidlowe powtórzenie hasła");
                User_Register(driver, username, name, CorrectEmail, CorrectPass, IncorrectPass);
                TimeUnit.SECONDS.sleep(2);
                form = driver.findElements(By.className("errorlist")).size() > 0;
                if (form) {
                    log.info("Prawidłowa obsluga źle powtórzonego hasła");

                } else {
                    log.warn("Nieprawidłowa obsługa niepoprawnie wpisanego hasła");
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
    public void Test5() throws InterruptedException {
        flag = true;
        try {
            boolean form = driver.findElements(By.id("register_form")).size() > 0;
            if (form) {
                log.info("Wpisywanie poprawnych danych");
                User_Register(driver, username, name, CorrectEmail, CorrectPass, CorrectPass);
                TimeUnit.SECONDS.sleep(2);
                form = driver.findElements(By.className("errorlist")).size() > 0;
                if (form == false) {
                    log.info("Udana rejestracja użytkownika");
                    Log_in(driver, "test1", "test1");
                    log.info("Udane zalogowanie sie na utworzone konto");

                } else {
                    log.warn("Nieudana rejestracja nowego uzytkownika");
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
            log.info("### Scenariusz Aplikacja_WIT_29 ukończony pomyślnie ");
        else
            log.warn("### Scenariusz Aplikacja_WIT_29 ukończony niepomyślnie ");
        driver.close();
    }

}
