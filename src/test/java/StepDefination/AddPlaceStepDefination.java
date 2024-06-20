package StepDefination;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import Pojo_classes.AddPlace;
import Pojo_classes.Location;
import Resources.APIResources_enum;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AddPlaceStepDefination extends Utils{
	
	//Step definition class should have only core logic. 
	RequestSpecification RequestObject;
	ResponseSpecification responseObject;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	//test data is driven from TestDataBuild class as we are returning the object ap and passed it in RequestObject = given().spec(req).body(data.addPlacePayload());
	//For test data passing we are using serialization to build our java object based upon the pojo classes.
	
	//instead to creating object of Utils class and use the methods, we will extend that Utils class so that we can call them without creating objects.
	//now in the spec(req) in "RequestObject = given().spec(req).body(data.addPlacePayload());", we will include the method name as that method is returnig the req object.
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload(String StringName, String StringLanguage, String StringAddress) throws IOException {
		// we will pass the body using POJO classes and Request/Response spec classes.
		//we can break the request separately and use it further like below and use that object further with when/
		 RequestObject = given().spec(requestSpecification()).body(data.addPlacePayload(StringName, StringLanguage, StringAddress));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) //since we are using 2 parameters in when statement in feature file.
	{
		//In our feature file we have defined "AddPlaceAPI" in 'when' line which means that value will be stored in step definition in 
		//"public void user_calls_with_post_http_request(String resource)" method in 'resource' argument of String type 
		//and when we are calling "APIResources_enum.valueOf(resource);" in step definition class,
		//means the value of resource that is AddPlaceAPI gets stored in the constructor and feature file gets executed.
		//Using this we have to simply modify the parameter "AddPlaceAPI" in our feature file and same step definition method will work. We need not to write new step defination for get or delete.
		//just change the value in feature file and corresponding recourse will get passed in the post method.
	APIResources_enum resourceAPI = 	APIResources_enum.valueOf(resource);
	System.out.println(resourceAPI.getResource());
	//here we are printing the resource we are trying to use based upon the keyword we have used in the feature file. AddPlaceAPI, getPlaceAPI etc.
		responseObject = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		//now we are checking it the second parameter in when is post then use post method and so on. Test
		if(method.contentEquals("Post"))
		response = RequestObject.when().post(resourceAPI.getResource());
		else if(method.contentEquals("Get"))
			response = RequestObject.when().get(resourceAPI.getResource());
		else if(method.contentEquals("Delete"))
		response = RequestObject.when().delete(resourceAPI.getResource());
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	   assertEquals(response.statusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String Key, String ExpectedValue) {
	   
	    //System.out.println(response);
	   assertEquals(getJsonPathElement(response, Key), ExpectedValue);
	}
	
	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resourceused) throws IOException {
	    //steps 1: Use requestspecification that we have created in utils and prepare a getplaceapi request.
		
		//https://rahulshettyacademy.com/maps/api/place/get/json?key=qaclick123&place_id=d30877c5e8257b9eecdcbf5ba2f6740c
		String placeID = getJsonPathElement(response, "place_id");
		System.out.println(placeID);
		 RequestObject = given().spec(requestSpecification()).queryParam("place_id", placeID);
		 //Step 2: Call the getplace api 
		 user_calls_with_http_request(resourceused, "Get"); //this will call the getPlaceAPI as resourceused stores the same.
		 //now the response is stored and we will be capturing the value of Name fromt he same using the common method we have created in utils.
		 String actualName = getJsonPathElement(response, "name");
		 //now we have to compare the Name with what we passed in the Examples.
		assertEquals(actualName, expectedName);
	}

}
