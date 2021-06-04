package resources;

import java.util.ArrayList;
import java.util.List;

import io.restassured.response.Response;
import pojo.SerializationAddPlace;
import pojo.SerializationLocationSubJson;

public class TestDataBuild{
	public SerializationAddPlace addPlacePayLoad(String name, String language, String address ) {
	SerializationAddPlace sap = new SerializationAddPlace();
	sap.setAccuracy(50);
	sap.setAddress(address);
	sap.setLanguage(language);
	sap.setPhone_number("9876534568");
	sap.setWebsite("http://Rahulshettyacademy.com");
	sap.setName(name);
	List<String> myList = new ArrayList<String>();
	myList.add("Saree house");
	myList.add("shop");
	sap.setTypes(myList);
	SerializationLocationSubJson sj = new SerializationLocationSubJson();
	sj.setLat(-38.323464);
	sj.setLng(33.478776);
	sap.setLocation(sj);
	return sap;
}
	public static String deletePlacePayload(String grabbedPlace_id) {
		
		return "{\r\n" + 
				"   \"place_id\": \""+grabbedPlace_id+"\"\r\n" + 
				"   \r\n" + 
				"}";
		
	}
}