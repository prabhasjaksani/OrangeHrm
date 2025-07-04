package miniproject.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;

public class WriteToCsvFile {

    public static void writeListToCSV(List<WebElement> list, String filepath) {
        try(FileWriter writer = new FileWriter(filepath + timestamp() + " jobtitle.csv")) {
            for (WebElement element : list) {
                writer.append(element.getText());
                writer.append("\n");
            }
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
}
