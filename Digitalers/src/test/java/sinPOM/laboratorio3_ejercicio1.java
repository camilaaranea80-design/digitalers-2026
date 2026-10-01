package sinPOM;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class laboratorio3_ejercicio1 {
     
	WebDriver Driver;
	WebDriverWait Wait;
	
	@BeforeSuite
	public void setUp() {
		Driver = new ChromeDriver();
		Wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
}

	@BeforeTest
	public void irUrl() {
		Driver.get("https://automationexercise.com/login");
	}	
	
	@BeforeClass
	public void maxVentana () {
		Driver.manage().window().maximize();
		Driver.manage().deleteAllCookies();
	}
	
	@BeforeMethod
	public void mensajeDeInicio() {
		System.out.println("Antes del test (Method)");
	}

	@Test
	public void registroUsuario() {
		WebElement nombre = Driver.findElement(By.name("name"));
		WebElement email = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa='signup-email']")));
		WebElement botonRegistro = Driver.findElement(By.xpath("//button[@data-qa='signup-button']"));

		nombre.sendKeys("Estudiante Digitalers");
		email.sendKeys("correo-invalido");

		String urlAntes = Driver.getCurrentUrl();

		botonRegistro.click();

		Assert.assertEquals(Driver.getCurrentUrl(), urlAntes);
		Assert.assertTrue(nombre.isDisplayed());
		String estoEsUnTexto = null;
		Assert.assertNull(estoEsUnTexto);
	}
	
	@Test
	public void loginUsuario() {
		System.out.println("Esta sería la prueba 1");
	}

	@Test
	public void loginUsuario2() {
		System.out.println("Esta sería la prueba 2");
	}
	
	@Test
	public void escribirYLeerTexto() throws IOException {

	    File carpeta = new File("Evidencias");
	    if (!carpeta.exists() && !carpeta.mkdirs()) {
	        throw new IOException("No se pudo crear Evidencias");
	    }

	    File archivo = new File(carpeta, "nota.txt");
	    try (FileWriter escritor = new FileWriter(archivo)) {
	        escritor.write("Evidencia de la unidad 22");
	    }

	    try (FileReader lector = new FileReader(archivo)) {
	        int caracter;
	        while ((caracter = lector.read()) != -1) {

	            System.out.print((char) caracter);
	        }
	    }
	}
	
	@AfterMethod

	public void screenshot() throws IOException {
	    if (Driver == null) {
	        return;
	    }

	    File screen = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);

	    File imageFile = new File("Evidencias/Test.png");

	    FileUtils.copyFile(screen, imageFile);

	    System.out.println(imageFile.getAbsolutePath());

	}

	@AfterMethod
	public void mensajeDeFin() {
		System.out.println("Despues del test (Method)");
	}

	@AfterClass
	public void finDeTest() {
		System.out.println("Fin de la prueba");
	}

	@AfterTest
	public void cerrarNavegador() {
		if(Driver != null) {
			Driver.quit();
		}

	}

	@AfterSuite
	public void finSuite() {
		System.out.println("Fin de la suite de Pruebas.");
	}
}
  
