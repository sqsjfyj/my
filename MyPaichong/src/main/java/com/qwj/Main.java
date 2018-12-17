package com.qwj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class Main implements PageProcessor {

    public static void main(String[] args){
//        System.setProperty("webdriver.chrome.driver", "G:\\chromedriver_win32\\chromedriver.exe");
        //System.setProperty("webdriver.firefox.bin", "G:\\Mozilla Firefox\\firefox.exe");
//        WebDriver webDriver = new ChromeDriver();
        HtmlUnitDriver webDriver = new HtmlUnitDriver();
//        WebDriver webDriver = new htmlunit
        webDriver.navigate().to("https://www.baidu.com");
        WebElement webElement = webDriver.findElement(By.id("su"));
        System.out.println(webElement.getAttribute("value"));
        webDriver.quit();
    }

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return null;
    }
}
