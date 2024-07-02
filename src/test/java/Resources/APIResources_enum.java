package Resources;

//enum is a special class in java which has collection of constants and methods.
public enum APIResources_enum {
	//we will declare a method using below syntax and enum treats this as a method. We don't need a parenthesis, body, return etc.
	//we can declare numtiple methods separated by comma and at the end we can use semicolon to complete the statement.
	//we have to define a constructor in enum when working with methods and that constructor accepts an argument.
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String resource;
	APIResources_enum(String resource) { //resource is storing value of AddPlaceAPI or getPlaceAPI or deletePlaceAPI
		// TODO Auto-generated constructor stub
		this.resource=resource;
	}
	//when we will use this constructor in step definition class, it will expose a method called valueof()
	//and that valueOf() will call the constructor. its like creating object for that enum.
	
	//In our feature file we have defined "AddPlaceAPI" in 'when' line which means that value will be stored in step definition in 
	//"public void user_calls_with_post_http_request(String resource)" method in 'resource' argument of String type 
	//and when we are calling "APIResources_enum.valueOf(resource);" in step definition class,
	//means the value of resource that is AddPlaceAPI gets stored in the constructor and feature file gets executed.
	//Using this we have to simply modify the parameter "AddPlaceAPI" in our feature file and same step definition method will work. We need not to write new step defination for get or delete.
	//just change the value in feature file and corresponding recourse will get passed in the post method.
public String getResource() {
	return resource;
	
}

//we are using the getResource method to return the value of the resource that has the value "/maps/api/place/add/json"
//So now using enum classes we need to define the methods only and all the methods using the same constructor and using the resource and pass the value to resource
// variable and using get method, we will utilize the value.
}

