package scenarios.Aplikacja;

import Methods.BeforeClassMethod;
import Methods.Logging;
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
   Scenariusz WIT_27 - [Aplikacja]: Wylogowanie

*/

public class Aplikacja_WIT_27 {

    static boolean AssertFlag = true;
    static boolean flag = true;
    static WebDriver driver;

    @BeforeClass
    public static void Setup() throws InterruptedException {
        driver = BeforeClassMethod.Start();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        log.info("# Scenariusz Aplikacja_WIT_27 - Wylogowanie");
        Log_in(driver);
    }


    @Test
    public void Test1() throws InterruptedException {
        flag = true;
        try {
            if(Logging.Is_Logged_In(driver)) log.info("Zalogowane konto");
            else { log.warn("Brak zalogowanego konta"); Log_in(driver); }
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
        assertEquals(true, AssertFlag);
    }

    @Test
    public void Test3() {
        flag = false;
        try { Log_out(driver); log.info("Wylogowanie udane");}
        catch (Exception e) {
            flag = false;
            AssertFlag = false;
            log.warn("Wylogowanie nie powiodło się");
        }
        assertEquals(true, AssertFlag);
    }

    @AfterClass
    public static void Close() {
        if (AssertFlag)
            log.info("### Scenariusz Aplikacja_WIT_27 ukończony pomyślnie ");
        else
            log.warn("### Scenariusz Aplikacja_WIT_27 ukończony niepomyślnie ");
        driver.close();
    }

}
