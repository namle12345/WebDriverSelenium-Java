#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Register
  Description: Register a new user to the Trading Platform website

  @RegisterNewUser
  Scenario: Register a new user
    Given a user is on the Registration page of the Trading Platform
    When a user enters valid customer information onto the Registration page
    And a user enters unique "RT67@fdm.com" , "LontestRT67" and "Scam" in the relevant fields
    And a user clicks on the submit button on the Registration page
    Then confirm that an appropriate welcome message has appeared
    When a user clicks on Login link
    Then confirm that the user is correctly brought to the Login page
    When a user enters in the newly created account information on the Login page
    Then confirm that an appropriate user name message appears when logging in

