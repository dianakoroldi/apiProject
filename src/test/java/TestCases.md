##  _LIST OF USER_
 ### Test Cases:

### Positive Scenario:

#### Test case 1: Test successful request
_Description:_ Verify that a successful response is returned when sending a GET request to the base URL.
* #### _Test Steps:_
* Set the base URI and base path.
* Send a GET request to the base URL.
* Validate that the response status code is 200 OK.
* Validate that the response content type is application/json.


#### Test case 2: Test page number
_Description:_ Verify that the page number returned in the response is correct.
* #### _Test Steps:_
* Set the base URI and base path.
* Send a GET request to the base URL with the query parameter "page" set to 2.
* Validate that the response body contains the correct page number (2).

#### Test case 3: Test all users data
_Description:_ Verify that all user data is returned in the response and is not empty.
* #### _Test Steps:_
* Set the base URI and base path.
* Send a GET request to the base URL with the query parameter "page" set to 2.
* Extract the response body and deserialize it to a list of User objects.
* For each user in the list, validate that the ID, first name, last name, email, and avatar are not empty.


#### Test case 4: Test users count matches total
_Description:_ Verify that the total number of users returned in the response matches the expected total.
* #### _Test Steps:_
* Set the base URI and base path.
* Send two GET requests to the base URL with the query parameter "page" set to 1 and 2 respectively.
* Extract the response body for each page and deserialize it to a list of User objects.
* Calculate the actual total number of users returned by summing the number of users in each page's list.
* Extract the total number of users from the response for the first page.
* Validate that the actual total number of users matches the expected total.


#### Test case 5: Store user information in a map
_Description:_ Verify that user information can be stored in a map and printed to the console.
* #### _Test Steps:_
* Set the base URI and base path.
* Send a GET request to the base URL with the query parameter "page" set to 2.
* Extract the response body and deserialize it to a list of User objects.
* For each user in the list, store their email address in a map with the key being their first and last name concatenated with a period.
Print the contents of the map to the console.

### Negative Scenario: 

#### Test case 6: Test invalid endpoint
_Description:_ Verify that an error response is returned when sending a GET request to an invalid endpoint.
* #### _Test Steps:_
* Set the base URI and base path.
* Send a GET request to an invalid endpoint.
* Validate that the response status code is 404 Not Found.
* Validate that the response content type is application/json.

## _SINGLE USER:_

### Positive Scenario:

#### Test Case 1: Test Status Code
_Description:_ Verify that a successful response is returned when sending a GET request to the Single User API.

* #### _Test Steps:_
* Set the base URI and base path to the Single User API endpoint.
* Send a GET request to the endpoint with id=2.
* Validate that the response status code is 200 OK.


#### Test Case 2: Test User Name, ID and Avatar URL
_Description:_ Verify that the response includes the user's name, id, and avatar URL.

* #### _Test Steps:_
* Set the base URI and base path to the Single User API endpoint.
* Send a GET request to the endpoint with id=2.
* Parse the response JSON to extract the user's name, id, and avatar URL.
* Validate that the name, id, and avatar URL are not empty.

#### Test Case 3: Test Support URL
_Description:_ Verify that the response includes a support URL.

* #### _Test Steps:_
* Set the base URI and base path to the Single User API endpoint.
* Send a GET request to the endpoint with id=2.
* Parse the response JSON to extract the support URL.
* Validate that the support URL is not empty.

#### Test Case 4: Test All Users Data
_Description:_ Verify that the response includes data for all 12 users.

* #### _Test Steps:_
* Set the base URI and base path to the Single User API endpoint.
* Send a GET request to the endpoint.
* Parse the response JSON to extract data for all users.
* Validate that data is returned for all 12 users.

### Negative Scenario:
#### Test Case 5: Test Invalid User ID
_Description:_ Verify that an error response is returned when an invalid user ID is specified.

* #### _Test Steps:_
* Set the base URI and base path to the Single User API endpoint.
* Send a GET request to the endpoint with an invalid user ID.
* Validate that the response status code is 404 Not Found.


## _SINGLE USER NOT FOUND_

### Positive Scenario:

#### Test case 1: Validate status code for single user not found
* #### _Test Steps:_
* Open the properties file and read the base URL.
* Create an HTTP request using the GET method to the URL https://reqres.in/api/users/23, where 23 is an invalid user ID.
* Send the request to the server.
* Retrieve the response from the server.
* Validate that the status code returned is 404.
* 
### Negative Scenario:
#### Test case 2: Validate empty response body for single user not found
* #### _Test Steps:_
* Open the properties file and read the base URL.
* Create an HTTP request using the GET method to the URL https://reqres.in/api/users/23, where 23 is an invalid user ID.
* Send the request to the server.
* Retrieve the response from the server.
* Validate that the status code returned is 404.
* Validate that the response body is empty.

## _LIST RESOURCE_

### Positive Scenario:
#### Test Case 1: Validate Unknown User Resource List

_Description:_ Ensure that a successful GET request is made to the unknown user resource endpoint and that the response contains a list of resources with valid data.

* #### _Test Steps:_

* Set the base URL to the value specified in the configuration file.
* Set the base path to "/api/unknown".
* Send a GET request to the endpoint.
* Verify that the response status code is 200.
* Extract the response body and convert it to a list of UnknownUserResource objects.
* Check if there are more pages in the response and add their data to the list.
* Calculate the sum of all "id" values in the list of resources.
* Verify that the sum of "id" values equals 78.
* Calculate the average of all "year" values in the list of resources.
* Verify that the average "year" value is between 2005 and 2006.
* And here's an example of how the test case for the testGetWithInvalidEndpoint method could be organized:

### Negative Scenario:
#### Test Case 2: Verify Invalid Endpoint Error Response

_Description:_ Ensure that a 404 error response is returned when a GET request is made to an invalid unknown user resource endpoint.

* #### _Test Steps:_

* Set the base URL to the value specified in the configuration file.
* Set the base path to "/api/unknown/78".
* Send a GET request to the endpoint.
* Verify that the response status code is 404.

# _SINGLE RESOURCE_

### Positive Scenario:
#### Test Case 1: Verify Successful Request
_Description:_ This test case validates that a successful GET request to the unknown endpoint with a valid ID returns a response with a status code of 200 and the expected response body.

* #### _Test Steps:_
* Send a GET request to the https://reqres.in/api/unknown/2 endpoint.
* Verify that the response status code is 200.
* Verify that the response content type is JSON.
* Verify that the response body contains the id, year, support text, and color fields with their expected values.

#### Test Case 2: Verify Unknown Endpoint
_Description:_ This test case validates that a GET request to the unknown endpoint returns a response with a status code of 200 and all resources within the page are successfully validated.

* #### _Test Steps:_
* Send a GET request to the https://reqres.in/api/unknown endpoint.
* Verify that the response status code is 200.
* Get the total number of pages from the response.
* Loop over each page and validate each resource within the data array.

### Negative Scenario
#### Test Case 3: Verify Invalid Request
_Description:_ This test case validates that a GET request to the unknown endpoint with an invalid ID returns a response with a status code of 404.

* #### _Test Steps:_
* Send a GET request to the https://reqres.in/api/unknown/100 endpoint.
* Verify that the response status code is 404.

## _Single Resource Not Found_

### Positive Scenario:

#### Test Case 1: Unknown Resource Returns 404
_Description:_ Verify that the application returns a 404 error and an empty JSON object when attempting to access an unknown resource with a non-existent ID.
* #### _Test Steps:_
* Set the base path for RestAssured to the endpoint for accessing an unknown resource with a non-existent ID.
* Send a GET request to the endpoint with the Accept header set to application/json.
* Verify that the response has a status code of 404.
* Verify that the response content type is application/json.
* Verify that the response body is an empty JSON object.

### Negative Scenario:

#### Test Case 2: Invalid Request Returns 404

* #### _Test Steps:_
_Description:_ Verify that the application returns a 404 error and an empty JSON object when attempting to access a resource with an invalid ID.

*Set the base path for RestAssured to the endpoint for accessing an unknown resource with an invalid ID.
* Send a GET request to the endpoint without any headers.
* Verify that the response has a status code of 404.
* Verify that the response content type is application/json.
* Verify that the response body is an empty JSON object.



