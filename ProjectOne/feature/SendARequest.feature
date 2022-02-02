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
Feature: Employee sends a request
  As an Employee, I wish to send a request for a reimbursement

  @tag1
  Scenario: Sends a request
    Given a user is logged in
    When the user enters in a title 
    And the user enters in a description 
    And the user enters in what type 
    And the user enters in an amount 
    And the user enters in an image
    And the user submits the information
    Then the request is submitted to the database
    And takes user to the Current Request Page

  @tag2
  Scenario Outline: Sends a request
    Given a user is logged in
    When the user enters in a title "<title>"
    And the user enters in a description "<description>"
    And the user enters in what type "<type>"
    And the user enters in an amount "<amount>"
    And the user enters in an image "<receipt>"
    And the user submits the information
    Then the request is submitted to the database
    And takes user to the Current Request Page

    Examples: 
      | title          | description                    | type  | amount | 
      | Test Request   | This is a test request to 
      								   see what the values submitions 
      								   should look like               | Other | 69.69  |
      | Test Request 2 | This is a test request to see 
      									 if the function works          | Other | 69.69  |
      									 
      									 
      									 
      									 
      									 
      									 
      									 
      									 
      									 
