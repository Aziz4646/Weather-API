import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class APITest extends Base {

    private String response;
    private JsonPath js;

    @Test
    public void verifyNameAttributeCorrespondsToTheEnteredCityName() {
        setAppidKey(appidKeyData);
        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/";
        RequestSpecification request = RestAssured.given();
        Response response = request.queryParam("q", "Boston")
                .queryParam("appid", getAppidKey())
                .get("/weather");

        String jsonString = response.asString();
        Assert.assertTrue(jsonString.contains("Boston"));
        System.out.println("Status:  " + response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public void verifyCoordLatAndCoordLonAttributesCorrespondToTheOnesEntered() {
        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/";
        setAppidKey(appidKeyData);
        response = given().queryParam("lat", latitude).queryParam("lon", longitude)
                .queryParam("appid", getAppidKey())
                .get("/weather").then().extract().response().asString();

        js = new JsonPath(response);
        double longitudeResponse = js.getDouble("coord.lon");
        double latitudeResponse = js.getDouble("coord.lat");

        Assert.assertEquals(longitudeResponse, longitude);
        Assert.assertEquals(latitudeResponse, latitude);

        System.out.println("longitude: " + longitudeResponse);
        System.out.println("latitude: " + latitudeResponse);

    }

    @Test
    public void userHitsTheURLByTheZipCode() {
        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/";
        setAppidKey(appidKeyData);
        response = given().queryParam("zip", "11229")
                .queryParam("appid", getAppidKey())
                .get("/weather").then().extract().response().asString();
        js = new JsonPath(response);
        String cityNameResponse = js.get("name");

        Assert.assertEquals(cityNameResponse, "Brooklyn");
        System.out.println("City Name: " + cityNameResponse);
    }
}