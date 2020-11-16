package ohtu;

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

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin

        sleep(2);
        element.submit();
        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin

        sleep(3);

        System.out.println(driver.getPageSource()); // tulostetaan sivu konsoliin

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
