package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.ImmutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
		
	public Base() {
		
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		
		try {
			FileInputStream dataFis = new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
		
		
			
		if(browserName.equalsIgnoreCase("chrome")) {
			
//			System.setProperty("otel.traces.exporter", "jaeger");
//			System.setProperty("otel.exporter.jaeger.endpoint", "http://localhost:14250");
//			System.setProperty("otel.resource.attributes", "service.name=selenium-java-client");

			ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");

			try {
				 driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			// remote webdriver
//			ChromeOptions chromeOptions = new ChromeOptions();
//			chromeOptions.setCapability("browserVersion", "113");
		
			
			//WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			//dc.setBrowserName("chrome");
			//WebDriverManager.chromedriver().setup();
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
			
		}else if(browserName.equalsIgnoreCase("safari")) {
			
			driver = new SafariDriver();
			
		}
	
		
		
////		try {
////			driver = new RemoteWebDriver(new URL("http://localhost:4444"),dc);
////		} catch (MalformedURLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
//		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		
		return driver;
		
	}
	
	
	
	
	
}
