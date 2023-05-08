package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.DriverUtils;
import utils.PageUtils;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class StepDefinitions extends DriverUtils {
    WebDriver driver;
    PageUtils pageUtils = new PageUtils();
    DriverUtils driverUtils = new DriverUtils();

    @Given("I navigate to {string}")
    public void i_navigate_to(String url) {
        driver = driverUtils.getDriver();
        driver.get(url);
        WebElement consentPrompt = pageUtils.findElementByCss(driver,"#consent_prompt_submit");
        pageUtils.clickElement(driver,consentPrompt);
    }

    @Then("masthead banner should display {string}")
    public void masthead_banner_should_display(String expectedBannerContent) {
        WebElement banner = pageUtils.findElementByCss(driver,".masthead__wrapper h1");
        assertEquals(banner.getText(), expectedBannerContent);
    }

    @Given("I click on required car model")
    public void i_click_on_required_car_model() {
        WebElement scroller = pageUtils.findElementByCss(driver, ".cta__scroll");
        pageUtils.clickElement(driver, scroller);

        WebElement modelsSection = pageUtils.findElementByCss(driver, "section[data-id='section-explore']");
        pageUtils.scrollElementIntoView(driver, modelsSection);

        WebElement carModel = pageUtils.findElementByCss(driver, "section[data-id='section-explore'] a[href='https://www.lexus.com.sg/en/models/ux.html']");
        pageUtils.clickElement(driver, carModel);
    }

    @Then("corresponding YouTube video should be loading in gallery")
    public void corresponding_you_tube_video_should_be_loading_in_gallery() {
        WebElement gallery = pageUtils.findElementByCss(driver, "#gallery");
        pageUtils.scrollElementIntoView(driver, gallery);
        WebElement youTubeVideo = pageUtils.findElementByCss(driver, "div[data-src='https://www.youtube.com/watch?v=eokV5IpMGig&rel=0']");
        assertNotNull(youTubeVideo);
    }

    @Given("I book a test drive for {string}")
    public void i_book_a_test_drive_for(String model) {
        WebElement carModel = pageUtils.findElementByCss(driver, "li[data-id='ux-300e']");
        pageUtils.scrollElementIntoView(driver, carModel);
    }

    @Given("I fill form for booking test drive")
    public void i_fill_form_for_booking_test_drive(DataTable dataTable) {
        List<Map<String,String>> data = dataTable.asMaps(String.class, String.class);

        WebElement bookTestDrive = pageUtils.findElementByCss(driver, "li[data-id='ux-300e'] a.mdc-button");
        pageUtils.clickElement(driver, bookTestDrive);

        WebElement firstName = pageUtils.findElementByCss(driver, "#input_first_name");
        pageUtils.sendKeysToElement(driver, firstName, data.get(0).get("FirstName"));

        WebElement lastName = pageUtils.findElementByCss(driver, "#input_last_name");
        pageUtils.sendKeysToElement(driver, lastName, data.get(0).get("LastName"));

        WebElement emailAddress = pageUtils.findElementByCss(driver, "#input_email_address");
        pageUtils.sendKeysToElement(driver, emailAddress, data.get(0).get("EmailAddress"));

        WebElement phoneNumber = pageUtils.findElementByCss(driver, "#input_phone_number");
        pageUtils.sendKeysToElement(driver, phoneNumber, data.get(0).get("PhoneNumber").split(" ")[1]);

        WebElement preferredDate = pageUtils.findElementByCss(driver, "#datepicker_preferred_date");
        pageUtils.clickElement(driver, preferredDate);
        WebElement preferredMonth = pageUtils.findElementByCss(driver, ".flatpickr-monthDropdown-months");
        pageUtils.selectDropdownByVisibleText(preferredMonth, data.get(0).get("PreferredDate").split(" ")[1]);
        WebElement preferredYear = pageUtils.findElementByCss(driver, ".numInput");
        pageUtils.sendKeysToElement(driver, preferredYear, data.get(0).get("PreferredDate").split(" ")[2]);
        String dateRequired = data.get(0).get("PreferredDate").split(" ")[0];
        WebElement preferredDay = pageUtils.findElementByXpath(driver, "//span[text()='" + dateRequired + "']");
        pageUtils.clickElement(driver, preferredDay);

        ((JavascriptExecutor) driver).executeScript("document.getElementById(\"select_preferred_time\").removeAttribute(\"hidden\")");
        WebElement preferredTime = pageUtils.findElementByCss(driver, "div[data-id ='select_preferred_time'] select");
        pageUtils.clickElement(driver, preferredTime);
        String preferredTimeInputValue = data.get(0).get("PreferredTime");
        WebElement preferredTimeInput = pageUtils.findElementByCss(driver, "div[data-id ='select_preferred_time'] [data-value = '" + preferredTimeInputValue + "']");
        pageUtils.clickElement(driver, preferredTimeInput);

        ((JavascriptExecutor) driver).executeScript("document.getElementById(\"select_preferred_sales_consultant\").removeAttribute(\"hidden\")");
        WebElement preferredConsultant = pageUtils.findElementByCss(driver, "div[data-id ='select_preferred_sales_consultant'] select");
        pageUtils.clickElement(driver, preferredConsultant);
        String preferredConsultantInputValue = data.get(0).get("PreferredConsultant");
        if (preferredConsultantInputValue != null) {
            WebElement preferredConsultantInput = pageUtils.findElementByCss(driver, "div[data-id ='select_preferred_sales_consultant'] [data-value = '" + preferredConsultantInputValue + "']");
            pageUtils.clickElement(driver, preferredConsultantInput);
        }

        ((JavascriptExecutor) driver).executeScript("document.getElementById(\"select_pax\").removeAttribute(\"hidden\")");
        WebElement numberOfPax = pageUtils.findElementByCss(driver, "div[data-id ='select_pax'] select");
        pageUtils.clickElement(driver, numberOfPax);
        String numberOfPaxInputValue = data.get(0).get("NumberOfPax");
        WebElement numberOfPaxInput = pageUtils.findElementByCss(driver, "div[data-id ='select_pax'] [data-value = '" + numberOfPaxInputValue + "']");
        pageUtils.clickElement(driver, numberOfPaxInput);

        ((JavascriptExecutor) driver).executeScript("document.getElementById(\"select_test_drive_option\").removeAttribute(\"hidden\")");
        WebElement testDriveOptions = pageUtils.findElementByCss(driver, "div[data-id ='select_test_drive_option'] select");
        pageUtils.clickElement(driver, testDriveOptions);
        String testDriveOptionInputValue = data.get(0).get("TestDriveOptions");
        WebElement testDriveOptionInput = pageUtils.findElementByXpath(driver, "//div[text()='" + testDriveOptionInputValue + "']");
        pageUtils.clickElement(driver, testDriveOptionInput);

        WebElement validDrivingLicense = pageUtils.findElementByCss(driver, "div[data-id ='checkbox_driving_license']");
        pageUtils.clickElement(driver, validDrivingLicense);

        WebElement termsAndConditions = pageUtils.findElementByCss(driver, "div[data-id ='checkbox_terms_conditions']");
        pageUtils.clickElement(driver, termsAndConditions);

        WebElement privacyPolicy = pageUtils.findElementByCss(driver, "div[data-id ='checkbox_privacy_policy']");
        pageUtils.clickElement(driver, privacyPolicy);

        WebElement agreeToMarketing = pageUtils.findElementByCss(driver, "div[data-id ='checkbox_marketing']");
        pageUtils.clickElement(driver, agreeToMarketing);
    }

    @Then("submit button should be enabled")
    public void submit_button_should_be_enabled() {
        WebElement submitButton = pageUtils.findElementByCss(driver, "button.mdc-button");
        Assert.assertFalse(pageUtils.isButtonDisabled(submitButton));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
