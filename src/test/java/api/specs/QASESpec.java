package api.specs;

import config.Credentials;
import config.Driver;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class QASESpec {

    public static final RequestSpecification REQ_SPEC = with()
            .baseUri(Driver.config.getBaseApiUri())
            .filter(withCustomTemplate())
            .log().uri()
            .log().body()
            .contentType(JSON)
            .header("Token", Credentials.config.getToken());

    public static final ResponseSpecification responseWithStatusCode(int statusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .expectStatusCode(statusCode)
                .build();
    }
}
