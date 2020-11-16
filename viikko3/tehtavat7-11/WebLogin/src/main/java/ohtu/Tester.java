package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");
        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin

        sleep(2);

        // Test creating new user
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin

        sleep(2);

        element = driver.findElement(By.name("username"));
        Random r = new Random();
        element.sendKeys("user" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("password123");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("password123");
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();
        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin

        sleep(2);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin

        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin

//        // Test login
//        WebElement element = driver.findElement(By.linkText("login")); // kirjautuminen
//        element.click();
//        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin
//
//        sleep(2);
//
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        // element.sendKeys("akkep"); // login with correct password
//        element.sendKeys("wrong"); // login with incorrect password
//        element = driver.findElement(By.name("login"));
//        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin
//
//        sleep(2);
//        element.submit();
//        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin

        sleep(3);

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
