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
Feature: Check Balance
  Description: Able to check account details on the existing user

  @AddAccountWithFunds
  Scenario: Create a new currency account and add funds into it
    Given a user is on the Check Balance screen of the Trading Platform
    When a user clicks on Add New Account link on the Check Balance page
    And a user selects an appropriate currency type on the Add New Account page
    And a user clicks on the Add Account button on the Add New Account page
    And a user clicks Add Funds on the newly made account row
    And a user enters the value they wish to deposit into the account
    And a user clicks the confirm button
    Then confirm that the appropriate account has the correct number of funds added to it
