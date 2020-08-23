Feature: Purchase two items
	Scenario: View basket
		Given user is on Index Page
		When the user adds item 1 in size 'L' to the basket
		And user continues shopping
		And the user adds item 2 in size 'default' to the basket
		And goes to checkout
		Then basket contains 2 items
		And item 1 has size 'L'
		And item 2 has size 'S'
		And item 1 has advertised price
		And item 2 has advertised price
		And total products is sum of items
		And total equals total products plus shipping