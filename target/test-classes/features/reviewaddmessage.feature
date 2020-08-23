Feature: review previous orders and add a message
Scenario: Add comment
Given user is on Order History page
When user selects an item from order
And user adds comment
Then comment appears under messages
And item 1 has a colour of 'Blue'