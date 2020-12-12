package com.gestion;





import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomatizacionTest {
	private WebDriver driver;
	By localizadorProductos = By.linkText("Productos");
	By localizadorPaginaProductos = By.xpath("//a[@class='router-link-exact-active router-link-active' and @href='/Admin_productos']");
	By localizadorRegistro = By.xpath("//button[contains(text(), 'Registro de Producto')]");
	By localizadorTituloRegistro = By.className("registro_tittle");
	By localizadorusuario = By.xpath("//button[@class=\"nav-link\"]");
	By localizadorInputNombre = By.xpath("//input[@type='text' and @required='required' and @style]");
	By localizadorMensajeError1= By.xpath("//div[text()=' Nombre muy largo maximo 30 caracteres. ']");
	By localizadorDescripcion = By.xpath("//textarea");
	By localizadorContadorCaracteres = By.xpath("//div[text()=' 1000/1000 caracteres. ']");
	By localizadorBotonConfirmar = By.xpath("//button[@disabled='disabled']");
	By localizadorInputUnidades= By.xpath("//input[@type='text' and @required='required' and not(@style)]");
    By localizadorSelector = By.xpath("//input[@value='peso']");
	By localizadorInputPeso = By.xpath("//input[@class='formulario_peso']");
	By localizadorErrorUnidades = By.xpath("//div[text()=' Ingrese valores enteros entre (1-1000). ']");
	By localizadorErrorPeso = By.xpath("//div[text()=' Ingrese valores entre (0.10-100). ']");
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://gestion-productos.herokuapp.com/");
	}
	
	
	
	
	@Test
	public void testRegistro() throws InterruptedException {
		driver.findElement(localizadorusuario).click();
		driver.findElement(localizadorProductos).click();
		Thread.sleep(3000);
		if(driver.findElement(localizadorPaginaProductos).isEnabled()) {
			driver.findElement(localizadorRegistro).click();
			Thread.sleep(2000);
			if(!testRegistroVacio()) {
				testNombreYDescripcion();
				testUnidadesYPrecio();
			}
			
		}
	}
	private boolean testRegistroVacio() {
		return driver.findElement(localizadorBotonConfirmar).isEnabled();
	}
	
	private void testUnidadesYPrecio() {
		driver.findElement(localizadorInputUnidades).sendKeys("0");
		if(driver.findElement(localizadorErrorUnidades).isEnabled()) {
			driver.findElement(localizadorSelector).click();
			driver.findElement(localizadorInputPeso).sendKeys("0.09");
			driver.findElement(localizadorErrorPeso).isEnabled();
		}
	}
	
	private void testNombreYDescripcion() {
		if(driver.findElement(localizadorTituloRegistro).isDisplayed()) {
			driver.findElement(localizadorInputNombre).sendKeys("No se me ocurre nada para el nombre del producto");
			if(driver.findElement(localizadorMensajeError1).isDisplayed()) {
				driver.findElement(localizadorDescripcion).sendKeys("Contadordecaracteres.com es un contador automático de caracteres y palabras en un texto. Solo colocque el cursor dentro de la caja de textos y comienze a escribir y el contador de caracteres comenzara a contar sus palabras y caracteres a medida de que usted vaya escribiendo.\r\n"
						+ "\r\n"
						+ "También puede copiar y pegar bloques de texto de un documento que tengas ya escrito. Solo pegue el texto dentro de la caja del contador y rápidamente se mostrara la cantidad de caracteres y palabras que contenga el texto pegado.\r\n"
						+ "\r\n"
						+ "Esta herramienta es útil para sus tweets en Twitter, y también para una multitud de otras aplicaciones. El límite de caracteres en twitter es de 140. Un mensaje de texto a tu celular el límite es 160. Anuncios de google son 25 caracteres para el título, 70 para el cuerpo del anuncio y 35 para la url que se muestra.\r\n"
						+ "\r\n"
						+ "El contador también es de gran ayuda a reporteros, para tareas escolares, escritores freelance, preparación de resume. También es muy útil para marketing, promociones, diseño, etc");
				driver.findElement(localizadorContadorCaracteres).isDisplayed();	
			}	
		}
	}
	@After
	public void tearDown() {
		//driver.quit();
	}

}
