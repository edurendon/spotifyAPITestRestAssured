@Comp_Artist
Feature: Test Artist

@TestApp @validToken
Scenario: Test a valid token
	Given Data for the test
	When Connect with the url
	When Invoke microservice "validToken"
	Then Do evidences file

@TestApp @withoutValidToken
Scenario: Test without valid token - 401
	Given Data for the test
	When Connect with the url
	When Invoke microservice "withoutValidToken"
	Then Do evidences file

@TestApp @invalidToken
Scenario: Test with invalid token - 401
	Given Data for the test
	When Connect with the url
	When Invoke microservice "invalidToken"
	Then Do evidences file
