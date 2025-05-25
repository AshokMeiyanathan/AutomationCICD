@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

 
  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with usrname <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is diplayed on ConfirmationPage

    Examples: 
      | name  | password | productName  |
      | RachelPineBrook@gmail.com |     RachelPineBrook1$ | ZARA COAT 3 |
