//package miniproject.pages;


package miniproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6")
    WebElement dashboardHeader;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span")
    WebElement adminMenu;

    @FindBy(xpath = "//span[normalize-space()='Job']")
    WebElement jobMenu;

    @FindBy(xpath = "//a[normalize-space()='Job Titles']")
    WebElement jobTitlesMenu;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed() {
        return dashboardHeader.getText().equalsIgnoreCase("Dashboard");
    }

    public void navigateToJobTitles() throws InterruptedException {
        adminMenu.click();
        
        jobMenu.click();
        
        jobTitlesMenu.click();
        
    }
}
