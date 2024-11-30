package api.account;

import api.models.LoginRequestModel;
import api.models.LoginResponseModel;
import data.AuthorizedData;

import static io.restassured.RestAssured.given;
import static specs.CodeDemoQASpec.requestSpec;
import static specs.CodeDemoQASpec.successfulResponse200Spec;

public class Authorization {
    public static LoginResponseModel userAuthorizationApi() {
        LoginRequestModel request = new LoginRequestModel(AuthorizedData.USER_NAME, AuthorizedData.USER_PASSWORD);
        return given(requestSpec)
                .body(request)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(successfulResponse200Spec)
                .extract().as(LoginResponseModel.class);
    }
}
