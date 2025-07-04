//package miniproject.utility;

package miniproject.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

    public static void takeScreenshot(WebDriver driver, String filePath) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        try {
            File destination = new File(filePath + timestamp() + " fullpage.png");
            FileUtils.copyFile(src, destination);
        } catch (Exception e) {
            System.out.println(e);
        }
}
}
