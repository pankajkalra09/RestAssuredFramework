Feature: Validating Add Place API's

Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI
Given Add Place Payload with "<Name>" "<Language>" "<Address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify place_id created maps to "<Name>" using "getPlaceAPI"
# we can make use of test data that is add place details using data driven having different set of data.
# We will be using Examples keyword to declare the scenario to send the different set of data. 
# Let's say for each add place request some values are dynamically driven from test including Name, Language and Address and remainign all will be static.
# If we will be using Examples then we have to tell our cucmber feature that this is not a scenario instead its a Scenario Outline.
# Now to which line this data needs to be passed, for that we need to clearly tell in our feature file that we will be using "<name>" etc. That
# indicates the name is a column name and in the step defination file for the given method we need to modify the defination.
# When we run this testrunner file, for given it will go to step defination file and Name will be stored in StringName and respectivaly all values get stored in the given method.
# From the given method control goes to TestDataBuild class and update the values there.

Examples:
		| 		Name 		| 		Language 		| 		Address		 |
		|			Pankaj	|		English				| World Cross		 |
	#	|     Philip	|		Germen				|	World Class		 |
		
# here when we are passing multiple data, it replaced the value passed in 1st test, but this is not what we wanted.
# FOr that in the Utils class 'requestSpecification()' runs 2 times and override the data in loggin.txt file.
# so we set a condition that if req object is null than run the logic else return the req object.


#now we want to add 1 more And condition that verify the name we passed in add is same as we capture in GetplaceID.
#From AddPlaceAPI we will get Place ID and same place ID we will be using in getPlaceAPI and verify if the name we passed in add is same as in Get.