Feature: Validating Place API's 
@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
 	Given Add Place Payload "<name>" "<address>" "<language>"
 	When user calls "AddPlaceAPI" with "POST" http request
 	Then the API call got success with status code 200
 	And "status" in response body is "OK"
 	And "scope" in response body is "APP"
 	And verify place_id created by maps with "<name>" using "GetPlaceAPI"
 	
Examples: 
	|name  |           address         |language|
 	|Karnan|Vijaya theater,krithi nagar|Tamil|
@DeletePlace
 Scenario: Verify if Delete place functionality is working as expected
 Given Delete Payload
 When user calls "DeletePlaceApi" with "DELETE" http request
 Then the API call got success with status code 200
 And "status" in response body is "OK"
 