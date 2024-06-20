package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	//we will include all reusable code here and pass it to AddPlaceStepDefinition class.
	
	public static RequestSpecification req;// made is static as we are clearly telling that do not create another instance or use this single instance in entire execution.
	// using static it will ensure that for the second test execution value of req is not null and coming from the previous test execution result.
	//If we don't make it static in this case value of req will be always null for each execution.
	
	public RequestSpecification requestSpecification() throws IOException 
	{	
		if(req==null) {
		PrintStream logObject = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalData("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(logObject))
				.addFilter(ResponseLoggingFilter.logResponseTo(logObject))
		.setContentType(ContentType.JSON).build();
		//RequestLoggingFilter and ResponseLoggingFilter will be applied to your object req so that our object will have knowledge of
		//logging filter and it will log everything as part of request/response. and LogRequestTo() will ask that where to log the logs. i.e console or file
		//we need to build 1 object for printstream class that clearly tells where we are logging to.
		//Now all the logs of request and response will be logged in logging.txt file.
		return req;
		}
		return req;
		// with req=null and returning req outside loop we are trying to protect the execution of same method twice 
		//in case we are passing multiple test data using Examples in the feature file.
		
	}
	
	public static String getGlobalData(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("E:\\Projects\\BDD_API_FRAMEWORK\\src\\test\\java\\Resources\\globalData.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPathElement(Response response, String key) {
		 String ResponseVerification = response.asString();
		   JsonPath js1 = new JsonPath(ResponseVerification);
		   return js1.get(key).toString();
	}
}
