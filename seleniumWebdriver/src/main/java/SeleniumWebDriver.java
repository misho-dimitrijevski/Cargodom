import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumWebDriver {
    private static WebDriver driver;

    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    public static String navigateTo() {
        driver.get("http://18.156.17.83:9095/");
        return driver.getCurrentUrl();
    }

    public static void goToBaseUrl() {
        driver.get("http://18.156.17.83:9095/");

    }


    public static String getMessage(String xPath) {
        WebElement messageLbl = driver.findElement(By.xpath(xPath));
        return messageLbl.getText();
    }

    public static void register(boolean isTransporter, String clientType, String firstName, String lastName,
                                String companyName, String address, String city, String zipCode, String country,
                                String taxNumber, String phoneNumber, String eMail, String password,
                                String confirmPassword) {
        if (isTransporter) {
            registerAsTransporter(firstName, lastName, companyName,
                    address, city, zipCode, country, taxNumber, phoneNumber, eMail, password, confirmPassword);
        } else {
            registerAsLookingForTransporter(clientType, firstName, lastName, companyName,
                    address, city, zipCode, country, taxNumber, phoneNumber, eMail, password, confirmPassword);
        }
    }

    public static void registerAsTransporter(String firstName, String lastName, String companyName,
                                             String address, String city, String zipCode, String country,
                                             String taxNumber, String phoneNumber,
                                             String eMail, String password, String confirmPassword) {
        WebElement registerBtn = driver.findElement(By.xpath("/html/body/div[1]/nav/div[3]/ul/li[3]/a/span"));
        registerBtn.click();

        try {
            Thread.sleep(1000);
        } catch (
                InterruptedException exception) {
        }

        WebElement iAmTransporterBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/button"));
        iAmTransporterBtn.click();

        WebElement firstNameTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[1]/div[1]/div[2]/input"));
        firstNameTxt.sendKeys(firstName);

        WebElement lastNameTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[1]/div[3]/div[2]/input"));
        lastNameTxt.sendKeys(lastName);

        WebElement companyNameTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[1]/div[5]/div[2]/input"));
        companyNameTxt.sendKeys(companyName);

        WebElement addressTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[1]/div[7]/div[2]/input"));
        addressTxt.sendKeys(address);

        WebElement cityTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[1]/div[9]/div[2]/input"));
        cityTxt.sendKeys(city);

        WebElement postalCodeTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[1]/div[11]/div[2]/input"));
        postalCodeTxt.sendKeys(zipCode);

        WebElement countryBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[1]/div[13]/div[2]/country-selector/div/div[1]/span"));
        countryBtn.click();

        WebElement countryDropDown = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[1]/div[13]/div[2]/country-selector/div/input[1]"));
        countryDropDown.sendKeys(country);
        countryDropDown.sendKeys(Keys.ENTER);

        WebElement taxNumberTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[1]/div[15]/div[2]/input"));
        taxNumberTxt.sendKeys(taxNumber);

        WebElement phoneNumberTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[1]/div[16]/div[2]/input"));
        phoneNumberTxt.sendKeys(phoneNumber);

        WebElement eMailTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[2]/div[2]/input"));
        eMailTxt.sendKeys(eMail);

        WebElement passwordTxt = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordTxt.sendKeys(password);

        WebElement passwordConfirmationTxt = driver.findElement(By.xpath("//*[@id=\"confirmPassword\"]"));
        passwordConfirmationTxt.sendKeys(confirmPassword);

        WebElement acceptTermsBtn = driver.findElement(By.xpath("//*[@id=\"acceptTerms\"]"));
        acceptTermsBtn.click();

        WebElement registerMeInBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/form/div[10]/input"));
        registerMeInBtn.click();

        try {
            Thread.sleep(1000);
        } catch (
                InterruptedException exception) {
        }
    }

    public static void registerAsLookingForTransporter(String clientType, String firstName, String lastName, String companyName,
                                                       String address, String city, String zipCode, String country,
                                                       String taxNumber, String phoneNumber, String eMail, String password, String confirmPassword) {

        if (clientType == "Физичко лице") {
            registerAsPerson(clientType, firstName, lastName, address, city, zipCode, country,
                    phoneNumber, eMail, password, confirmPassword);
        } else {
            registerAsCompany(clientType, firstName, lastName, companyName, address, city, zipCode, country,
                    taxNumber, phoneNumber, eMail, password, confirmPassword);
        }
    }

    public static void registerAsPerson(String clientType, String firstName, String lastName,
                                        String address, String city, String zipCode, String country,
                                        String phoneNumber, String eMail, String password,
                                        String confirmPassword) {
        WebElement registrationFormBtn = driver.findElement(By.xpath("/html/body/div[1]/nav/div[3]/ul/li[3]/a/span"));
        registrationFormBtn.click();

        try {
            Thread.sleep(1000);
        } catch (
                InterruptedException exception) {
        }

        WebElement lookingForAnTransporterBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[1]/button"));
        lookingForAnTransporterBtn.click();

        Select typeOfUser = new Select(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[1]/div[2]/select")));
        typeOfUser.selectByVisibleText(clientType);

        WebElement firstNameTxt = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        firstNameTxt.sendKeys(firstName);

        WebElement lastNameTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[4]/div[2]/input"));
        lastNameTxt.sendKeys(lastName);

        WebElement addressTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[1]/div[2]/input"));
        addressTxt.sendKeys(address);

        WebElement cityTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[3]/div[2]/input"));
        cityTxt.sendKeys(city);

        WebElement postalCodeTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[5]/div[2]/input"));
        postalCodeTxt.sendKeys(zipCode);

        WebElement countryBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[7]/div[2]/country-selector/div/div[1]/span"));
        countryBtn.click();

        if (!country.isEmpty()) {
            WebElement countryTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[7]/div[2]/country-selector/div/input[1]"));
            countryTxt.sendKeys(country);
            countryTxt.sendKeys(Keys.ENTER);
        }

        WebElement phoneNumberTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[9]/div[2]/input"));
        phoneNumberTxt.sendKeys(phoneNumber);

        WebElement eMailTxt = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        eMailTxt.sendKeys(eMail);

        WebElement passwordTxt = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordTxt.sendKeys(password);

        WebElement passwordRepeatTxt = driver.findElement(By.xpath("//*[@id=\"confirmPassword\"]"));
        passwordRepeatTxt.sendKeys(confirmPassword);

        WebElement termsAndConditionsCb = driver.findElement(By.xpath("//*[@id=\"acceptTerms\"]"));
        termsAndConditionsCb.click();

        WebElement registerMeBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[15]/input"));
        registerMeBtn.click();

        try {
            Thread.sleep(1000);
        } catch (
                InterruptedException exception) {
        }
    }


    public static void registerAsCompany(String clientType, String firstName, String lastName,
                                         String companyName, String address, String city, String zipCode, String country,
                                         String taxNumber, String phoneNumber, String eMail, String password,
                                         String confirmPassword) {

        WebElement registrationFormBtn = driver.findElement(By.xpath("/html/body/div[1]/nav/div[3]/ul/li[3]/a/span"));
        registrationFormBtn.click();

        try {
            Thread.sleep(1000);
        } catch (
                InterruptedException exception) {
        }

        WebElement lookingForAnTransporterBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[1]/button"));
        lookingForAnTransporterBtn.click();

        try {
            Thread.sleep(1000);
        } catch (
                InterruptedException exception) {
            exception.printStackTrace();
        }

        Select typeOfUserCompanyDdl = new Select(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[1]/div[2]/select")));
        typeOfUserCompanyDdl.selectByVisibleText(clientType);

        WebElement firstNameTxt = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        firstNameTxt.sendKeys(firstName);

        WebElement lastNameTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[4]/div[2]/input"));
        lastNameTxt.sendKeys(lastName);

        WebElement companyNameTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[1]/div[2]/input"));
        companyNameTxt.sendKeys(companyName);

        WebElement addressTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[3]/div[2]/input"));
        addressTxt.sendKeys(address);

        WebElement cityTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[5]/div[2]/input"));
        cityTxt.sendKeys(city);

        WebElement postalCodeTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[7]/div[2]/input"));
        postalCodeTxt.sendKeys(zipCode);

        WebElement countryBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[9]/div[2]/country-selector/div/div[1]/span"));
        countryBtn.click();

        WebElement countryTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[9]/div[2]/country-selector/div/input[1]"));
        countryTxt.sendKeys(country);
        countryTxt.sendKeys(Keys.ENTER);

        WebElement taxNumberTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[11]/div[2]/input"));
        taxNumberTxt.sendKeys(taxNumber);

        WebElement phoneNumberTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[12]/div[2]/input"));
        phoneNumberTxt.sendKeys(phoneNumber);

        WebElement eMailTxt = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        eMailTxt.sendKeys(eMail);

        WebElement passwordTxt = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordTxt.sendKeys(password);

        WebElement passwordRepeatTxt = driver.findElement(By.xpath("//*[@id=\"confirmPassword\"]"));
        passwordRepeatTxt.sendKeys(confirmPassword);

        WebElement termsAndConditionsCb = driver.findElement(By.xpath("//*[@id=\"acceptTerms\"]"));
        termsAndConditionsCb.click();

        WebElement registerMeBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[15]/input"));
        registerMeBtn.click();

        try {
            Thread.sleep(1000);
        } catch (
                InterruptedException exception) {
        }
    }

    public static void logMeIn(String eMail, String password) {
        WebElement logInBtn = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        logInBtn.click();
        WebElement usernameLbl = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div[2]/form/div[1]/label"));
        WebElement usernameTxt = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        usernameTxt.sendKeys(eMail);
        WebElement passwordTxt = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordTxt.sendKeys(password);
        WebElement rememberMeBtn = driver.findElement(By.xpath("//*[@id=\"rememberMe\"]"));
        rememberMeBtn.click();

        WebElement logMeInBtn = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div[2]/form/button"));
        logMeInBtn.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

    }

    public static void logMeOut() {
        WebElement logMeOutBtn = driver.findElement(By.xpath("//*[@id=\"logout2\"]/span"));
        logMeOutBtn.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    public static void supportRequest(String subject, String question) {
        WebElement createSupportRequestBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/ul/li[6]/a/span[2]"));
        createSupportRequestBtn.click();

        WebElement questionSubjectTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div/div/div[3]/div[1]/input"));
        questionSubjectTxt.sendKeys(subject);

        WebElement questionTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div/div/div[3]/div[2]/textarea"));
        questionTxt.sendKeys(question);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement submitBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div/div/div[3]/button"));
        submitBtn.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }


    public static void createTransportRequest(String title, String category, String description,
                                              String length, String width, String height, String weight,
                                              boolean shouldChoosePaymentMethod) {
        WebElement createRequestBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/ul/li[3]/a/span[2]"));
        createRequestBtn.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement titleTxt = driver.findElement(By.xpath("//*[@id=\"newRequestForm\"]/div/div[2]/div[2]/input"));
        titleTxt.sendKeys(title);

        Select categoryDdl = new Select(driver.findElement(By.xpath("//*[@id=\"field_y\"]")));
        categoryDdl.selectByVisibleText(category);

        WebElement fromAddressTxt = driver.findElement(By.xpath("//*[@id=\"newRequestForm\"]/div/div[4]/div[2]/place-search-field/input"));
        fromAddressTxt.sendKeys("Partizanska 50, Skopje, North Macedonia");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> elements = driver.findElements(By.className("pac-item"));

        for (WebElement element : elements) {
            if (element.getAttribute("innerHTML").contains("Partizanska 50")) {
                element.click();
            }
        }

        WebElement toAddressTxt = driver.findElement(By.xpath("//*[@id=\"newRequestForm\"]/div/div[5]/div[2]/place-search-field/input"));
        toAddressTxt.sendKeys("Partizanska 60, Skopje, North Macedonia");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elements = driver.findElements(By.className("pac-item"));

        for (WebElement element : elements) {
            if (element.getAttribute("innerHTML").contains("Partizanska 60")) {
                element.click();
            }
        }

        WebElement descriptionTxt = driver.findElement(By.xpath("//*[@id=\"newRequestForm\"]/div/div[10]/div/div[1]/div[2]/textarea"));
        descriptionTxt.sendKeys(description);

        WebElement lengthTxt = driver.findElement(By.xpath("//*[@id=\"newRequestForm\"]/div/div[10]/div/div[3]/div[2]/div[1]/div/div[2]/input"));
        lengthTxt.sendKeys(length);

        WebElement widthTxt = driver.findElement(By.xpath("//*[@id=\"newRequestForm\"]/div/div[10]/div/div[3]/div[2]/div[2]/div/div[2]/input"));
        widthTxt.sendKeys(width);

        WebElement heightTxt = driver.findElement(By.xpath("//*[@id=\"newRequestForm\"]/div/div[10]/div/div[3]/div[2]/div[3]/div/div[2]/input"));
        heightTxt.sendKeys(height);

        WebElement weightTxt = driver.findElement(By.xpath("//*[@id=\"newRequestForm\"]/div/div[10]/div/div[3]/div[2]/div[4]/div/div[2]/input"));
        weightTxt.sendKeys(weight);

        WebElement temperatureCb = driver.findElement(By.xpath("//*[@id=\"hasFreezer\"]"));
        temperatureCb.click();

        if (shouldChoosePaymentMethod) {
            WebElement paymentCb = driver.findElement(By.xpath("//*[@id=\"cacheDelivery\"]"));
            paymentCb.click();
        }
        WebElement sendRequestBtn = driver.findElement(By.xpath("//*[@id=\"newRequestForm\"]/div/div[14]/input"));
        sendRequestBtn.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void findRequest(String fromCountry, String toCountry, String category) {
        WebElement fromCountryBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/request-search-list/div[1]/div[2]/request-search/div/div/div[2]/div[1]/div/div[1]/country-selector/div/div[1]/span"));
        fromCountryBtn.click();

        WebElement fromCountryDdl = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/request-search-list/div[1]/div[2]/request-search/div/div/div[2]/div[1]/div/div[1]/country-selector/div/input[1]"));
        fromCountryDdl.sendKeys(fromCountry);
        fromCountryDdl.sendKeys(Keys.ENTER);

        WebElement toCountryBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/request-search-list/div[1]/div[2]/request-search/div/div/div[2]/div[1]/div/div[2]/country-selector/div/div[1]/span"));
        toCountryBtn.click();

        WebElement toCountryDdl = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/request-search-list/div[1]/div[2]/request-search/div/div/div[2]/div[1]/div/div[2]/country-selector/div/input[1]"));
        toCountryDdl.sendKeys(toCountry);
        toCountryDdl.sendKeys(Keys.ENTER);

        Select categoryDdl = new Select(driver.findElement(By.xpath("//*[@id=\"field_y\"]")));
        categoryDdl.selectByVisibleText(category);

        WebElement searchBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/request-search-list/div[1]/div[2]/request-search/div/div/div[3]/a/span[2]"));
        searchBtn.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement openRequest = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/request-search-list/div[2]/request-list/div[2]/div[2]/table/tbody/tr[1]/td[1]/a"));
        openRequest.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sendOffer(String price, String offerValidity) {
                WebElement createOfferBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div[1]/div[5]/div/div[2]/div/button"));
        createOfferBtn.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement priceTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/form/div/div[2]/div[2]/table/tbody/tr/td[5]/input"));
        priceTxt.sendKeys(price);

        WebElement offerValidityTxt = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/form/div/div[4]/div/div[1]/div[3]/input"));
        offerValidityTxt.sendKeys(offerValidity);

        WebElement toCreateOfferBtn = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/form/div/div[5]/button"));
        toCreateOfferBtn.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement toCreateOfferConfirmBtn = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/button[1]"));
        toCreateOfferConfirmBtn.click();
    }
    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static void end() {
        driver.quit();
    }
}
