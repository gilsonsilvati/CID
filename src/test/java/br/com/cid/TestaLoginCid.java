package br.com.cid;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestaLoginCid {

	private static WebDriver driver;
	private static WebElement element;
	
	private static final String GOOGLE_CHROME = "/home/gilsonsilvati/Documentos/chromedriver";
	
	private static final String URL = "http://127.0.0.1:8080/cid";

	@BeforeClass
	public static void setUpTest() {
		System.setProperty("webdriver.chrome.driver", GOOGLE_CHROME);
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
	}

	@AfterClass
	public static void tearDownTest() {
		driver.quit();
	}
	
	@Test
	public void testaLoginCidSuccess() throws InterruptedException {
		element = driver.findElement(By.id("frm:email"));
		element.sendKeys("gilsonsilvati@gmail.com");

		element = driver.findElement(By.id("frm:senha"));
		element.sendKeys("121212");
		
		driver.findElement(By.id("frm:entrar")).click();
		
		Thread.sleep(5000);
	}

	@Test
	public void testaLoginCidCampoObrigatorio() throws InterruptedException {
		element = driver.findElement(By.id("frm:email"));
		element.sendKeys("gilsonsilvati@gmail.com");

		element = driver.findElement(By.id("frm:senha"));
		element.sendKeys("");
		
		driver.findElement(By.id("frm:entrar")).click();
		
		Thread.sleep(5000);
	}

	@Test
	public void testaLoginCidInvalid() throws InterruptedException {
		element = driver.findElement(By.id("frm:email"));
		element.sendKeys("josy.love@gmail.com");

		element = driver.findElement(By.id("frm:senha"));
		element.sendKeys("121212");
		
		driver.findElement(By.id("frm:entrar")).click();
		
		Thread.sleep(5000);
	}

}

