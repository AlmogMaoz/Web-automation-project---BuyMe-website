package Pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

import static Test.Main.test;

public class HomeScreen {
    private final WebDriver driver;

    @FindBy(xpath = ".//*[@id='ember585_chosen']")
    private WebElement priceDropDown;

    @FindBy(xpath = ".//*[@id='ember585_chosen']/div/ul/li[4]")
    private WebElement priceDropDownChoice;

    @FindBy(xpath = ".//*[@id='ember600_chosen']")
    private WebElement areaDropDown;

    @FindBy(xpath = ".//*[@id='ember600_chosen']/div/ul/li[6]")
    private WebElement areaDropDownChoice;


    @FindBy(xpath = ".//*[@id='ember609_chosen']/a")
    private WebElement categoryDropDown;

    @FindBy(xpath = ".//*[@id='ember609_chosen']/div/ul/li[4]")
    private WebElement categoryDropDownChoice;

    @FindBy(xpath = ".//*[@id='ember637']/button")
    private WebElement searchButton;


    public HomeScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    private void clickPriceDropDown(){  //handling price down drop menu
        priceDropDown.click();
        priceDropDownChoice.click();
    }

    private void clickAreaDropDown(){  //handling area drop down menu
        areaDropDown.click();
        areaDropDownChoice.click();
    }

    private void clickCategoryDropDown(){  //handling category drop down menu
        categoryDropDown.click();
        categoryDropDownChoice.click();
    }

    private void clickSearchButton(){
        searchButton.click();
    }

    public void homeScreenSelect() throws InterruptedException, IOException { //used elements are here to be called easily from main

        Thread.sleep(3000);   //try ByAngular to avoid using thread sleep
        this.clickPriceDropDown();
        test.log(Status.INFO, "After click and choose price drop down");
        test.fail("homescreen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "/Users/AlmogMaoz/Desktop/2")).build());
        this.clickAreaDropDown();
        test.log(Status.INFO, "After click and choose area drop down");
        this.clickCategoryDropDown();
        test.log(Status.INFO, "After click and choose category drop down");
        this.clickSearchButton();
        test.log(Status.INFO, "After click search button");

    }

    private String takeScreenShot(WebDriver driver, String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }

}