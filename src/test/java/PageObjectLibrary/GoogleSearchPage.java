package PageObjectLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage {
    protected WebDriver driver;

    private final By acceptCookiesButton_By = By.xpath("//div[text()='Accept all']");
    private final By searchField_By = By.xpath("//input[@title='Search']");
    private final By searchButton_By = By.xpath("//form/div/div/div/center/input[@value='Google Search']");

    public GoogleSearchPage(WebDriver driver){
        this.driver = driver;
    }

    public void acceptCookies(){
        WebElement acceptCookiesButton = driver.findElement(this.acceptCookiesButton_By);
        acceptCookiesButton.click();
    }

    public String submitSearchTerm(String searchTerm){
        WebElement searchField = driver.findElement(this.searchField_By);
        searchField.sendKeys(searchTerm);

        WebElement searchButton = driver.findElement(this.searchButton_By);
        searchButton.click();

        return driver.getCurrentUrl();
    }
}
