package resources;

public enum enumClass {

	AddPlaceAPI("maps/api/place/add/json"), 
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceApi("maps/api/place/delete/json");
	private String resource;

	enumClass(String resource) {
		this.resource = resource;

	}

	public String getResource() {

		return resource;
	}

}
