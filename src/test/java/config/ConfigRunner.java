package config;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class ConfigRunner {
    private final WebDriverConfig config;

    public ConfigRunner() {
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        createDriverBrowser();
        createDriver();
    }

    private void createDriver() {
        if (config.getRemoteURL() != null) {
            Configuration.remote = config.getRemoteURL().toString();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));

            Configuration.browserCapabilities = capabilities;
        }
    }

    public void createDriverBrowser() {
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browser = config.getBrowserName();
        switch (config.getBrowserName()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                new FirefoxDriver();
            }
            default -> throw new RuntimeException("No such driver");
        }
    }
}