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
    Then confirm radio button is enabled and is clickable for the user
    When a user selects an appropriate radio button for GLENCORE INTL on the Buy Stock page
    Then confirm the radio button clicked has been properly selected
    When a user clicks on the Buy button on the Buy Stock page
    Then confirm that the user is correctly brought to Buy Shares page
    When a user enters an amount of shares to buy on the Buying Shares page
    And a user clicks the Calculate button on the Buying Shares page
    Then confirm that the calculate results shown in Transaction Summary are correct
    When a user clicks the Confirm button
    Then confirm that all the details are correct on the Transaction Confirmation page
    When a user clicks the Transaction History link on the Navbar
    Then confirm that the user is correctly brought to Transaction History page
    And confirm all details are correct on the Transaction History page
