package TestCases;

import PageObjectLibrary.GoogleSearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestSandBox {

    private WebDriver driver;
    private GoogleSearchPage googleSearchPage;

    @Parameters({"browser"})
    @BeforeMethod
    public void SetUp(@Optional("edge") String browser){

        switch (browser) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--window-size=1920,1200", "--ignore-certificate-errors");
                System.setProperty("webdriver.chrome.driver", "C:/Users/Andy/Downloads/chromedriver_win32/chromedriver.exe");
                driver = new ChromeDriver(chromeOptions);
            }
            case "edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless", "--window-size=1920,1200", "--ignore-certificate-errors");
                System.setProperty("webdriver.edge.driver", "C:/Users/erptest/Downloads/edgedriver_win32/msedgedriver.exe");
                driver = new EdgeDriver(edgeOptions);
            }
        }

        googleSearchPage = new GoogleSearchPage(driver);
    }

    @AfterMethod
    public void CleanUp(){
        driver.quit();
    }

    @Test
    public void url_redirect(){
        String originalUrl = "http://www.google.co.uk";
        String expectedUrl = "https://www.google.co.uk/?gws_rd=ssl";

        driver.get(originalUrl);

        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl);
    }

    @Test
    public void url_after_search(){
        String originalUrl = "http://www.google.com";
        String searchTerm = "selenium";
        String expectedUrl = "https://www.google.com/search?q="+searchTerm;

        driver.get(originalUrl);

        googleSearchPage.acceptCookies();

        String currentUrl = googleSearchPage.submitSearchTerm(searchTerm);

        Assert.assertTrue(currentUrl.contains(expectedUrl));
    }
}
