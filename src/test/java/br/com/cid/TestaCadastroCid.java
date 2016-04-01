package br.com.cid;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestaCadastroCid {
	
	private static WebDriver driver;
	private static WebElement element;
	
	private static final String URL_HOME = "http://127.0.0.1:8080/cid";
	private static final String URL_CADASTRO_CID = "http://127.0.0.1:8080/cid/paginas/cadastrar/CadastraDoenca.xhtml";
	private static final String URL_CONSULTA_CID = "http://127.0.0.1:8080/cid/paginas/consultar/ConsultaDoenca.xhtml";
	
	@BeforeClass
	public static void setUpTest() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(URL_HOME);
		
		efetuarlogin();
	}

	@AfterClass
	public static void tearDownTest() {
		driver.quit();
	}
	
	@Test
	public void testaCadastroCidSuccess() throws InterruptedException {
		driver.get(URL_CADASTRO_CID);
		
		element = driver.findElement(By.id("frm:cid"));
		element.sendKeys("N13");
		
		element = driver.findElement(By.id("frm:doenca"));
		element.sendKeys("Uropatia obstrutiva e por refluxo");
		
		element = driver.findElement(By.id("frm:panorama"));
		element.sendKeys("É uma doença na qual o fluxo de urina sofre uma inversão de direção."
				+ " Em vez de fluir dos rins para a bexiga, a urina \"reflui\" de volta para os rins."
				+ " Refluxo significa literalmente \"um líquido fluir de volta\".");
		
		driver.findElement(By.id("frm:salvar")).click();
		
		Thread.sleep(8000);
		
		driver.get(URL_CONSULTA_CID);
		
		Thread.sleep(5000);
	}
	
	@Test
	public void testaCadastroCidCampoObrigatorio() throws InterruptedException {
		driver.get(URL_CADASTRO_CID);
		
		element = driver.findElement(By.id("frm:cid"));
		element.sendKeys("A00");
		
		element = driver.findElement(By.id("frm:doenca"));
		element.sendKeys("Cólera");
		
		element = driver.findElement(By.id("frm:panorama"));
		element.sendKeys("");
		
		driver.findElement(By.id("frm:salvar")).click();
		
		Thread.sleep(5000);
		
		driver.get(URL_CONSULTA_CID);
		
		Thread.sleep(5000);
	}
	
	private static void efetuarlogin() {
		element = driver.findElement(By.id("frm:email"));
		element.sendKeys("gilsonsilvati@gmail.com");

		element = driver.findElement(By.id("frm:senha"));
		element.sendKeys("121212");
		
		driver.findElement(By.id("frm:entrar")).click();
	}

}

