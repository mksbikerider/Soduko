package name.msutherland.mvc.rest;

import name.msutherland.config.SpringStart;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

// Falling back to JUnit 4 as JUnit 5 does not work with SpringBootTest yet.
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringStart.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@RunWith(JUnitPlatform.class)
//@ExtendWith(SpringExtension::class)
public class SudokuRestIntegrationTest {

    private static WebDriver driver;
    @Autowired
    public ConfigurableApplicationContext applicationContext;

    @BeforeClass
    public static void beforeClass() throws InterruptedException {
        driver = new HtmlUnitDriver(true);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void afterClass(){
        driver.quit();
    }

    @Test
    public void loadPage() throws InterruptedException {
        applicationContext.isRunning();
        driver.get("http://localhost:5000/jsp/RestView.jsp");

        /*
        WebElement element = driver.findElement(By.xpath("/html"));
        String body = element.getAttribute("innerHTML");
        */

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("matrix_row_0")).getAttribute("ng-repeat").toLowerCase().startsWith("row");
            }
        });

    }


}
