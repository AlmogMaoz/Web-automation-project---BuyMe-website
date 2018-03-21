package Pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static Test.Main.test;


public class ChooseGiftScreen{


    private final WebDriver driver;


    @FindBy(id = "ember965")
    private WebElement chooseBusiness;

    @FindBy(id = "ember1020")
    private WebElement businessPurchaseButton;


    public ChooseGiftScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    private void clickChooseBusiness(){ //clicking on the element and asserting
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(chooseBusiness));  //waiting for our element to be visible to assert URL

        String URL = driver.getCurrentUrl();
        Assert.assertNotEquals(URL, "https://buyme.co.il/");  //asserting
        test.log(Status.INFO, "After website URL assertion");
        chooseBusiness.click();
    }

    private void clickBusinessPurchaseButton(){
        businessPurchaseButton.click();
    }

    public void chooseGiftScreenSelect() throws IOException { //used elements are here to be called easily from main

        this.clickChooseBusiness();
        test.log(Status.INFO, "After business picked");
        test.fail("giftscreen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "/Users/AlmogMaoz/Desktop/3")).build());
        this.clickBusinessPurchaseButton();
        test.log(Status.INFO, "After business button clicked");


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
