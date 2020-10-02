import org.openqa.selenium.By.ById;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		ChromeDriver chromeDriver = new ChromeDriver();
		try {
			chromeDriver.get("https://facebook.com");
			//System.out.println(chromeDriver.getTitle());
			//System.out.println(chromeDriver.getCurrentUrl());
			//System.out.println(chromeDriver.getPageSource());
			WebElement inputEmail =  chromeDriver.findElement(By.id("email"));
			WebElement inputSenha =  chromeDriver.findElement(By.id("pass"));
			WebElement buttonLogin =  chromeDriver.findElement(By.id("loginbutton"));
			inputEmail.sendKeys("xerxesfalcao90@gmail.com");
			inputSenha.sendKeys("cabo090890");
			buttonLogin.click();
			
		}finally {
			//chromeDriver.close(); // fecha a aba atual do browser que está aberta
			//chromeDriver.quit(); // fecha todas as abas que estiverem abertas 
		}

	}

}
