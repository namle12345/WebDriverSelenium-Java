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
Feature: Buy Stock
  Description: Able to buy stock in companies from existing user

  @GeneralBuyStock
  Scenario: Test that a user can buy stock
    Given a user is on the Buy Stock screen of the Trading Platform
    When a user clicks on G link on the Buy Stock page
    And a user selects an appropriate radio button for GLENCORE INTL on the Buy Stock page
    And a user clicks on the Buy button on the Buy Stock page
    And a user enters an amount of shares to buy on the Buying Shares page
    And a user clicks the Calculate button on the Buying Shares page
    And a user clicks the Confirm button
    Then confirm that the Company Name and Number of Shares bought is correct on the Transaction Confirmation page
    And confirm that the Company Name, Transaction Type and Number of Shares is correct on the Transaction History page
