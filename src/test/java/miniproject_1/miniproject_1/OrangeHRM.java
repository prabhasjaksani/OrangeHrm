package miniproject_1.miniproject_1;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class OrangeHRM {

	public static String timestamp()
	{
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, MalformedURLException 

	{
		
//		DesiredCapabilities capabilities = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",new String[]{"enable-automation"});

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		
		
		
		
		String baseUrl = "https://opensource-demo.orangehrmlive.com/";
		driver.get(baseUrl);	
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.name("username")).sendKeys("Admin");
		Thread.sleep(2000);
		driver.findElement(By.name("password")).sendKeys("admin123");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'oxd-button')]")).click();
		
		
//		WebElement dash = driver.findElement(By.className("oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module"));
		WebElement dash = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6"));
		String dash1 = dash.getText();
		
		List<WebElement> alljobtitles;
		if(dash1.equalsIgnoreCase("Dashboard"))
		{
			driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[normalize-space()='Job']")).click();
			Thread.sleep(2000);
			WebElement jt = driver.findElement(By.xpath("//a[normalize-space()='Job Titles']"));
			String js1 = jt.getText();
			if(js1.equalsIgnoreCase("Job Titles"))
			{
				driver.findElement(By.xpath("//a[normalize-space()='Job Titles']")).click();
				Thread.sleep(2000);
				alljobtitles = new ArrayList<>(driver.findElements(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div")));
				System.out.println(alljobtitles.size());
				
				for(WebElement op : alljobtitles)
				{
					System.out.println(op.getText());
					if(op.getText().equalsIgnoreCase("Automation Tester"))
					{
						
					}
				}
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[1]/div/button")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")).sendKeys("Automation Tester");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				boolean status = false;
				status = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")).isDisplayed();
				if(status == true)
				{
					TakesScreenshot ts = (TakesScreenshot)driver;
					File src = ts.getScreenshotAs(OutputType.FILE);
					try
					{
						File destination = new File("src/test/resourse/screenshots/" + timestamp() +" fullpage.png");
						FileUtils.copyFile(src, destination);
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				
			}
			else
			{
				driver.quit();
			}
		}
		else
		{
			driver.quit();
		}
		
		Thread.sleep(5000);
		driver.findElement(By.className("oxd-userdropdown-tab")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/ul/li[4]/a")).click();
		Thread.sleep(5000);
		driver.quit();
	}  
}
