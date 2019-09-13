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
Feature: Reset Password
  Description: Able to reset an existing users' password

  @ResetUserPassword
  Scenario: Reset an existing user's password
    Given a user is on the Login screen of the Trading Platform
    When a user clicks on the Forgot Password link on the Check Balance page
    And a user enters in the customer's valid data on the Request Password Reset page
    And a user clicks on the Reset Password button on the Request Password Reset page
    Then confirm that the appropriate confirmation reset password message has appeared
    When a user can login with their new password on the Login screen
    Then confirm the appropriate login confirmation message has appeared
