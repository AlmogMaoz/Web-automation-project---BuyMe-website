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


public class SenderAndReceiverScreen {


    private final WebDriver driver;

    @FindBy(id = "ember1055")
    private WebElement toWhoButton;

    @FindBy(id = "ember1058")
    private WebElement receiverNameInput;

    @FindBy(id = "sender-name")
    private WebElement senderNameInput;

    @FindBy(xpath = ".//*[@id=\"msg\"]")
    private WebElement blessingInputField;

    @FindBy(xpath = ".//input[@type='file']")
    private WebElement uploadPictureButton;

    @FindBy(xpath = ".//*[@id=\'ember1060_chosen\']")
    private WebElement eventDropDown;

    @FindBy(xpath = ".//*[@id=\'ember1060_chosen\']/div/ul/li[3]")
    private WebElement dropDownThankYou;

    @FindBy(xpath = ".//*[@id=\"ember1055\"]/div[2]/div[3]/div/div[1]/label[1]")
    private WebElement sendNowRadioButton;

    @FindBy(xpath = ".//*[@id=\"ember1055\"]/div[2]/div[4]/div/div[1]/div[2]/div/button")
    private WebElement emailGiftcardButton;
    //*[@id="ember1526"]
    @FindBy(xpath = "//input[@type=\'email\']")
    private WebElement emailGiftcardInput;

    @FindBy(xpath = ".//*[@id=\"ember1055\"]/div[2]/div[4]/div/div[3]/div/div[2]/button[2]")
    private WebElement emailGiftCardSaveButton;

    @FindBy (xpath = ".//*[@id=\"ember1055\"]/div[2]/div[5]/button")
    private WebElement goToPaymentButton;

    public SenderAndReceiverScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    private void clickToWhoButton(){  //clicking receiver radio button
        toWhoButton.click();
    }

    private void setReceiverNameInputField(){  //writing to receiver name input field

        receiverNameInput.sendKeys("Receiver Examplename");
    }
    private void setSenderNameInput(){  //writing to sender name input field
        senderNameInput.clear();
        senderNameInput.sendKeys("Sender Examplename");
    }

    private void setBlessingInputField(){  //writing to input field
        blessingInputField.sendKeys("A little something from me to you");
    }

    private void setPictureUpload(){  //uploading picture
        uploadPictureButton.sendKeys("/Users/AlmogMaoz/Desktop/218519670-animals-wallpapers.jpg");
    }

    private void setEventDropDown(){ //handling event drop down
        eventDropDown.click();
        dropDownThankYou.click();
    }

    public void senderAndReceiverSelect() throws IOException { //used elements are here to be called easily from main

        this.clickToWhoButton();
        test.log(Status.INFO, "After To who radio press press");
        this.setReceiverNameInputField();
        test.log(Status.INFO, "After receiver input field is entered");
        this.setSenderNameInput();
        test.log(Status.INFO, "After sender name input field is entered");
        test.fail("senderreceiverscreen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "/Users/AlmogMaoz/Desktop/4")).build());
        this.setBlessingInputField();
        test.log(Status.INFO, "After blessing input field is entered");
        this.setPictureUpload();
        test.log(Status.INFO, "After setting the picture upload");
        this.setEventDropDown();
        test.log(Status.INFO, "After picking event from drop down");
        blessingInputField.clear();    //field gets changed after picking event, so clearing it to re-input
        test.log(Status.INFO, "After clearing blessing input field");
        this.setBlessingInputField();  //re-input blessing into field
        test.log(Status.INFO, "After re-input of blessing field");
        sendNowRadioButton.click();
        test.log(Status.INFO, "After radio button press to send now");
        emailGiftcardButton.click();
        test.log(Status.INFO, "After picking email");
        emailGiftcardInput.sendKeys("giftcardreceiver@gmail.com");
        test.log(Status.INFO, "After entering email");
        emailGiftCardSaveButton.click();
        test.log(Status.INFO, "After clicking save button for gift card");
        goToPaymentButton.click();
        test.log(Status.INFO, "After clicking go to payment button");

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
