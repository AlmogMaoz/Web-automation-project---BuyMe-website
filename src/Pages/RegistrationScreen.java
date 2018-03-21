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
import java.util.Random;

import static Test.Main.test;


public class RegistrationScreen{
    private final WebDriver driver;


    @FindBy(xpath = "//*[@id=\"ember510\"]/div/header[1]/div[2]/ul/li[5]/a")
    private WebElement enterRegisterButton;

    @FindBy(xpath = "//*[@id=\"auth-modal\"]/div/span")
    private WebElement notRegisteredButton;

    @FindBy(xpath = "//*[@id=\"ember858\"]")
    private WebElement firstNameInput;

    @FindBy(xpath = "//*[@id=\"ember859\"]")
    private WebElement emailInput;

    @FindBy(id = "valPass")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"ember861\"]")
    private WebElement passwordValidationInput;

    @FindBy(xpath = "//*[@id=\"ember855\"]/div[5]/div/label")
    private WebElement checkBoxAgree;

    @FindBy(xpath = "//*[@id=\"ember855\"]/button")
    private WebElement registerToBuyMeButton;

    public RegistrationScreen(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void clickEnterRegisterButton(){  //clicking enter registration button
        enterRegisterButton.click();
    }

    private void clickNotRegisteredButton(){ //clicking not registered button

        notRegisteredButton.click();
    }

    private void setFirstName(String loginFirstName){  //setting first name in input field

        firstNameInput.sendKeys(loginFirstName);
    }

    private void setEmail(String loginEmail){  //setting email with changing numbers for re-testing
        Random randomEmail = new Random();         //create a random number to come before the email to generate a unique one every time you sign up to allow reuse of the test
        int randomInt = randomEmail.nextInt(5000);
        emailInput.sendKeys(randomInt + loginEmail);
    }

    private void setPassword(String loginPassword){  //setting password in input field

        passwordInput.sendKeys(loginPassword);
    }

    private void setValidationPassword(String loginPassword){ //setting validation password in input field

        passwordValidationInput.sendKeys(loginPassword);
    }



    public void registerToBuyMe() throws IOException {  //used elements are here to be called easily from main

        this.clickEnterRegisterButton();
        test.log(Status.INFO, "After to register button click");
        this.clickNotRegisteredButton();
        test.log(Status.INFO, "After not registered button click");
        setFirstName(Constants.USER_NAME);
        test.log(Status.INFO, "After set first name");
        test.fail("registration", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "/Users/AlmogMaoz/Desktop/1")).build());
        setEmail(Constants.USER_EMAIL);
        test.log(Status.INFO, "After set email");
        setPassword(Constants.USER_PASSWORD);
        test.log(Status.INFO, "After set password");
        setValidationPassword(Constants.USER_PASSWORD);
        test.log(Status.INFO, "After set validation password");
        checkBoxAgree.click();
        test.log(Status.INFO, "After I agree radio button press");
        registerToBuyMeButton.click();
        test.log(Status.INFO, "After register button click");

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
