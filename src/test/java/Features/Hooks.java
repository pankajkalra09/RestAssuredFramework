package Features;

import java.io.IOException;

import StepDefination.AddPlaceStepDefination;
import io.cucumber.java.Before;

//If we want to run dependent test cases independently, we will make use of a concept in Cucumber called HOOK. 
//Hook will help us setting up preconditions and postconditions for our cucumber scenario.
//We will create a java class and define some preconditions before running the dependent scenario (i.e. deleteplace as it is using the 
//placeid created from addplace and we are running delete scenario independent, so it is throwing error as placeid is missed in the request.)
//We will make use of @Before(@DeleteTagName) and define a method stating that before executing deletetag scenario, run that method.

public class Hooks {
	
	//@Before("@DeletePlaceTag")
	public void Precondition() throws IOException {
			//Execute this code only when place id is null.
			//Write a code that will give you placeId.
			//we will call the given method for add place from AddPlaceStepDefination class.
			
		AddPlaceStepDefination AddPlaceobject = new AddPlaceStepDefination();
			
			if(AddPlaceStepDefination.placeID==null) {
				AddPlaceobject.add_place_payload("Philip", "French", "Asia");
				AddPlaceobject.user_calls_with_http_request("AddPlaceAPI", "Post");
				AddPlaceobject.verify_place_id_created_maps_to_using("Philip", "getPlaceAPI");
				//System.out.println("hello");
			}
			
		}

		
	}
	
	
	
