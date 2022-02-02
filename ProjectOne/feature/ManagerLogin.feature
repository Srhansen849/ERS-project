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
@tag
Feature: Manager Logging in to ERS App
  As an Manager, I wish to login to the ERS App using proper Credentials

  @tag1
  Scenario: Successful Login to the ERS App
    Given a user is at the login page
    When a user inputs thier user name
    And a user inputs thier password
    And a user submits the information
    But that user is a user of role Manager
    Then the user is redirected to the Manager ERS Page

  @tag2
  Scenario Outline: Successful Login to the ERS App
    Given a user is at the login page
    When a user inputs thier user name "<username>"
    And a user inputs thier password "<password>"
    And a user submits the information
    But that user is a user of role Employee
    Then the user is redirected to the Employee ERS Page

    Examples: 
      | username    | password    |
      | mendezodin1 | Password123 |
      | noelstua1   | Password123 | 



  @tag3
  Scenario: Failure to Login to the ERS App
    Given a user is at the login page
    When a user incorrectly thier user name
    And a user incorrectly thier password
    But a user submits the information
    Then the user should see the span of The username or password was not correct

  @tag4
  Scenario Outline: Successful Login to the ERS App
    Given a user is at the login page
    When a user inputs thier user name "<username>"
    And a user inputs thier password "<password>"
    But a user submits the information
    Then the user should see the span of The username or password was not correct

    Examples: 
      | username | password |
      | adfgadfh | dfnhamfg |
      | adfhadfn | fhkfjkfk | 
      