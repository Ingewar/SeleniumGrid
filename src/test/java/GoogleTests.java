import conf.MyListeners;
import conf.TestManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(MyListeners.class)
public class GoogleTests extends TestManager {

    @Test(groups = "All", alwaysRun = true)
    public void openGoogleStartPage(){

        System.out.println("=== "+ System.getProperty("os") +" ===");
        System.out.println("=== "+ System.getProperty("browser") +" ===");


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getTitle(), googleStartPage.getTitle());
    }
}
