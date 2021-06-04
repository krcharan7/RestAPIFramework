package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws Exception {
		stepDefinitions sd = new stepDefinitions();
		if (stepDefinitions.grabbedPlace_id == null)
		{	sd.add_Place_Payload("charan", "sanskrit", "Asia");
		sd.user_calls_with_http_request("AddPlaceAPI", "POST");
		sd.verify_place_id_created_by_maps_with_using("charan", "GetPlaceAPI");
		}
	}
}
