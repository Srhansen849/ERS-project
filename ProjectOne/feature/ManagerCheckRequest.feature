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
Feature: Manager checks employees request forms
  As a Manager, I wish to check my current employee request for reimbrusement

  @tag1
  Scenario: Approving an Employees Request Form
    Given a user has the role of manager 
    And a user is logged in
    When a user selects a current request
    And clicks the approved button
    Then the request will be submitted to the database
    And the user will return to the Current Pending Request Page

  @tag2
  Scenario Outline: Approving an Employees Request Form
    Given a user has the role of manager 
    And a user is logged in
    When a user selects a current request
    And clicks the approved button
    Then the request will be submitted to the database
    And the user will return to the Current Pending Request Page



  @tag3
  Scenario: Rejecting an Employees Request Form
    Given a user has the role of manager 
    And a user is logged in
    When a user selects a current request
    And a user leaves a comment
    And clicks the reject button
    Then the request will be submitted to the database
    And the user will return to the Current Pending Request Page

  @tag4
  Scenario Outline: Rejecting an Employees Request Form
    Given a user has the role of manager 
    And a user is logged in
    When a user selects a current request
    And a user leaves a comment "<comment>"
    And clicks the reject button
    Then the request will be submitted to the database
    And the user will return to the Current Pending Request Page  

    
    
  @tag5
  Scenario: Denying an Employees Request Form
    Given a user has the role of manager 
    And a user is logged in
    When a user selects a current request
    And a user leaves a comment
    And clicks the deny button
    Then the request will be submitted to the database
    And the user will return to the Current Pending Request Page

  @tag6
  Scenario Outline: Denying an Employees Request Form
    Given a user has the role of manager 
    And a user is logged in
    When a user selects a current request
    And a user leaves a comment "<comment>"
    And clicks the deny button
    Then the request will be submitted to the database
    And the user will return to the Current Pending Request Page
    
    