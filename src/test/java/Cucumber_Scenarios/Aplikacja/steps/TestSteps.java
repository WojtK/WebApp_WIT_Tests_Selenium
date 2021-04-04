package Cucumber_Scenarios.Aplikacja.steps;

import Methods.Account;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static Methods.Account.log;

public class TestSteps {

    String username = "9999999088";
    String password = "4365";
    String username1 = "9999900018";
    String password1 = "0018";
    String url = "http://127.0.0.1:8000/account/login/?next=/account/";
    static WebDriver driver;

    @Before
    public static WebDriver Setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    @Given("Uzytkownik wchodzi na strone o adresie {string}")
    public void GetWebSite(String url) throws IOException, InterruptedException {
        log.info("Wejscie na strone "+url);
        driver.get(url);
    }

    @When("Uzytkownik wpisuje prawidlowy login {string} haslo {string} i klika \"loguj\"")
    public void Login(String username, String password) throws IOException, InterruptedException {
        Account.Log_in(driver,username,password);
        log.info("Logowanie na konto przy pomocy hasla: "+password+" login: "+username);
        TimeUnit.SECONDS.sleep(1);
    }

    @When("Uzytkownik wpisuje nieprawidlowy login {string} i nieprawidlowe haslo {string} i klika \"loguj\"")
    public void Invalid_Login(String username, String password) throws IOException, InterruptedException {
        Account.Log_in(driver,username,password);
        log.info("Logowanie na konto przy pomocy nieprawidlowych danych - hasla: "+password+" login: "+username);
        TimeUnit.SECONDS.sleep(1);
    }

    @Then("Uzytkownik dostaje komunikat o nieprawidlowym zalogowaniu")
    public void Incorrect_Login(){
        driver.findElement(By.id("incorrect_login"));
    }


    @Then("Uzytkownik wylogowuje sie")
    public void Logout() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("Wylogowanie sie z konta");
        Account.Log_out(driver);
    }

    @Then("Uzytkownik jest zalogowany")
    public void Is_Logged()
    {
        Account.Is_Logged_In(driver);
    }

    @After
    public static void Close()
    {
        driver.close();
    }
}
