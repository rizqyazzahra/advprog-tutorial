package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProductTitle_IsCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void createProduct_IsSuccessful(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        driver.findElement(By.id("nameInput")).sendKeys("Test Product");
        driver.findElement(By.id("quantityInput")).sendKeys("150");
        driver.findElement(By.tagName("button")).click();

        String pageTitle = driver.getTitle();
        assertEquals("Product List", pageTitle);

        String productName = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();
        String productQuantity = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();
        assertEquals("Test Product", productName);
        assertEquals("150", productQuantity);
    }
}