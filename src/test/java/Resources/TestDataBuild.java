package Resources;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import Pojo_classes.AddPlace;
import Pojo_classes.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		//setType is expecting list of string, so we will create list here first
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		//setLocation is expecting Location class object. so we will be creating object for the same first.
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);
		//returning the object ap as it is passed in AddPlaceStepDefination class in the RequestObject = given().spec(req).body(ap);
		//now to pass the ap we create object of the this class and pass it like RequestObject = given().spec(req).body(data.addPlacePayload());
		return ap;
	}
}
