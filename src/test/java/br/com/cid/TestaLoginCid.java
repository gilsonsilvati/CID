package br.com.cid;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestaLoginCid {

	private static WebDriver driver;
	private static final String URL = "http://127.0.0.1:8080/cid";

	@BeforeClass
	public static void setUpTest() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(URL);
	}

	@AfterClass
	public static void tearDownTest() {
		try {
			Thread.sleep(5000);
			driver.quit();
		} catch (InterruptedException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	 /* Fazer teste ao vivo :) */ 

	@Test
	public void testaLoginCidSuccess() {
		WebElement element = driver.findElement(By.id("frm:email"));
		element.sendKeys("gilsonsilvati@gmail.com");

		element = driver.findElement(By.id("frm:senha"));
		element.sendKeys("121212");
		
		driver.findElement(By.id("frm:entrar")).click();
	}

	@Test
	public void testaLoginCidCampoObrigatorio() {
		WebElement element = driver.findElement(By.id("frm:email"));
		element.sendKeys("gilsonsilvati@gmail.com");

		element = driver.findElement(By.id("frm:senha"));
		element.sendKeys("");
		
		driver.findElement(By.id("frm:entrar")).click();
	}

	@Test
	public void testaLoginCidInvalid() {
		WebElement element = driver.findElement(By.id("frm:email"));
		element.sendKeys("josy.love@gmail.com");

		element = driver.findElement(By.id("frm:senha"));
		element.sendKeys("121212");
		
		driver.findElement(By.id("frm:entrar")).click();
	}

}

