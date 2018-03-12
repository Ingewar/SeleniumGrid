package conf;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.GoogleStartPage;
import sun.security.krb5.internal.crypto.Des;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestManager {

    protected static WebDriver driver;
    private String baseUrl = "http://google.com/";
    private static String uniqValue;
    public static String uniqPhoneNumber;

    // Create instance of Google Start page
    protected GoogleStartPage googleStartPage;

    //Getting properties from Maven -D
    protected String os = System.getProperty("os").toLowerCase();
    protected String browser = System.getProperty("browser").toLowerCase();


    @BeforeClass(alwaysRun = true)
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = null;

        if(browser.contains("chrome")){
            capabilities = DesiredCapabilities.chrome();
        }else if(browser.contains("firefox")){
            capabilities = DesiredCapabilities.firefox();
        }else if(browser.contains("safari")){
            capabilities = DesiredCapabilities.safari();
        }

        switch (os){
            case "mac":
                capabilities.setPlatform(Platform.MAC);
                break;
            case "windows":
                capabilities.setPlatform(Platform.WINDOWS);
                break;
            case "linux":
                capabilities.setPlatform(Platform.LINUX);
        }

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1440, 900));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        googleStartPage = new GoogleStartPage();
    }


    @BeforeMethod(alwaysRun = true)
    public void openBaseUrl(){
        driver.manage().deleteAllCookies();
        driver.get(baseUrl);
        uniqValue = RandomStringUtils.randomAlphanumeric(10);
        uniqPhoneNumber = "+380"+RandomStringUtils.randomNumeric(9);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }


    public static WebDriver getDriver(){ return driver; }

    public static void waitInSeconds(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Adding random value to all except empty string or it contains 'auto'
    public static String addUniqValue(String value) {
        String result = "";
        if (value.toLowerCase().contains("auto".toLowerCase())) {
            result = value;
        } else if (!value.equals("")) {
            if (value.contains("@")) {
                String[] arrOfStrings = value.split("@", 2);
                result = arrOfStrings[0] + "" + uniqValue + "@" + arrOfStrings[1];
            } else if (value.toLowerCase().contains("auto".toLowerCase())) {
                result = value;
            } else {
                result = value + "" + uniqValue;
            }
        }
        return result;
    }
}
