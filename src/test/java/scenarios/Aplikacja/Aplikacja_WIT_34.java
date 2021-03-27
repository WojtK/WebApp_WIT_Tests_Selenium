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
import static org.junit.Assert.assertEquals;

/*
   Scenariusz WIT_34 - Blog - udostępnianie postu

*/


public class Aplikacja_WIT_34 {

    static boolean AssertFlag = true;
    static boolean flag = true;
    static WebDriver driver;

    @BeforeClass
    public static void Setup() throws InterruptedException {
        driver = BeforeClassMethod.Start();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("### Scenariusz Aplikacja_WIT_34 - Blog - udostępnianie postu");
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
            driver.findElement(By.id("blog_post")).click();
            log.info("Przejscie do szczegółów postu");
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieprawidłowe przejscie do szczegółów postu");
        }
        assertEquals(true, flag);
    }


    @Test
    public void Test4() {
        flag = true;
        try {
            String title = driver.findElement(By.id("post_title")).getText();
            String date = driver.findElement(By.id("publish_date")).getText();
            log.info("Post tytuł: " + title + "\n"+ date);

            driver.findElement(By.id("share_post")).click();
            log.info("Przejscie do formularza udostępniania postu");
            if (driver.findElement(By.id("post_share")).getText().contains("Udostępnij"))
                log.info("Prawidłowe przejscie do formularza");
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieprawidłowe przejscie do formularza udostepniania");
        }
        assertEquals(true, flag);
    }

    @Test
    public void Test5() {
        flag = true;
        try {
            Share_Post(driver, "tresc komentarza", "test@test.com", "odbiorca@to.com", "test");
            log.info("Wypełnianie formularza");
            if (driver.findElement(By.id("content")).getText().contains("\"test\" został pomyślnie wysłany do odbiorca@to.com")) {
                log.info("Prawidłowe wypełnienie i udostepnienie postu");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieprawidłowa obsługa postu");
        }
        assertEquals(true, flag);
    }

    @AfterClass
    public static void Close() {
        if (AssertFlag)
            log.info("### Scenariusz Aplikacja_WIT_34 ukończony pomyślnie ");
        else
            log.warn("### Scenariusz Aplikacja_WIT_34 ukończony niepomyślnie ");
        driver.close();
    }

}
