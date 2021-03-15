package Methods;

import org.openqa.selenium.WebDriver;

import static Methods.Data.Url;

public class BeforeClassMethod {


    public static WebDriver Start()
    {
        WebDriver driver = WebDriverInit.NewDriver("chrome");
        driver.manage().window().maximize();
        driver.get(Url);

        return driver;
    }
}
