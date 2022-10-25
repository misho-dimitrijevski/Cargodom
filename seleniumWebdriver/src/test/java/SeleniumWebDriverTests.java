import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumWebDriverTests {
    String email = "mishod_" + System.currentTimeMillis() + "@gmail.com";
    String password = "12345";

    @BeforeClass
    public static void beforeClass() {
        SeleniumWebDriver.setup();
    }

    @BeforeMethod
    public static void beforeMethod() {
        SeleniumWebDriver.goToBaseUrl();
    }

    @Test(priority = 1, enabled = true)
    public void testForVerifyingUserReachedHomePage() {
        String actualUrl = SeleniumWebDriver.navigateTo();
        Assert.assertEquals(actualUrl, "http://18.156.17.83:9095/");
    }

    @Test(priority = 2, enabled = true)
    public void testForVerifyingUserIsSuccessfullyRegistered() {
        SeleniumWebDriver.registerAsPerson(
                "Физичко лице",
                "Misho",
                "Dimitrijevski",
                "Prolet 33",
                "Skopje",
                "1000",
                "Macedonia",
                "076555444",
                email,
                password,
                password);
        String actualRegistrationMsg = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/h3");
        Assert.assertEquals(actualRegistrationMsg, "Вашиот профил е успешно креиран! Ве молиме проверете го Вашиот e-mail за да ја завршите регистрацијата.");
    }

    @Test(priority = 3, enabled = true)
    public void testForVerifyingUserIsSuccessfullyLoggedIn() {
        SeleniumWebDriver.logMeIn(email, password);
        String actualLoggedInUsernameMsg = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div[1]/div/div/h3");
        Assert.assertEquals(actualLoggedInUsernameMsg, "Misho Dimitrijevski");
        String actualLoggedInWelcomeMsg = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div[2]/h2[1]");
        Assert.assertEquals(actualLoggedInWelcomeMsg, "Добредојдовте на cargodom.com");
        SeleniumWebDriver.logMeOut();
    }

    @Test(priority = 4, enabled = true)
    public void testForVerifyingUserIsNotSuccessfullyLoggedIn() {
        SeleniumWebDriver.logMeIn(email, "");
        String actualLoggedInWelcomeMsg = SeleniumWebDriver.getMessage("/html/body/div[1]/div/div/div[2]/div/div[1]/div");
        Assert.assertEquals(actualLoggedInWelcomeMsg, "Вашиот обид е неуспешен! Ве молиме проверете го вашето корисничко име и лозинка и обидете се повторно.");

    }

    @Test(priority = 5, enabled = true)
    public static void automatedFlowTest() {
        String email = "mishod_" + System.currentTimeMillis() + "@gmail.com";
        String password = "123456";

        // Register as "Looking for transporter"
        SeleniumWebDriver.register(
                false,
                "Физичко лице",
                "Misho",
                "Dimitrijevski",
                "",
                "Partizanska 44",
                "Skopje",
                "1000",
                "Macedonia",
                "",
                "33334444",
                email,
                password,
                password);

        //Log in
        SeleniumWebDriver.logMeIn(email, password);

        //Send support request/question
        SeleniumWebDriver.supportRequest("Hello Team", "Do you have premium features?");
        String positive = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div[2]/div/div/div/div[1]/strong");
        Assert.assertEquals(positive, "Вашиот меил е успешно испратен до одделот за корисничка поддршка!");


        //Create 3 transport requests
        SeleniumWebDriver.createTransportRequest("Fish", "Разно", "urgent",
                "1", "2", "3", "4", true);
        String positiveMsg1 = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div[2]/div/request-list-pagination/jhi-alert/div/div/div/div/pre");
        Assert.assertEquals(positiveMsg1, "Успешно е креирано ново барање");

        SeleniumWebDriver.createTransportRequest("Fruit", "Разно", "urgent",
                "2", "3", "4", "5", true);
        String positiveMsg2 = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div[2]/div/request-list-pagination/jhi-alert/div/div/div/div/pre");
        Assert.assertEquals(positiveMsg2, "Успешно е креирано ново барање");

        SeleniumWebDriver.createTransportRequest("Vegetables", "Разно", "urgent",
                "5", "6", "7", "8", true);
        String positiveMsg3 = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div[2]/div/request-list-pagination/jhi-alert/div/div/div/div/pre");
        Assert.assertEquals(positiveMsg3, "Успешно е креирано ново барање");

        //Log out
        SeleniumWebDriver.logMeOut();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Register as "Transporter"
        email = "mishod_" + System.currentTimeMillis() + "@gmail.com";
        SeleniumWebDriver.register(
                true,
                "",
                "Misho",
                "Dimitrijevski",
                "MishoTrans",
                "Partizanska 44",
                "Skopje",
                "1000",
                "Macedonia",
                "666457668990",
                "33334444",
                email,
                password,
                password);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 6, enabled = true)
    public static void negativeTest1PasswordTooShort() {
        SeleniumWebDriver.registerAsPerson(
                "Физичко лице",
                "Misho",
                "Dimitrijevski",
                "Partizanska 44",
                "Skopje",
                "1000",
                "Macedonia",
                "33334444",
                "mishod_" + System.currentTimeMillis() + "@gmail.com",
                "123",
                "123456");
        String passwordErrorMessage = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[10]/div/p[2]");
        Assert.assertEquals(passwordErrorMessage, "Вашата лозинка треба да биде од најмалку 4 карактери.");
    }

    @Test(priority = 7, enabled = true)
    public static void negativeTest2EmailNotValid() {
        SeleniumWebDriver.registerAsPerson(
                "Физичко лице",
                "Misho",
                "Dimitrijevski",
                "Partizanska 44",
                "Skopje",
                "1000",
                "Macedonia",
                "33334444",
                "mishod_" + System.currentTimeMillis() + "gmail.com",
                "123456",
                "123456");

        String emailErrorMessage = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[8]/div/p[2]");
        Assert.assertEquals(emailErrorMessage, "Вашиот e-mail не е валиден.");
    }

    @Test(priority = 8, enabled = true)
    public static void negativeTest3PasswordConfirmationTooShort() {
        SeleniumWebDriver.registerAsPerson(
                "Физичко лице",
                "Misho",
                "Dimitrijevski",
                "Partizanska 44",
                "Skopje",
                "1000",
                "Macedonia",
                "33334444",
                "mishod_" + System.currentTimeMillis() + "@gmail.com",
                "123456",
                "12");
        String passwordConfrimErrorMessage = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[12]/div/p[2]");
        Assert.assertEquals(passwordConfrimErrorMessage, "Потврдта на лозинката треба да биде од најмалку 4 карактери.");
    }

    @Test(priority = 9, enabled = true)
    public static void negativeTest4EmailNotValidAndTooShort() {
        SeleniumWebDriver.registerAsPerson(
                "Физичко лице",
                "Misho",
                "Dimitrijevski",
                "Partizanska 44",
                "Skopje",
                "1000",
                "Macedonia",
                "33334444",
                "mi",
                "123456",
                "123456");
        String eMailLenghtErrorMessageLbl = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[8]/div/p[3]");
        Assert.assertEquals(eMailLenghtErrorMessageLbl, "Вашиот e-mail треба да биде од најмалку 5 карактери.");
        String eMailFormatErrorMessageLbl = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[8]/div/p[2]");
        Assert.assertEquals(eMailFormatErrorMessageLbl, "Вашиот e-mail не е валиден.");
    }

    @Test(priority = 10, enabled = true)
    public static void negativeTest5CountryEmpty() {
        SeleniumWebDriver.registerAsPerson(
                "Физичко лице",
                "Misho",
                "Dimitrijevski",
                "Partizanska 44",
                "Skopje",
                "1000",
                "",
                "33334444",
                "mishod_" + System.currentTimeMillis() + "@gmail.com",
                "123456",
                "12345");
        String countryEmptyTxt = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[6]/div[8]/div/p");
        Assert.assertEquals(countryEmptyTxt, "Ова поле е задолжително.");
    }

    @Test(priority = 11, enabled = true)
    public static void negativeTest6WrongConfirmationPassword() {
        SeleniumWebDriver.registerAsPerson(
                "Физичко лице",
                "Misho",
                "Dimitrijevski",
                "Partizanska 44",
                "Skopje",
                "1000",
                "Macedonia",
                "33334444",
                "mishod_" + System.currentTimeMillis() + "@gmail.com",
                "123456",
                "12345");
        String confirmationPasswordLbl = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/div/div[1]/div[5]");
        Assert.assertEquals(confirmationPasswordLbl, "Вашата лозинка не соодветсвува со потврдената лозинка!");
    }

    @Test(priority = 12, enabled = true)
    public static void negativeTest7ZipCodeEmpty() {
        SeleniumWebDriver.registerAsPerson(
                "Физичко лице",
                "Misho",
                "Dimitrijevski",
                "Partizanska 44",
                "Skopje",
                "",
                "Macedonia",
                "33334444",
                "mishod_" + System.currentTimeMillis() + "@gmail.com",
                "123456",
                "12345");
        String allFields = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[15]/div/p");
        Assert.assertEquals(allFields, "Ве молиме пополнете ги сите задолжителни полиња!");
    }

    @Test(priority = 13, enabled = true)
    public static void negativeTest8PasswordAndConfirmPasswordEmpty() {
        SeleniumWebDriver.registerAsPerson(
                "Физичко лице",
                "Misho",
                "Dimitrijevski",
                "Partizanska 44",
                "Skopje",
                "1000",
                "Macedonia",
                "33334444",
                "mishod_" + System.currentTimeMillis() + "@gmail.com",
                "",
                "");
        String emptyPassword = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[10]/div/p[1]");
        Assert.assertEquals(emptyPassword, "Задолжително внесете лозинка");
        String emptyConfirmPassword = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/div/div[2]/form/div[12]/div/p[1]");
        Assert.assertEquals(emptyConfirmPassword, "Задолжително потврдете ја лозинката.");
    }

    @Test(priority = 14, enabled = true)
    public static void actualUrlAfterLogin() {
        SeleniumWebDriver.logMeIn("misho.dimitrijevski@gmail.com", "cargodom2022");
        String currentUrl = SeleniumWebDriver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://18.156.17.83:9095/client/home");
        SeleniumWebDriver.logMeOut();
    }

    @Test(priority = 15, enabled = true)
    public static void sendMsgToAdminVerification() {
        SeleniumWebDriver.logMeIn("misho.dimitrijevski@gmail.com", "cargodom2022");
        SeleniumWebDriver.supportRequest("", "");
        String emptyTitleAndQuestion = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div[2]/div/div/div/div[2]");
        Assert.assertEquals(emptyTitleAndQuestion, "Грешка при испраќањето! Вашиот меил не е испратен!");
        SeleniumWebDriver.logMeOut();
    }

    @Test(priority = 16, enabled = true)
    public static void sendMsgForCreateRequestTitleEmpty() {
        SeleniumWebDriver.logMeIn("misho.dimitrijevski@gmail.com", "cargodom2022");
        SeleniumWebDriver.createTransportRequest("", "Разно", "urgent", "1",
                "2", "3", "4", true);
        String negativeMsg1 = SeleniumWebDriver.getMessage("//*[@id=\"newRequestForm\"]/div/div[2]/div[3]/p");
        Assert.assertEquals(negativeMsg1, "Ова поле е задолжително.");
        String negativeMsg2 = SeleniumWebDriver.getMessage("//*[@id=\"newRequestForm\"]/div/div[14]/div/p");
        Assert.assertEquals(negativeMsg2, "Ве молиме пополнете ги сите задолжителни полиња!");
        SeleniumWebDriver.logMeOut();
    }

    @Test(priority = 17, enabled = true)
    public static void sendMsgForCreateRequestPaymentMethodEmpty() {
        SeleniumWebDriver.logMeIn("misho.dimitrijevski@gmail.com", "cargodom2022");
        SeleniumWebDriver.createTransportRequest("Fish", "Разно", "urgent",
                "1", "2", "3", "4", false);
        // Works with Full XPath only
        String negativeMsg3 = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div[2]/div/form/div/div[11]/div/div/div[6]/p");
        Assert.assertEquals(negativeMsg3, "Ве молиме изберете најмалку еден начин на плаќање.");
        SeleniumWebDriver.logMeOut();
    }

    @Test (priority = 18, enabled = true)
    public static void logoutVerification() {
        SeleniumWebDriver.logMeIn("misho.dimitrijevski@gmail.com", "cargodom2022");
        SeleniumWebDriver.logMeOut();
        String loginBtnVisibility = SeleniumWebDriver.getMessage("//*[@id=\"login\"]/span[2]");
        Assert.assertEquals(loginBtnVisibility,"Најава");
        String registrationBtnVisibility = SeleniumWebDriver.getMessage("/html/body/div[1]/nav/div[3]/ul/li[3]/a/span");
        Assert.assertEquals(registrationBtnVisibility,"Регистрација");
    }

    @Test(priority = 19, enabled = true)
    public static void transporterRegistartionVerification() {
        SeleniumWebDriver.register(
                true,
                "",
                "Misho",
                "Dimitrijevski",
                "MishoTrans",
                "Partizanska 44",
                "Skopje",
                "1000",
                "Macedonia",
                "666457668990",
                "33334444",
                "mishod_" + System.currentTimeMillis() + "@gmail.com",
                "123456",
                "123456");
        String transporterRegVerification1 = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/h1");
        Assert.assertEquals(transporterRegVerification1,"ДОБРЕДОЈДОВТЕ!");
        String transporterRegVerification2 = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div/div/h3");
        Assert.assertEquals(transporterRegVerification2, "Вашиот профил е успешно креиран! Ве молиме проверете го Вашиот e-mail за да ја завршите регистрацијата.");
    }

    @Test (priority = 20, enabled = true)
    public static void transporterLoginVerification() {
        SeleniumWebDriver.logMeIn("dimitrijevski@gmail.com","1234");
        String transporterLoginVerificationMsg1 = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div[1]/div/div/h3");
        Assert.assertEquals(transporterLoginVerificationMsg1,"Misho Dimitrijevski");
        String transporterLoginVerificationMsg2 = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div[2]/div/div[1]/div[1]/div[1]/h3");
        Assert.assertEquals(transporterLoginVerificationMsg2,"Име на фирмата");
        SeleniumWebDriver.logMeOut();
    }

    @Test (priority = 21, enabled = true)
    public static void findRequest() {
        SeleniumWebDriver.logMeIn("dimitrijevski@gmail.com", "1234");
        SeleniumWebDriver.findRequest("Macedonia", "Macedonia","Разно");

        SeleniumWebDriver.logMeOut();
    }

    @Test (priority = 22, enabled = true)
    public static void sendOffer() {
        SeleniumWebDriver.logMeIn("dimitrijevski@gmail.com", "1234");
        SeleniumWebDriver.findRequest("Macedonia", "Macedonia","Разно");
        SeleniumWebDriver.sendOffer("500", "20.10.2022 00:00");

        String sendOfferVerification = SeleniumWebDriver.getMessage("/html/body/div[3]/div[1]/div[2]/div[1]/div[5]/div/div[2]/div/button[2]");
        Assert.assertEquals(sendOfferVerification, "ОТКАЖИ ЈА ПОНУДАТА");
        SeleniumWebDriver.logMeOut();
    }

    @AfterClass
    public static void afterClass() {
        SeleniumWebDriver.end();
    }
}



