@Comp_Playlist
Feature: Test playlist

@TestApp @createPlaylist
Scenario: Test create playlist
	Given Data for the test
	When Connect with the url
	When Invoke microservice "createPlaylist"
	Then Do evidences file

@TestApp @failCreatePlaylist
Scenario: Test create playlist with invalid user id - 403
	Given Data for the test
	When Connect with the url
	When Invoke microservice "playlistInvalidUserId"
	Then Do evidences file