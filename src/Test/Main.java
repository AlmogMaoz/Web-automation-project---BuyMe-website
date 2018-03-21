package Test;

import Pages.ChooseGiftScreen;
import Pages.HomeScreen;
import Pages.RegistrationScreen;
import Pages.SenderAndReceiverScreen;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Main{

    private static WebDriver driver;
    private RegistrationScreen objRegistrationScreen;
    private HomeScreen objHomeScreen;
    private ChooseGiftScreen objChooseGiftScreen;
    private SenderAndReceiverScreen objSenderAndReceiverScreen;
    private static ExtentReports extent;
    public static ExtentTest test;

    @BeforeClass
    public static void beforeClass() {

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("/Users/AlmogMaoz/Desktop/extentreport.html");

        // choose to append each test
        htmlReporter.setAppendExisting(true);
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest("BuyMeTest");
        // log results
        test.log(Status.INFO, "Before class");

        String browserType = xmlGrab("BrowserType");  //goes to xml file to check browsertype tag
        switch (browserType){                         //initiates drivers for browsers to cover whichever one is being used
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "/Users/AlmogMaoz/Downloads/chromedriver");
                driver = new ChromeDriver();
                break;
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", "/Users/AlmogMaoz/Downloads/geckodriver");
                driver = new FirefoxDriver();
                break;
            case "Safari":
                System.setProperty("webdriver.safari.driver", "Users/AlmogMaoz/Applications/safaridriver");
                driver = new SafariDriver();
                break;
        }
        driver.navigate().to(xmlGrab("URL"));
        test.log(Status.INFO, "Navigate to page");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }



    @Test
    public void myTest() throws InterruptedException, IOException {

        objRegistrationScreen = new RegistrationScreen(driver);
        objRegistrationScreen.registerToBuyMe();

        objHomeScreen = new HomeScreen(driver);
        objHomeScreen.homeScreenSelect();

        objChooseGiftScreen = new ChooseGiftScreen(driver);
        objChooseGiftScreen.chooseGiftScreenSelect();

        objSenderAndReceiverScreen = new SenderAndReceiverScreen(driver);
        objSenderAndReceiverScreen.senderAndReceiverSelect();
    }





    @AfterClass
    public static void afterClass() {
        test.log(Status.INFO, "After test");
        extent.flush();
    }


    private static String xmlGrab (String thisClass) {

        // Get xml file
        File xmlFile = new File("MyXML.xml");

        try{

            // Prepare XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            return doc.getElementsByTagName(thisClass).item(0).getTextContent();


        } catch (Exception e){
            e.printStackTrace();
        }

        return thisClass;


    }

}
