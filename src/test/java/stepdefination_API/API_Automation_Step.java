package stepdefination_API;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.junit.Assert;

import io.restassured.response.Response;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utilities.BaseClass;
import utilities.RandomGenerator;
//import com.google.common.io.Files;

public class API_Automation_Step {
	private Response response;
	private Response listResponse;

	@Given("Launch the browser and navigate to the website")
	public void launch_the_browser_and_navigate_to_the_website() {
//		BaseClass.browserLaunchAndNavigateToWebsite("chrome");
//		BaseClass.browserLaunchAndNavigateToWebsite("edge");
		BaseClass.browserLaunchAndNavigateToWebsite("firefox");
	}

	@And("^User fetch the data from (.*)$")
	public void user_fetch_the_data_from_api(String url) throws URISyntaxException {
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification requestSpecification = RestAssured.given();
		response = (Response) requestSpecification.when().get(new URI(url));
	}

	@Then("^User verify the (.*)$")
	public void user_verify_the_response(String statusCode) {
		Assert.assertEquals(statusCode, response.getStatusCode() + "");
		Assert.assertEquals("application/json; charset=utf-8", response.contentType());
		response.then().assertThat().contentType("application/json; charset=utf-8");
	}

	@Given("^User get the data from (.*) for list users$")
	public void user_get_the_data_from_api_for_list_users(String listurl) throws URISyntaxException {
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification requestSpecification = RestAssured.given();
		listResponse = (Response) requestSpecification.when().get(new URI(listurl));

	}

	@Then("^Verify the (.*) and body content for list users$")
	public void user_verify_the_status_code_and_body_content_for_list_users(String statusCode) throws IOException {
		Assert.assertEquals(statusCode, listResponse.getStatusCode() + "");
		listResponse.then().assertThat().contentType("application/json; charset=utf-8");
//		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		Files.copy(f, new File("C:\\Users\\Onkar Jadhao\\Documents\\Screenshots\\list.jpg"));

	}

	@Then("Browser closed")
	public void browser_closed() {
		BaseClass.browserClosed();
	}

	private RequestSpecification _REQUEST_SPEC;
	private Response _RESP;
	String unique_cat_name;
	String unique_cat_job;

	@Given("User is on the base uri")
	public void user_is_on_the_base_uri() {
		_REQUEST_SPEC = given().baseUri("https://reqres.in");

	}

	@SuppressWarnings("unchecked")
	@When("User submit create post request with unique name and job name")
	public void user_submit_create_post_request_with_unique_name_and_job_name() {
		// Body
		JSONObject body_json = new JSONObject();
		unique_cat_name = RandomGenerator.getFirstName();
		unique_cat_job = RandomGenerator.getJobName();
		body_json.put("job", unique_cat_job);
		body_json.put("name", unique_cat_name);
		// Headers
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json; charset=utf-8");

		_RESP = _REQUEST_SPEC.headers(headers).body(body_json).when().post("/api/users");
	}

	@Then("API returns the response with status code as {int}")
	public void api_returns_the_response_with_status_code_as(Integer int1) {
		_RESP.then().statusCode(int1);
	}

	@Then("new id is created in the system")
	public void new_id_is_created_in_the_system() {
		_RESP.then().assertThat().body("job", equalTo(unique_cat_job));
		_RESP.then().assertThat().body("name", equalTo(unique_cat_name));
	}

}
