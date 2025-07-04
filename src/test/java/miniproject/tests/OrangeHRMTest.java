package miniproject.tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import miniproject.pages.DashboardPage;
import miniproject.pages.JobTitlesPage;
import miniproject.pages.LoginPage;
import miniproject.utility.ScreenshotUtil;
import miniproject.utility.WriteToCsvFile;

public class OrangeHRMTest {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    JobTitlesPage jobTitlesPage;
    @BeforeClass
    public void setup()
    {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        jobTitlesPage = new JobTitlesPage(driver);
    }

    @Test(priority = 1)
    public void loginTest() throws InterruptedException {

    	loginPage.login("Admin", "admin123");
        Thread.sleep(2000);
        assert dashboardPage.isDashboardDisplayed();
    }

    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void navigateToJobTitlesTest() throws InterruptedException {
        dashboardPage.navigateToJobTitles();
        assert jobTitlesPage.getAllJobTitles().size() > 0;
        
    }
    @Test(priority = 3,dependsOnMethods = "navigateToJobTitlesTest")
    public void printinglisttocsv()
    {
    	List<WebElement> li = jobTitlesPage.getAllJobTitles();
    	System.out.println(li.size());
        for(WebElement q : li)
        {
        	System.out.println(q.getText());
        }
        WriteToCsvFile.writeListToCSV(li,"src/test/resources/exceldata/");
    }
    @Test(priority = 4, dependsOnMethods = "navigateToJobTitlesTest")
    public void addJobTitleTest() throws InterruptedException 
    {
       jobTitlesPage.addJobTitle("Automation Tester");
       try
       {
    	   if (jobTitlesPage.isErrorMessageDisplayed()) {
               ScreenshotUtil.takeScreenshot(driver, "src/test/resources/screenshots/");
               System.out.println("Job title 'Automation Tester' already exists.");
           }
    	   else
    	   {
    		   System.out.println("Job title 'Automation Tester' added successfully.");
    	   }
       }
       catch(Exception e)
       {
    	   System.out.println("Job title 'Automation Tester' added successfully.");
       }       
    }
    @Test(priority = 5)
    public void logoutTest() throws InterruptedException {
    	Thread.sleep(2000);
		driver.findElement(By.className("oxd-userdropdown-tab")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/ul/li[4]/a")).click();
		Thread.sleep(1000);
    }
    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
