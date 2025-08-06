
Feature: Hudl Login 

Scenario: Valid login 
Given I am on the Hudl login page
When  I enter valid credentials
And I click the login button 
Then I should be redirected to the dashboard

Scenario: Invalid login with correct email format 
Given I am on the Hudl login page
When I enter invalid credentials 
And I click the login button 
Then I should see a login error message


Scenario: Valid Login with invalid email format
Given I am on the Hudl login page
When I enter an invalid email format and a password
And I click the login button
Then I should see an invalid email format error message


Scenario: Blank email and continue
Given I am on the Hudl login page
When I leave email and password blank
And I click the login button
Then I should see a required fields error message

Scenario: Blank password
Given I am on the Hudl login page
When I enter a valid email and leave password blank
And I click the login button
Then I should see a password required error message


Scenario: Forgot password link
Given I am on the Hudl login page
When I enter valid email without password
When I click the Forgot Password link
Then I should see the password reset page