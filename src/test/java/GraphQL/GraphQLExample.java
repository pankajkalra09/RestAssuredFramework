package GraphQL;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class GraphQLExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Query
		
		//here our body expects the json and graphql query is intenally converted into json format and we can capture the same from our browser's network tab.
		//click on the network call > payload > View source > copy and paste it in body.
		
		int characterID=650;
		int locationID=190;
		String locationName = "Punjab";
		String episodeName = "PTC";
		
		String queryResponse = given().log().all().header("Content-Type", "application/json").
		body("{\"query\":\"query($characterId:Int!, $locationId:Int!){\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n    id\\n    status\\n  }\\n  location(locationId: $locationId) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: 872) {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name: \\\"Rahul\\\", type: \\\"Tester\\\"}) {\\n    info {\\n      count\\n    }\\n    result {\\n      id\\n      name\\n      type\\n      location {\\n        id\\n      }\\n    }\\n  }\\n  episodes(filters: {episode: \\\"hulu\\\", name: \\\"tom and jerry\\\"}) {\\n    info {\\n      count\\n      pages\\n    }\\n    result {\\n      name\\n      id\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":"+characterID+",\"locationId\":"+locationID+"}}").when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();

		System.out.println(queryResponse);
		JsonPath js = new JsonPath(queryResponse);
		String ActualName = js.getString("data.character.name");
		System.out.println(ActualName);
		
		//Mutation
		
		String mutationResponse = given().log().all().header("Content-Type", "application/json").
				body("{\"query\":\"mutation($locationName:String!, $episodeName:String!){\\n  createLocation(location: {name:$locationName, type:\\\"North\\\", dimension:\\\"234\\\"}){\\n    id\\n  }\\n  \\n  createEpisode(episode:{name:$episodeName, air_date:\\\"15 Aug\\\", episode:\\\"5\\\"}){\\n    id\\n  }\\n  \\n  deleteLocations(locationIds:[12329]){\\n    locationsDeleted\\n  }\\n}\",\"variables\":{\"locationName\":\"Punjab\",\"episodeName\":\"PTC\"}}").when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
		
		System.out.println(mutationResponse);
		JsonPath jsa = new JsonPath(mutationResponse);
		String ActualNamae = js.getString("data.createLocation.id");
		System.out.println(ActualNamae);
		
	}

}
