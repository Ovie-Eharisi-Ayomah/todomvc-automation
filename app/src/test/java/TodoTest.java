/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    private static ChromeDriver driver;
    private static Map<String, Object> vars;
    static JavascriptExecutor js;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://todomvc.com/examples/react/#/");
    }

    // Your tests will go here!
    @Test
    void getTitleFromPage() {
        assertEquals("TodoMVC", driver.getTitle());
        System.out.println(driver.getTitle());
    }

    @Test
    void addNewTodoAndAssertTrue() {
//        WebElement addTodo = driver.findElement(By.cssSelector(".new-todo"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement addTodo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".new-todo")));
        addTodo.sendKeys("take a break");
        addTodo.sendKeys(Keys.ENTER);
        WebElement newTodo = driver.findElement(By.className("todo-list"));
//        System.out.println(newTodo.getText());
        assertEquals("take a break", newTodo.getText());
        assertEquals("1 item left", driver.findElement(By.className("todo-count")).getText());
    }

    @Test
    void addItemWithOneCharacterAndAssertTrue() {
//        WebElement addTodo = driver.findElement(By.cssSelector(".new-todo"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement addTodo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".new-todo")));
        addTodo.sendKeys("O");
        addTodo.sendKeys(Keys.ENTER);
        WebElement newTodo = driver.findElement(By.className("todo-list"));
//        System.out.println(newTodo.getText());
        assertEquals("O", newTodo.getText());
        assertEquals("1 item left", driver.findElement(By.className("todo-count")).getText());
    }

    @Test
    void addItemWithOneAccentedCharacterAndAssertTrue() {
//        WebElement addTodo = driver.findElement(By.cssSelector(".new-todo"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement addTodo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".new-todo")));
        addTodo.sendKeys("Énd the zoom call");
        addTodo.sendKeys(Keys.ENTER);
        WebElement newTodo = driver.findElement(By.className("todo-list"));
//        System.out.println(newTodo.getText());
        assertEquals("Énd the zoom call", newTodo.getText());
        assertEquals("1 item left", driver.findElement(By.className("todo-count")).getText());
    }

    @Test
    void addItemWithEmojiAndAssertTrue() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addTodo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".new-todo")));
        String text = " ⭐";
        addTodo.sendKeys("Clean" + text);
        Thread.sleep(3000);
        addTodo.sendKeys(Keys.ENTER);
        WebElement newTodo = driver.findElement(By.className("todo-list"));
        assertEquals("Clean" + text, newTodo.getText());
        assertEquals("1 item left", driver.findElement(By.className("todo-count")).getText());
    }

    @Test
    void addItemWithEmptyValue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement addTodo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".new-todo")));
        addTodo.sendKeys("Clean");
        addTodo.sendKeys(Keys.ENTER);
        addTodo.sendKeys(Keys.SPACE);
        addTodo.sendKeys(Keys.ENTER);
        assertEquals("1 item left", driver.findElement(By.className("todo-count")).getText());
    }
        @Test
        void characterLimit() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
            WebElement addTodo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".new-todo")));
            addTodo.sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo " +
                    "ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, " +
                    "nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretiu");
            addTodo.sendKeys(Keys.ENTER);
            WebElement newTodo = driver.findElement(By.className("todo-list"));
            assertEquals("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo " +
                    "ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, " +
                    "nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretiu", newTodo.getText());

        }
        @Test
        void clearCompleteLinkIsVisible() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
            WebElement addTodo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".new-todo")));
            addTodo.sendKeys("take a break");
            addTodo.sendKeys(Keys.ENTER);
            WebElement checkbox = driver.findElement(By.cssSelector(".toggle"));
            checkbox.click();
            WebElement clearCompletedLink = driver.findElement(By.cssSelector(".clear-completed"));
            assertTrue(clearCompletedLink.isDisplayed());

        }


    @Test
    void modifyATodoItemDoubleClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement addTodo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".new-todo")));
        addTodo.sendKeys("Clean");
        addTodo.sendKeys(Keys.ENTER);
        addTodo.sendKeys("Énd the zoom call");
        addTodo.sendKeys(Keys.ENTER);

        {
            WebElement element = driver.findElement(By.cssSelector("li:nth-child(1) label"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        driver.findElement(By.cssSelector(".editing > .edit")).sendKeys(" and dirty");
        driver.findElement(By.cssSelector(".editing > .edit")).sendKeys(Keys.ENTER);
        assertEquals("Clean and dirty", driver.findElement(By.cssSelector("li:nth-child(1) label")).getText());
    }

    @Test
    void statusBarDisplaysNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement addTodo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".new-todo")));
        addTodo.sendKeys("Clean");
        addTodo.sendKeys(Keys.ENTER);
        addTodo.sendKeys("Énd the zoom call");
        addTodo.sendKeys(Keys.ENTER);
        assertEquals("2 items left", driver.findElement(By.className("todo-count")).getText());
        driver.findElement(By.cssSelector("li:nth-child(1) .toggle")).click();
        assertEquals("1 item left", driver.findElement(By.className("todo-count")).getText());
        driver.findElement(By.cssSelector("li:nth-child(2) .toggle")).click();
        assertEquals("0 items left", driver.findElement(By.className("todo-count")).getText());
    }

    @Test
    void statusBarIsHidden() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement addTodo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".new-todo")));
        addTodo.sendKeys("Clean");
        addTodo.sendKeys(Keys.ENTER);
        assertTrue(driver.findElement(By.className("todo-count")).isDisplayed());
        driver.findElement(By.cssSelector("li:nth-child(1) .toggle")).click();
        driver.findElement(By.className("clear-completed")).click();
        assertFalse(driver.findElement(By.className("todo-count")).isDisplayed());

    }

    @Test
    void arrowTogglesAllTodos() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement addTodo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".new-todo")));
        addTodo.sendKeys("Clean");
        addTodo.sendKeys(Keys.ENTER);
        addTodo.sendKeys("Énd the zoom call");
        addTodo.sendKeys(Keys.ENTER);
        assertEquals("2 items left", driver.findElement(By.className("todo-count")).getText());
        WebElement arrow = driver.findElement(By.cssSelector(".main > label"));
        arrow.click();
        assertEquals("0 items left", driver.findElement(By.className("todo-count")).getText());
        arrow.click();
        assertEquals("2 items left", driver.findElement(By.className("todo-count")).getText());
    }
    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
