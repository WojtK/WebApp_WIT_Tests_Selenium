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
import static org.junit.Assert.assertEquals;

/*
   Scenariusz WIT_35 - Blog - dodawanie komentarza

*/


public class Aplikacja_WIT_35 {

    static boolean AssertFlag = true;
    static boolean flag = true;
    static WebDriver driver;

    @BeforeClass
    public static void Setup() throws InterruptedException {
        driver = BeforeClassMethod.Start();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("### Scenariusz Aplikacja_WIT_35 - Blog - dodawanie komentarza");
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
            log.info("Post tytuł: " + title + "\n" + date);
            log.info("Próba dodania komentarza");
            Add_Comment(driver, "testowy komentarz", "test@selenium.com", "selenium");
            if (driver.findElement(By.id("success")).getText().contains("Twój komentarz został dodany")) {
                log.info("Udane dodanie komentarza");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Nieprawidłowe dodanie nowego komentarza");
        }
        assertEquals(true, flag);
    }


    @AfterClass
    public static void Close() {
        if (AssertFlag)
            log.info("### Scenariusz Aplikacja_WIT_35 ukończony pomyślnie ");
        else
            log.warn("### Scenariusz Aplikacja_WIT_35 ukończony niepomyślnie ");
        driver.close();
    }

}
