package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.SerializationAddPlace;
import pojo.SerializationLocationSubJson;
import resources.TestDataBuild;
import resources.Utils;
import resources.enumClass;

public class stepDefinitions extends Utils {

	ResponseSpecification res;
	RequestSpecification r1;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String grabbedPlace_id;

	@Given("Add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String language, String address) throws Exception {

//post by SpecBuilders

		r1 = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, address, language));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions
		enumClass resourseAPI = enumClass.valueOf(resource);
		if (method.equalsIgnoreCase("POST")) {
			System.out.println(resourseAPI.getResource());
			response = r1.when().post(resourseAPI.getResource());
		}
		// then().spec(res).extract().response();
		else if (method.equalsIgnoreCase("GET")) {
			System.out.println(resourseAPI.getResource());
			response = r1.when().get(resourseAPI.getResource());
		}

		else if (method.equalsIgnoreCase("DELETE")) {
			System.out.println(resourseAPI.getResource());
			response = r1.when().delete(resourseAPI.getResource());
		}

	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedStatus) {
		// Write code here that turns the phrase above into concrete actions
		String grabbedStatus = getJson(response, keyValue);
		assertEquals(grabbedStatus, expectedStatus);
	}

	@Then("verify place_id created by maps with {string} using {string}")
	public void verify_place_id_created_by_maps_with_using(String expectedName, String resource) throws IOException {
		// requestSpec
		grabbedPlace_id = getJson(response, "place_id");
		r1 = given().spec(requestSpecification()).queryParam("place_id", grabbedPlace_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJson(response, "name");
		assertEquals(actualName, expectedName);

	}
	@Given("Delete Payload")
	public void given_delete_Payload() throws IOException {
		r1 = given().spec(requestSpecification()).body(TestDataBuild.deletePlacePayload(grabbedPlace_id));
	    
	}

}
