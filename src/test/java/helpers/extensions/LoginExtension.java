package helpers.extensions;

import api.account.Authorization;
import api.models.LoginResponseModel;
import data.AuthorizedData;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        LoginResponseModel cookies = Authorization.userAuthorizationApi();
        LoginResponseModel model = new LoginResponseModel();
        String userToken = model.getToken();
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", cookies.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", cookies.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", cookies.getToken()));

        open("/profile");
        $("#userName-value").shouldHave(text(AuthorizedData.USER_NAME));
    }
}
