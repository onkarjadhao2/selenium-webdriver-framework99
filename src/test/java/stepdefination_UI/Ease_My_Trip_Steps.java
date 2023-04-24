package stepdefination_UI;

import org.openqa.selenium.WebDriver;

import implementation.Ease_My_Trip_Methods;
import io.cucumber.java.en.*;

public class Ease_My_Trip_Steps {
	WebDriver driver;
	public static Ease_My_Trip_Methods easemytrip;

	@Given("Open the browser for Ease My Trip")
	public void open_the_browser_for_ease_my_trip() {
		System.out.println("Step1: Browser is open");
		easemytrip = new Ease_My_Trip_Methods(driver);
		// easemytrip.browser_Open("chrome");
		easemytrip.browser_Open("firefox");
		// easemytrip.browser_Open("edge");
	}

	@Given("User is on the Ease my trip website")
	public void user_is_on_the_ease_my_trip_website() throws InterruptedException {
		System.out.println("Step2: User is on the Ease my trip website");
		easemytrip.navigate_To_EaseMyTrip();

	}

	@When("User click on One Way Option")
	public void click_on_one_way_option() {
		System.out.println("Step3: User click on the One way option");
		easemytrip.one_Way();
	}

	@When("^User-filled travel details for (.*) to (.*)$")
	public void fillup_all_travel_details(String Origin, String Destination) throws Exception {
		System.out.println("Step4: Fillup all travel details");
		easemytrip.travel_Details(Origin, Destination);
	}

	@When("User click on the search button")
	public void click_on_the_search_button() throws Exception {
		System.out.println("Step5: Click on the search button");
		easemytrip.search();
	}

	@When("User navigated to search result page")
	public void user_navigated_to_search_result_page() throws Exception {
		System.out.println("Step6:User navigated to search result page ");
		easemytrip.search_Result_Page();
	}

	@And("User click on book button")
	public void user_click_on_book_button() throws Exception {
		System.out.println("Step7:User click on book button");
		easemytrip.book_Button();
	}

	@And("User navigated to the flight details page")
	public void user_navigated_to_the_flight_details_page() throws Exception {
		System.out.println("Step8:User navigated to the flight details page");
		easemytrip.flight_Details();
	}

	@And("^User filled the details and click on continue booking$")
	public void user_choose_insurance_option_and_click_on_continue_booking() throws Exception {
		System.out.println("Step9:User filled the details and click on continue booking");
		easemytrip.user_Details();
	}

	@Then("User complete the payment process")
	public void user_complete_the_payment_process() throws Exception {
		System.out.println("Step10:User complete the payment process");
		easemytrip.payment_Details();
	}

	@Given("Open the broser for Ease My Trip for Round Trip")
	public void open_the_broser_for_ease_my_trip_for_round_trip() {
		System.out.println("Step1: Browser is open");
		easemytrip = new Ease_My_Trip_Methods(driver);
		// easemytrip.browser_Open("chrome");
		// easemytrip.browser_Open("firefox");
		easemytrip.browser_Open("edge");
	}

	@Given("User is on the Ease My trip website for Round Trip")
	public void user_is_on_the_ease_my_trip_website_for_round_trip() throws InterruptedException {
		System.out.println("Step2: User is on the Ease my trip website");
		easemytrip.navigate_To_EaseMyTrip();

	}

	@When("User click on the Round Trip Option")
	public void click_on_the_round_trip_option() {
		easemytrip.round_trip();
	}

	@When("User fillup all travel details for Round Trip")
	public void fillup_all_travel_details_for_round_trip() throws Exception {
		System.out.println("Step4: Fillup all travel details");
		easemytrip.travel_details_round_trip();
	}

	@When("User verified and add values in traveller and class")
	public void user_verified_and_add_values_in_traveller_and_class() throws Exception {
		System.out.println("Step5: User verified and add values in traveller and class ");
		easemytrip.traveller_and_class();
	}

	@When("User click on the Search button for Round Trip")
	public void Click_on_the_Search_button_for_Round_Trip() throws Exception {
		System.out.println("Step6: Click on the search button");
		easemytrip.search();
	}

	@When("User is navigated to search result page for round trip")
	public void user_is_navigated_to_search_result_page_for_round_trip() throws Exception {
		System.out.println("Step7: User is navigated to search result page for round trip");
		easemytrip.search_Result_Page_roundtrip();
	}

	@When("User select the flights and click on book button")
	public void user_select_the_flights_and_click_on_book_button() throws Exception {
		System.out.println("Step8: User select the flights and click on book button");
		easemytrip.book_button_round_trip();
	}

	@And("User filled the details and click on continue booking for round trip")
	public void user_choose_insurance_option_and_click_on_continue_booking_for_round_trip() throws Exception {
		System.out.println("Step9:User filled the details and click on continue booking");
		easemytrip.user_Details_roundtrip();
	}

	@Then("User complete the payment process for round trip")
	public void user_complete_the_payment_process_for_round_trip() throws Exception {
		System.out.println("Step10:User complete the payment process");
		easemytrip.payment_Details();
	};

	@Given("Open the browser for E-commarce site")
	public void open_the_browser_for_e_commarce_site() {
		easemytrip = new Ease_My_Trip_Methods(driver);
		easemytrip.browser_Open("chrome");
		// easemytrip.browser_Open("firefox");
	//	easemytrip.browser_Open("edge");

	}

	@Given("User is on the website")
	public void user_is_on_the_website() throws InterruptedException {
		easemytrip.navigate_To_Ecommerce();
	}

	@Given("User complete the login process")
	public void user_complete_the_login_process() {
		easemytrip.login_Ecommerce();
	}

	@When("User enter the item name and search the product")
	public void user_enter_the_item_name_and_search_the_product() {
		easemytrip.search_the_product();
	}

	@When("User add the product into the cart")
	public void user_add_the_product_into_the_cart() {
		easemytrip.add_to_cart();
	}

	@When("fillup all the required address details and payment details")
	public void fillup_all_the_required_address_details_and_payment_details() {
		easemytrip.user_details_ecommerce();
	}

	@Then("User assert the order details")
	public void user_assert_the_order_details() {
		easemytrip.place_order();
	}

}
